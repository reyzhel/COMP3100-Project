import java.io.*;
import java.net.*;

public class MyClient {
    static String AUTH_NAME = System.getProperty("user.name");

    public static void main(String[] args) {
        String response = "";
        try{
            Socket socket = new Socket("localhost", 50000);
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
            BufferedReader dataIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            dataOut.write("HELO\n".getBytes());
            response = dataIn.readLine();
            System.out.println(serverSaid(response));
            dataOut.write(("AUTH " + AUTH_NAME + "\n").getBytes());
            response = dataIn.readLine();
            System.out.println(serverSaid(response));
            dataOut.write("REDY\n".getBytes());
            response = dataIn.readLine();
            System.out.println(serverSaid(response));
            dataOut.write(("QUIT\n").getBytes());
            dataOut.flush();
            dataOut.close();
            socket.close();
        } catch (Exception e) {  
            System.out.println("FAILED");  
        }
    }

    public static String serverSaid(String serverSaid) {
        return "The server said: " + serverSaid;
    }
}

