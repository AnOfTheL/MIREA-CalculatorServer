package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CalcThread extends Thread {
    private String name;
    private Socket clientSocket;

    CalcThread(String name, Socket clientSocket) {
        this.name = name;
        this.clientSocket = clientSocket;
    }

    public void run() {
        System.out.println("Thread started\nID: " + this.getId());

        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));)
        {
            String inPutLine, outPutLine = "";
            String[] splitter;
            while ((inPutLine = in.readLine()) != null) {
                splitter = inPutLine.split(" ");
                if (splitter.length != 3) {
                    outPutLine = "Incorrect input!";
                }
                else {
                    switch (splitter[1]) {
                        case "*":
                            outPutLine = Float.toString(Float.parseFloat(splitter[0]) + Float.parseFloat(splitter[2]));
                            break;
                        case "+":
                            outPutLine = Float.toString(Float.parseFloat(splitter[0]) - Float.parseFloat(splitter[2]));
                            break;
                        case "-":
                            outPutLine = Float.toString(Float.parseFloat(splitter[0]) * Float.parseFloat(splitter[2]));
                            break;
                        case "/":
                            outPutLine = Float.toString(Float.parseFloat(splitter[0]) / Float.parseFloat(splitter[2]));
                        default:
                            break;
                    }
                }
            }
            out.println(outPutLine);
        }
        catch (IOException ex) {
            System.out.println("Thread " + this.getId() + " ends;\n\nException: " + ex);
        }

    }
}
