package web.sem2.lab8.websockets;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

@ServerEndpoint(value = "/chat/{login}", encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class})
public class ChatEndpoint {
    private Session session;
    private static final HashMap<String, ChatEndpoint> CHAT_ENDPOINTS = new HashMap<>();
    private static final LinkedList<Message> LAST_MESSAGES = new LinkedList<>();
    private static final int MAX_LAST_MESSAGES = 20;
    private static final HashMap<String, String> USERS = new HashMap<>();

    private static void broadcastMessage(Message message) throws IOException {
        synchronized (LAST_MESSAGES) {
            if (LAST_MESSAGES.size() == MAX_LAST_MESSAGES) {
                LAST_MESSAGES.removeFirst();
            }
            LAST_MESSAGES.addLast(message);
        }
        synchronized (CHAT_ENDPOINTS) {
            for (ChatEndpoint chatEndpoint : CHAT_ENDPOINTS.values()) {
                try {
                    chatEndpoint.session.getBasicRemote().sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendLastMessages() {
        synchronized (LAST_MESSAGES) {
            for (Message message : LAST_MESSAGES) {
                try {
                    session.getBasicRemote().sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnOpen
    public void onOpen(Session session,
                       @PathParam("login")
                       String login) throws IOException {
        this.session = session;
        synchronized (CHAT_ENDPOINTS) {
            CHAT_ENDPOINTS.put(session.getId(), this);
        }
        USERS.put(session.getId(), login);

        sendLastMessages();

        Message message = new Message();
        message.setSender(login);
        message.setContents("\uD83D\uDC49 " + login);
        message.setSendDateTime(LocalDateTime.now());
        broadcastMessage(message);
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException {
        message.setSendDateTime(LocalDateTime.now());
        broadcastMessage(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        synchronized (CHAT_ENDPOINTS) {
            CHAT_ENDPOINTS.remove(session.getId());
        }
        Message message = new Message();
        message.setSender(USERS.get(session.getId()));
        message.setContents("\uD83D\uDC48 " + USERS.get(session.getId()));
        message.setSendDateTime(LocalDateTime.now());
        broadcastMessage(message);
        USERS.remove(session.getId());
    }
}
