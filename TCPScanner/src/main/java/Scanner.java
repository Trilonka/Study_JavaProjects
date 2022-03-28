import java.io.IOException;

import java.net.*;
import java.util.HashSet;
import java.util.Set;

public class Scanner extends Thread {
    private final Set<String> hosts;
    private final Set<Integer> ports;

    public Scanner(Set<Integer> ports) {
        hosts = new HashSet<>();
        this.ports = ports;
    }

    public void addHost(String host) {
        hosts.add(host);
    }

    @Override
    public void run() {
        for(String host : hosts) {
            for(int port : ports) {
                try {
                    InetAddress address = InetAddress.getByName(host);
                    SocketAddress socketAddress;
                    socketAddress = new InetSocketAddress(address, port);
                    Socket socket = new Socket();
                    socket.connect(socketAddress, 10);
                    System.out.println("Connection success with host: " + host + ", port: " + port);
                } catch(UnknownHostException e) {
                    System.out.println("Unknown host: " + host);
                    e.printStackTrace();
                    // TODO: Ошибочные hosts также записывать в файл
                } catch(IOException ignore) {
                    // TODO: Неудачные соединения записывать в JSON как 'close ports'
                }
            }
        }
    }
}