package com.company;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        int threadCounter = 0;
        int portNumber = Integer.parseInt(args[0]);

        while (true){
            try (ServerSocket serverSocket = new ServerSocket(portNumber)){
                new CalcThread(Integer.toString(threadCounter), serverSocket.accept()).start();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            threadCounter++;
        }
    }
}
