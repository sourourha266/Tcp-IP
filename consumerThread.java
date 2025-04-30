package week4;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class consumerThread implements Runnable {
    private Socket socket;
    private Map<String, Double> temperatures;
    public consumerThread(Socket socket, Map<String, Double> temperatures) {
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
                    if(city.equals("avg")){
                        if (temperatures.isEmpty())  outSocket.println("0");
                        double sum = 0;
                        for (double temp : temperatures.values()) {
                            sum += temp;
                        }
                        double avg = sum / temperatures.size();
                        outSocket.println("Average:" + avg);
                    }
                    else {
                        if (city.equals("list")){
                            outSocket.println("list:" + temperatures.toString());}
                        else
                            {   Double temp = temperatures.get(city);
                                if (temp == null) {
                                    outSocket.println("City not found");
                                } else {
                                    outSocket.println("Temperature of" + city + ": " + temp + "°C");
                                }
                            }


                    }

                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
