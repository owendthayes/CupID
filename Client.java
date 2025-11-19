package CupID;

import java.awt.GridLayout;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Client {
    private Socket socket;
    private DataOutputStream out;
    private String in;
    
    public Client() {
        try{
            socket = new Socket("127.0.0.1", Server.PORT);
            out = new DataOutputStream(socket.getOutputStream());
            //in = new Scanner(System.in);
            //writeMessages();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //private void writeMessages() throws IOException {
    //    String line = "";
    //    while(!line.equals(Server.STOP_STRING)) {
    //        //line = in.nextLine();
    //        out.writeUTF(line);
    //    }
    //    close();
    //}

    private void close() throws IOException {
        socket.close();
        out.close();
        //in.close();
    }

    public void main(String[] args) {
        JFrame frame = new JFrame("Coffee Selector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new GridLayout(6, 1));

        String[] coffees = {"Latte", "Espresso", "Cappuccino", "Mocha", "Americano", "Flat White"};

        for (String coffee : coffees) {
            JButton btn = new JButton(coffee);
            btn.addActionListener(e -> sendCoffee(coffee));
            frame.add(btn);
        }
        frame.setVisible(true);
    }

    public void sendCoffee(String coffeeName)
    {
        try {
            out.writeUTF(coffeeName);
            out.flush();
            System.out.println("Sent coffee: " + coffeeName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}