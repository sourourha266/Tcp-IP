package week4;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ConsumerClients {
    public static void main(String[] args) {
        int port = 20000;
        Scanner key = new Scanner(System.in);
        System.out.println("Connect to:");
        
        String host = key.nextLine();
        try {
            Socket socket=new Socket(host,port);
            PrintStream outSocket=new PrintStream(socket.getOutputStream());
            Scanner inSocket=new Scanner(socket.getInputStream());
            outSocket.println("c");
            while (true){
                String question=key.nextLine();
                outSocket.println(question);

                if(question.equals("."))
                    break;
                System.out.println(inSocket.nextLine());
            }
            try {
                Thread.sleep(2000);
                inSocket.close();
                outSocket.close();
                socket.close();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
