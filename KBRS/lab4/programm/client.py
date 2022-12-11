import socket
import hashlib

import gm
import idea

from protocol import *
from transport import send_msg, recv_msg


HOST = 'localhost'
PORT = 11555

MENU_MESSAGE = '''Enter 1 for regenerating a session key.
Enter 2 for requesting a file text.
Enter 3 for requesting file names.
Enter 4 for exit.
Choose operation: '''
ENTER_FILE_NAME_TEXT = '''Enter file name: '''

SALT = b'\x0e\x9bj+\x17K\x88ajI\xcb\x9f\xf1,h\xc9N\x95\xd7\xcd\x15\xd0\xed\xdf\xe8\x1d\x0e\xf5\xaf`\x03>'


def send_request(s, request):
    send_msg(s, Message.to_bytes(request))
    return Message.from_bytes(recv_msg(s))


def check_ok_response(response, request_name):
    if not isinstance(response, ServerOkResponse):
        raise IllegalMessageException('Server responded with error after ' + request_name + ': ' + str(response))


def request_session_key(sock):
    session_key_response = send_request(sock, GetSessionKeyRequest())
    if isinstance(session_key_response, GetSessionKeyResponse):
        return session_key_response.encrypted_session_key

    raise IllegalMessageException()


def run_client():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect((HOST, PORT))
        
        hello_response = send_request(s, ClientHelloRequest())
        check_ok_response(hello_response, 'ClientHello')

        authenticated = False

        while not authenticated:
            password = input('Enter password: ')
            password_hash = hashlib.pbkdf2_hmac('sha256', password.encode('utf-8'), SALT, 100000, dklen=128)
            send_password_response = send_request(s, SendPasswordRequest(password_hash))
            if isinstance(send_password_response, ServerOkResponse):
                authenticated = True
                print('You are successfully logged!')
            elif isinstance(send_password_response, WrongPasswordResponse):
                print('Illegal password. Try again')
            else:
                raise IllegalMessageException()

        open_gm_key, closed_gm_key = gm.generate_keys()
        send_gm_key_response = send_request(s, SendOpenKeyRequest(open_gm_key))
        check_ok_response(send_gm_key_response, 'SendOpenKeyRequest')

        session_key = gm.decrypt(request_session_key(s), closed_gm_key) 

        while True:
            operation = int(input(MENU_MESSAGE))

            if operation == 1:
                # refresh session key
                session_key = gm.decrypt(request_session_key(s), closed_gm_key) 
            elif operation == 2:
                # get file text
                file_name = input(ENTER_FILE_NAME_TEXT)
                file_text_response = send_request(s, GetFileTextRequest(file_name))
                if isinstance(file_text_response, SessionKeyExpiredResponse):
                    print()
                    print('Session key expired, regenerate it!')
                    print()
                elif not isinstance(file_text_response, GetFileTextResponse):
                    raise IllegalMessageException()
                else:
                    print()
                    print(idea.decrypt(file_text_response.encrypted_text, session_key, file_text_response.initialization_list))
                    print()
            elif operation == 3:
                file_names_response = send_request(s, GetFileNamesRequest())
                if not isinstance(file_names_response, GetFileNamesResponse):
                    raise IllegalMessageException()
                else:
                    print()
                    print(file_names_response.file_names)
                    print()
            elif operation == 4:
                break


if __name__ == '__main__':
    run_client()
