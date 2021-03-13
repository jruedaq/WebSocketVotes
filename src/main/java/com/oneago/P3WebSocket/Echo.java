package com.oneago.P3WebSocket;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
@ServerEndpoint("/echo")
public class Echo {
    private static Set<Session> connectedClients = new HashSet<>();

    private int anaVotes;
    private int alejandroVotes;
    private int antoniaVotes;
    private int alexandraVotes;

    @OnClose
    public void close(Session session) {
        System.out.println("Disconnecting client...");
        connectedClients.remove(session);
    }

    @OnError
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
        throwable.printStackTrace();
    }

    @OnOpen
    public void open(Session session) {
        System.out.println("Connecting client...");
        connectedClients.add(session);
        try {
            session.getBasicRemote().sendText(this.anaVotes + "," + this.alejandroVotes + "," + this.antoniaVotes + "," + this.alexandraVotes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void sendMessage(String message, Session session) { // En el momento que se envía el mensaje
        System.out.println("Entry message > " + message);

        switch (Integer.parseInt(message)) {
            case 0:
                this.anaVotes++;
                break;
            case 1:
                this.alejandroVotes++;
                break;
            case 2:
                this.antoniaVotes++;
                break;
            case 3:
                this.alexandraVotes++;
                break;
        }
        try {
            for (Session i : connectedClients) {
                if (!i.getId().equals(session.getId())) {
                    i.getBasicRemote().sendText(this.anaVotes + "," + this.alejandroVotes + "," + this.antoniaVotes + "," + this.alexandraVotes);
                }
            }
        } catch (IOException e) { // En caso de error
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
