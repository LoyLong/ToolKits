package org.loy.multithread.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SelectSockets {

    public static void main(String[] args) {
        try {
            new SelectSockets().go();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void go() throws Exception {
        int port = 1234;
        System.out.println("Listening on port " + port);

        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverChannel.socket();
        Selector selector = Selector.open();

        serverSocket.bind(new InetSocketAddress(port));
        serverChannel.configureBlocking(false);

        // register ServerSocketChannel to Selector
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            // This may block for a long time. Upon returning, the
            // selected set contains keys of the ready channels.
            int n = selector.select();
            if (n == 0) {
                // no available request, nothing to do
                continue;
            }

            Iterator<SelectionKey> selectedKey = selector.selectedKeys().iterator();
            while (selectedKey.hasNext()) {
                SelectionKey key = selectedKey.next();
                // channel setup!
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    // set to non-blocking
                    channel.configureBlocking(false);
                    // register
                    channel.register(selector, SelectionKey.OP_CONNECT);
                    channel.register(selector, SelectionKey.OP_READ);
                    channel.register(selector, SelectionKey.OP_WRITE);

                    sayHello(channel);
                }

                // if this channel has data to read
                if (key.isReadable()) {
                    readDataFromSocket(key);
                }

                // remove the processed selectionkey
                selectedKey.remove();
            }
        }
    }

    // ----------------------------------------------------------
    // Use the same byte buffer for all channels. A single thread is
    // servicing all the channels, so no danger of concurrent acccess.
    private final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

    /**
     * Sample data handler method for a channel with data ready to read.
     * 
     * @param key
     *            A SelectionKey object associated with a channel determined by
     *            the selector to be ready for reading. If the channel returns
     *            an EOF condition, it is closed here, which automatically
     *            invalidates the associated key. The selector will then
     *            de-register the channel on the next select call.
     */
    protected void readDataFromSocket(SelectionKey key) throws Exception {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        buffer.clear();
        // Loop while data is available; channel is nonblocking
        while ((count = socketChannel.read(buffer)) > 0) {
            // set buffer to readable
            buffer.flip();
            // Send the data; don't assume it goes all at once
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
            // WARNING: the above loop is evil. Because
            // it's writing back to the same nonblocking
            // channel it read the data from, this code can
            // potentially spin in a busy loop. In real life
            // you'd do something more useful than this.
            buffer.clear(); // Empty buffer
        }

        if (count < 0) {
            // Close channel on EOF, invalidates the key
            socketChannel.close();
        }
    }

    // ----------------------------------------------------------
    /**
     * Spew a greeting to the incoming client connection.
     *
     * @param channel
     *            The newly connected SocketChannel to say hello to.
     */
    private void sayHello(SocketChannel channel) throws Exception {
        buffer.clear();
        buffer.put("Hi there!\r\n".getBytes());
        buffer.flip();
        channel.write(buffer);
    }

}
