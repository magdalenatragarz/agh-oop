import java.io.*;
import java.net.Socket;

public class HelloThread extends Thread {

    private Socket clientSocket;
    private boolean shouldRun = true;

    HelloThread(Socket socket){
        this.clientSocket = socket;
    }

    public void run() {
        //System.out.println("runs");
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(in.readLine());
            String inputLine;
            while(shouldRun) {
                inputLine = in.readLine();
                if (inputLine.trim().equals("\n")){
                    break;
                }
                out.println(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                out.close();
                in.close();
            }catch(Exception e){}
        }
    }
}