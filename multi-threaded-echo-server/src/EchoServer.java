import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {

    private static boolean shouldRun = true;


    public static void  main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 6666");
            System.exit(-1);
        }
        Socket clientSocket = null;
        try{
            while(shouldRun) {
                clientSocket = serverSocket.accept();
                HelloThread connection = new HelloThread(clientSocket);
                executor.execute(connection);
            }
        } catch (IOException e) {
            System.out.println("Accept failed: 6666");
            System.exit(-1);
        }

        clientSocket.close();
        serverSocket.close();
    }
}