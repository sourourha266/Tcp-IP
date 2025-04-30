package week4;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class serverCityTemperatures {
    public static void main(String[] args) {
        int port = 20000;
        Map<String, Double> temperatures = new HashMap<>();
        try {
            ServerSocket server=new ServerSocket(port,1);
            System.out.println(InetAddress.getLocalHost());
            System.out.println("Waiting for clients");
            while(true) {
                Socket socket = server.accept();
                System.out.println("new Client " + socket.getRemoteSocketAddress());
                PrintStream outSocket = new PrintStream(socket.getOutputStream());
                Scanner inSocket = new Scanner(socket.getInputStream());


                if (inSocket.hasNextLine()){
                    String type = inSocket.nextLine();
                   if(type.equals("c")){
                    Thread consumer = new Thread(new consumerThread(socket,temperatures));
                    consumer.start();
                } else {
                    if (type.equals("p")) {
                        Thread producer = new Thread(new producerThread(socket, temperatures));
                        producer.start();
                    } else {
                        outSocket.println("Unknown client type.");
                        socket.close();
                    }
                }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
