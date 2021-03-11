package com.oneago.P3WebSocket;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
@ServerEndpoint("/echo")
public class Echo {
    private static Set<Session> connectedClients = new HashSet<>();

    @OnClose
    public void close(Session session) { // Cuando se cierra la conexión de un cliente
        System.out.println("Disconnecting client...");
        connectedClients.remove(session);
    }

    @OnError
    public void onError(Throwable throwable) { // En caso de error
        System.out.println(throwable.getMessage());
        throwable.printStackTrace();
    }

    @OnOpen
    public void open(Session session) { // Cuando se abre la conexión de un cliente
        System.out.println("Connecting client...");
        connectedClients.add(session);
        try {
            session.getBasicRemote().sendText("Connected!"); // Conexión correcta
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void sendMessage(String message, Session session) { // En el momento que se envía el mensaje
        System.out.println("Entry message > " + message);

        try { // Si no hay error entonces
            for (Session i : connectedClients) { // Por cada sesión almacenada en el array de la linea 13 lo llamare uno por uno como i
                if (!i.getId().equals(session.getId())) { // Si no soy yo entonces
                    i.getBasicRemote().sendText("Entry message > " + message); // mostrar mensaje
                }
            }
        } catch (IOException e) { // En caso de error
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
