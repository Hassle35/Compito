package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    //private static int contatore = 0;
    //private int id;
    private Socket s;
    private int Biglietti = 10;
    private List<ClientHandler> clients;
    private PrintWriter out = null;
    private BufferedReader in = null;

    public ClientHandler(Socket s, List<ClientHandler> clients) {
        //contatore++;
        //this.id = contatore;
        this.s = s;
        this.clients = clients;
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String request, nomeClient = "";

        boolean running = true;
        while (running) {
            try {
                request = in.readLine();
                //DISPONIBILITÀ
                if (request.equalsIgnoreCase("d")) {
                    String dRes = "Disponibili " + Biglietti + " biglietti";
                    out.println(dRes);
                    //ACQUISAT
                } else if (request.equalsIgnoreCase("a")) {
                    if(Biglietti > 0){
                        String aRes = "Biglietto acquistato";
                        Biglietti--;
                        out.println(aRes);
                    }else{
                        String dRes = "Biglietti esauriti";
                        out.println(dRes);
                        running = false;
                    }
                    //DISCONESSIONE
                } else if (request.startsWith("q")) {
                    out.println("Connessione chiusa");
                } else
                    out.println("Comando non valido");
            } catch (IOException e) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
                out.close();
            }
        }

    }

    
/*
    private void sendToAll(String aa) {
        for (ClientHandler client : clients) {
            client.out.println(aa);
        }
    }
*/
}
