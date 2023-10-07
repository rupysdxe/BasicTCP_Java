/**
 * @author Rupesh Dangi
 */

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer {
    private static final Logger LOGGER = Logger.getLogger(TCPServer.class.getName());
    private static final int PORT = 8080;
    private static final Executor THREAD_POOL = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            LOGGER.info("Server is listening on port: " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                THREAD_POOL.execute(() -> processClientConnection(clientSocket));
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Server Exception", e);
        }
    }

    private static void processClientConnection(Socket clientSocket) {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(clientSocket.getOutputStream())) {
            LOGGER.info("Client connected from port " + clientSocket.getPort());
            String content = "Hello World";
            String httpResponse = "HTTP/1.1 200 OK\r\n" +
                    "Content-Length: " + content.length() + "\r\n" +
                    "Content-Type: text/plain\r\n" +
                    "\r\n" +
                    content;
            outputStream.write(httpResponse.getBytes());
            outputStream.flush();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error processing client connection", e);
        } finally {
            try {
                if (!clientSocket.isClosed()) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error closing client socket", e);
            }
        }
    }
}
