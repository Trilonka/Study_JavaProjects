import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.io.IOException;


public class PortScanWorker implements Runnable {

    static int globalId = 1;

    private int id;
    private List<Integer> ports;
    private List<Integer> openPorts;
    private InetAddress inetAddress;
    private int timeout = 200;
    CyclicBarrier barrier;

    public PortScanWorker() {
        id = globalId++;
    }

    public int getId() {
        return id;
    }

    public void setBarrier(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public List<Integer> getOpenPorts() {
        return openPorts;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public void setPorts(List<Integer> ports) {
        this.ports = ports;
    }

    public void run() {
        //System.out.println("Started thread with id = " + id);
        scan(inetAddress);
        try {
            barrier.await();
        } catch (InterruptedException ex) {
            return;
        } catch (BrokenBarrierException ex) {
            return;
        }
    }

    void scan(InetAddress inetAddress) {
        openPorts = new ArrayList<Integer>();
        //System.out.println("scanning ports: ");
        for (Integer port : ports) {
            //System.out.print(port);
            try {
                InetSocketAddress isa = new InetSocketAddress(inetAddress,port);
                Socket socket = new Socket();
                socket.connect(isa,timeout);
                System.out.println("Found opened port: " + port);
                openPorts.add(port);
                socket.close();
            } catch (IOException ioe) {
                //System.out.println("");
            }
        }
        //System.out.println("FINISH, id = " + id);
    }
}