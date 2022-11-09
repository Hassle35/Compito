package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 4000);
        //ServerConnection serverConn = new ServerConnection(s);

        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        //new Thread(serverConn).start();

        System.out.println("per richiesta disponibilità inserire 'd' se si vuole acquista un biglietto inserire 'a' per terimnare la connessioen inserire 'q': ");

        while (true) {
            System.out.print("Inserisre un comando: ");
            String command = keyboard.readLine();

            if (command.equalsIgnoreCase("q"))
                break;

            out.println(command);
            String serverResponse = in.readLine();
            System.out.println("S: " + serverResponse);
        }
        in.close();
        out.close();
        s.close();
    }
}
