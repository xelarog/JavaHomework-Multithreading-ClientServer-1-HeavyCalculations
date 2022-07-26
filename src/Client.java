import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 25000);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            String msg;
            while(true) {
                System.out.println("Введите положительное целое число: ");
                msg = scanner.nextLine();
                out.println(msg);
                if(msg.equals("end"))
                    break;
                String response = in.readLine();
                try {
                    long result = Long.parseLong(response);
                    System.out.println("Server: " + result);

                } catch (NumberFormatException e) {
                    System.out.println(response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
