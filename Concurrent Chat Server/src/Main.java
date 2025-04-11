import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            WebServer server = new WebServer(serverSocket);
            System.out.println("Server started on port...");
            server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}