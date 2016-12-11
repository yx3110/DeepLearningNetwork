import DL.NeuralNetwork;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang Xu on 06/12/2016.
 */
public class Server{
    static final boolean isTraining = true;
    private static NeuralNetwork nn = new NeuralNetwork(isTraining);
    public static void main(String args[]){
        startServer();
    }
    public static void startServer() {

            ServerSocket ss;
            try {
                ss = new ServerSocket(60010);
                while(true) {
                    Socket s = ss.accept();
                    ObjectInputStream in = new ObjectInputStream(s.getInputStream());
                    List<List<Double>> valMatrix = (List<List<Double>>) in.readObject();

                    Double score = nn.evaluate(valMatrix);

                    Socket reply = new Socket("localhost",60011);
                    ObjectOutputStream oos = new ObjectOutputStream(reply.getOutputStream());
                    oos.writeObject(score);
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

    }
}
