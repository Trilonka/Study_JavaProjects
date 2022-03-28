import java.util.HashSet;
import java.util.Set;

public class Host {
    private final String hostName;
    private final Set<Integer> openPorts;

    public Host(String hostName) {
        this.hostName = hostName;
        openPorts = new HashSet<>();
    }

    public void addOpenPort(Integer port) {
        openPorts.add(port);
    }

    public String getHostName() {
        return hostName;
    }

    public Set<Integer> getOpenPorts() {
        return openPorts;
    }
}
