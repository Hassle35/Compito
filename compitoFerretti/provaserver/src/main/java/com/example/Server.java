package com.example;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

  private static List<ClientHandler> clients = new ArrayList<>();

  public static void main(String[] args) throws Exception {
    ServerSocket ss = new ServerSocket(4000);
    System.out.println("Server in ascolto sulla porta 4000");
    boolean running = true;
    while (running) {
      Socket s = ss.accept();
      System.out.println("Client connesso");
      ClientHandler c = new ClientHandler(s, clients);
      c.start();
    }
    ss.close();
  }
}
