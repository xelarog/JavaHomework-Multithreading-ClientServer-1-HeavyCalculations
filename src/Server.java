import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(25000);

        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                String line;
                while ((line = in.readLine()) != null) {

                    if (line.equals("end"))
                        break;

                    try {
                        int numberFibo = Integer.parseInt(line);
                        if (numberFibo >= 0) {
                            long result = calculateTermFiboSeries(numberFibo);
                            System.out.println(result);
                            out.println(result);
                        } else {
                            out.println("Вы ввели отрицательное число");
                        }
                    } catch (NumberFormatException e) {
                        out.println("Ошибка ввода");
                    }


                }

            } catch (IOException e) {
                e.printStackTrace(System.out);
            }

        }
    }

    private static long calculateTermFiboSeries(int numberFibo) {
        if (numberFibo == 0)
            return 0;
        else if (numberFibo == 1)
            return 1;

        return calculateTermFiboSeries(numberFibo - 1) + calculateTermFiboSeries(numberFibo - 2);
    }
}
