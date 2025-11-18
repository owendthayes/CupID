package CupID;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;
    private DataInputStream in;

    //can change the port here.
    public static final int PORT = 3030;

    //specifies that client has stopped communication with server.
    public static final String STOP_STRING = "##";

    
    public Server(){
        try{
            server = new ServerSocket(PORT);
            initConnections();
        }   catch (IOException e) {
            e.printStackTrace();
        }
    }

    //get client socket
    private void initConnections() throws IOException{
        Socket clientSocket = server.accept();

        //initialise input stream
        in = new DataInputStream(new BufferedInputStream((clientSocket.getInputStream())));
        readMessages();
        close();
    }

    private void close() throws IOException{
        in.close();
        server.close();
    }

    //read from input stream
    private void readMessages() throws IOException{
        String line = "";
        while(!line.equals(STOP_STRING))
        {
            line = in.readUTF();
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}