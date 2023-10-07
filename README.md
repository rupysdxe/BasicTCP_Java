
# Documentation

`TCPServer` is a simple TCP server implemented in Java. It accepts client connections and responds with a basic "Hello World" message using the HTTP protocol.

## Features

- Utilizes a fixed thread pool to handle multiple client connections concurrently.
- Uses the standard Java logging framework for logging events and errors.
- Implements resource management to ensure proper closure of sockets and streams.

## Usage

1. Compile the `TCPServer.java` file:
   ```bash
   javac TCPServer.java
   ```

2. Run the compiled Java class:
   ```bash
   java TCPServer
   ```

3. The server starts and listens on port `8080`. You can connect to it using a web browser or tools like `curl`:
   ```bash
   curl http://localhost:8080
   ```

## Configuration

- **PORT**: The port on which the server listens. Currently set to `8080`.
- **THREAD_POOL**: A fixed-size thread pool executor used for handling client connections. The size is currently set to `5`.

## Methods

### main(String[] args)

The main method initializes the server socket and starts listening for client connections. For each client connection, it dispatches the processing task to the thread pool.

### processClientConnection(Socket clientSocket)

Handles the client connection. It writes a basic HTTP response with the "Hello World" message to the connected client.

- **Parameters**: 
  - `clientSocket`: The socket representing the client connection.
  
- **Behavior**: 
  - Writes an HTTP response with "Hello World" content.

## Error Handling

Errors and exceptions are logged using the standard Java logging framework. Specific catch blocks are provided for `IOException` and general exceptions to ensure meaningful error messages.

## Dependencies

- Java Standard Library

## Future Improvements

1. Allow dynamic configuration of server settings, such as port number and thread pool size.
2. Implement more advanced HTTP request handling, possibly integrating a lightweight HTTP server library.
3. Add security features like SSL/TLS support.

