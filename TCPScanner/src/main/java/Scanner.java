import java.io.IOException;

import java.net.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Scans open ports on the given list of hosts
 */
public class Scanner extends Thread {
    private Set<Host> hosts;
    private Set<Port> ports;

    public Scanner(Set<Port> ports) {
        hosts = new HashSet<>();
        this.ports = ports;
    }

    public void addHost(Host host) {
        hosts.add(host);
    }

    @Override
    public void run() {
        for(Host host : hosts) {
            for(Port port : ports) {
                try {
                    InetAddress address = InetAddress.getByName(host.getHostName());
                    SocketAddress socketAddress;
                    socketAddress = new InetSocketAddress(address, port.getAddress());
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