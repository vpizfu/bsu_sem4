import socket
import threading
import os
import datetime

import gm
import idea

from protocol import *
from transport import *

HOST = ''
PORT = 11555


FILES_DIRECTORY_PATH = 'storage/'
SESSION_KEY_EXPIRY_DELTA = datetime.timedelta(seconds=10)
PASSWORD_HASH = b'\x7f\\(\x13\x15\xe3\xba[t;\xf3|\xacV\xc7z\x8ba\xdbj\xbf\xc2}\xd3\x08|\xdc\x9a\xf0J&\xa7T\xf8\xcf<\xf9:UN\xdbT\x1d\'\xe9\xf45\x9a3U\xdfM\x83:\x88\xa4\x13\x8c\xeb\xce8>\xef\x12\xc5\xb4\xe1\x88:\xb1/\\\xe8z\xadcK\xfe\xc8\xdf\xe7\xf4U\xc2\xaa\xa0{\xbe\x02\x95%\x99\xed\x81\xeb\x15\xe8\xa4n\xcb\xdfw}\xaa\xae+\xa4\x1e\x08B\xb4e\xe8\xfb\x82\x8e\xb4d#\x92\xc7\\\xe2"i\x9eW\x97'


def read_file(file_path):
    with open(file_path, 'r') as file:
        return file.read()


def get_file_names(files_dir_path):
    return [entry.name for entry in os.scandir(files_dir_path) if entry.is_file()] 


def handle_client(conn, addr):
    with conn:
        print('Connected by', addr)

        hello_request = Message.from_bytes(recv_msg(conn))
        if not isinstance(hello_request, ClientHelloRequest):
            raise IllegalMessageException()
        else:
            send_msg(conn, Message.to_bytes(ServerOkResponse()))

        authenticated = False
        while not authenticated:
            password_request = Message.from_bytes(recv_msg(conn))
            if not isinstance(password_request, SendPasswordRequest):
                raise IllegalMessageException()
            elif password_request.password == PASSWORD_HASH:
                authenticated = True
                send_msg(conn, Message.to_bytes(ServerOkResponse()))
            else:
                send_msg(conn, Message.to_bytes(WrongPasswordResponse()))

        gm_request = Message.from_bytes(recv_msg(conn))
        if not isinstance(gm_request, SendOpenKeyRequest):
            raise IllegalMessageException()
        else:
            open_gm_key = gm_request.open_key
            send_msg(conn, Message.to_bytes(ServerOkResponse()))

        session_key = None
        session_key_generation_time = None
        while True:
            request = Message.from_bytes(recv_msg(conn))
            if isinstance(request, GetSessionKeyRequest):
                session_key = idea.generate_key()
                session_key_generation_time = datetime.datetime.now()
                encrypted_session_key = gm.encrypt(session_key, open_gm_key)
                send_msg(conn, Message.to_bytes(GetSessionKeyResponse(encrypted_session_key)))
            elif isinstance(request, GetFileTextRequest):
                if not session_key:
                    raise IllegalMessageException('Client should request session key before file text')

                if datetime.datetime.now() - session_key_generation_time > SESSION_KEY_EXPIRY_DELTA:
                    send_msg(conn, Message.to_bytes(SessionKeyExpiredResponse()))
                else:    
                    file_name = request.file_name
                    file_text = read_file(FILES_DIRECTORY_PATH + file_name)
                    encrypted_file_text, initialization_list = idea.encrypt(file_text, session_key)
                    send_msg(conn, Message.to_bytes(GetFileTextResponse(encrypted_file_text, initialization_list)))
            elif isinstance(request, GetFileNamesRequest):
                send_msg(conn, Message.to_bytes(GetFileNamesResponse(get_file_names(FILES_DIRECTORY_PATH))))
            else:
                raise IllegalMessageException()


def run_server():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind((HOST, PORT))
        s.listen(2)
        while True:
            conn, addr = s.accept()

            thread = threading.Thread(target=handle_client, args=(conn, addr))
            thread.start()


if __name__ == '__main__':
    run_server()
