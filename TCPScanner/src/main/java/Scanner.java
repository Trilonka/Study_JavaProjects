import java.io.IOException;

import java.net.*;
import java.util.HashSet;
import java.util.Set;

public class Scanner extends Thread {
    private final Set<Host> hosts;
    private final Set<Integer> ports;

    public Scanner(Set<Integer> ports) {
        hosts = new HashSet<>();
        this.ports = ports;
    }

    public void addHost(Host host) {
        hosts.add(host);
    }

    @Override
    public void run() {
        for(Host host : hosts) {
            for(int port : ports) {
                try {
                    InetAddress address = InetAddress.getByName(host.getHostName());
                    SocketAddress socketAddress;
                    socketAddress = new InetSocketAddress(address, port);
                    Socket socket = new Socket();
                    socket.connect(socketAddress, 10);
                    host.addOpenPort(port);
                } catch(UnknownHostException e) {
                    System.out.println("Unknown host: " + host);
                    e.printStackTrace();
                } catch(IOException ignore) {
                }
            }
        }
    }
}