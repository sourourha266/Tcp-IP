package week4;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class producerThread implements Runnable {
    private Socket socket;
    private Map<String, Double> temperatures;
    public producerThread(Socket socket, Map<String, Double> temperatures) {
        this.socket = socket;
        this.temperatures = temperatures;
    }

    @Override
    public void run() {
        try {
            Scanner inSocket = new Scanner(socket.getInputStream());
            PrintStream outSocket = new PrintStream(socket.getOutputStream());
            while (true){
                if (inSocket.hasNextLine()) {
                    String city=  inSocket.nextLine();
                    if(city.equals("."))
                        break;
                    String temp= inSocket.nextLine();
                    try {
                        double tempi = Double.parseDouble(temp);
                        if (tempi >= -50 && tempi <= 60) {
                            synchronized(this.temperatures) {
                                this.temperatures.put(city, tempi);
                            }
                            outSocket.println("Temperature for " + city + " updated to " + tempi + "°C");
                        } else {
                            outSocket.println("Temperature is not normal. Please enter between -50 and 60.");
                        }
                    } catch (NumberFormatException e) {
                        outSocket.println("Invalid temperature format: " + temp);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

