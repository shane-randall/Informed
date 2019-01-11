package demo.nio2.asynchronous_channels;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AsynchronousSocketChannelExample {

	private static InetSocketAddress serverAddress = new InetSocketAddress("localhost", 24680);
	
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
    	
        // Open a server channel and bind to a free address, then accept a connection.
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open().bind(serverAddress);
        System.out.println("Server has opened channel.");

        Future<AsynchronousSocketChannel> future = server.accept();
        System.out.println("Server has initiated accept(), so it's (asynchronously) waiting to accept a connection.");
        
        // Now create a client (see the Client class below).
        Client client = new Client(server.getLocalAddress());
        
        // Wait for the accept to finish.
        AsynchronousSocketChannel worker = future.get();
        System.out.println("Server's accept() has completed.");
        
        // Start the client thread.
        client.start();
        ByteBuffer readBuffer = ByteBuffer.allocate(100);
        try {
            // Read a message from the client, timeout after 10 seconds.
            worker.read(readBuffer).get(10, TimeUnit.SECONDS);
            System.out.println("Server received from message from client: " + new String(readBuffer.array()));
        } 
        catch (TimeoutException e) {
            System.out.println("Server timed out, because the client didn't respond in time.");
        }
        
        // Wait for client thread to fin
        client.join();
        
        // Then close client-side and server-side sockets.
        client.close();
        server.close();
    }
}


class Client extends Thread {
    
	AsynchronousSocketChannel client;
    Future<Void> connectFuture;
    
    public Client(SocketAddress server) throws IOException {

    	// Open a new socket channel.
        client = AsynchronousSocketChannel.open();
        System.out.println("Client opened socket channel.");

    	// Open a new socket channel and connect to the server.
        connectFuture = client.connect(server);
        System.out.println("Client connected to server.");
    }
    
    public void run() {
        
    	// If the connect hasn't happened yet, cancel it.
        if (!connectFuture.isDone()) {
            connectFuture.cancel(true);
            return;
        }
        
        try {
            // Send a message to the server.
            ByteBuffer message = ByteBuffer.wrap("Super Swans".getBytes());
            
            // Wait for the response.
            System.out.println("Client sending message to the server...");
            int numberBytes = client.write(message).get();
            System.out.println("Client wrote " + numberBytes + " bytes to the server.");
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        } 
        catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    
    public void close() throws IOException {
        client.close();
    }
}
