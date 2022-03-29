import java.util.HashSet;
import java.util.Set;

/**
 * Class - host representation
 */
public class Host {
    private String hostName;
    private Set<Port> openPorts;

    public Host(String hostName) {
        this.hostName = hostName;
        openPorts = new HashSet<>();
    }

    public void addOpenPort(Port port) {
        openPorts.add(port);
    }

    public String getHostName() {
        return hostName;
    }

    public Set<Port> getOpenPorts() {
        return openPorts;
    }

    public static Set<Host> parse(String hosts) {
        String[] parsedHosts = hosts.split(",");
        Set<Host> allHosts = new HashSet<>();
        String[] splitHost;
        int headHostPart, tailHostPart;
        StringBuilder prefixPort = new StringBuilder();
        StringBuilder postfixPort = new StringBuilder();
        for(String parsedHost : parsedHosts) {
            if(parsedHost.contains("-")) {
                splitHost = parsedHost.split("\\.");
                for(int hostSegmentIndex = 0; hostSegmentIndex < 4; hostSegmentIndex++) {
                    if(splitHost[hostSegmentIndex].contains("-")) {
                        headHostPart = Integer.parseInt(splitHost[hostSegmentIndex].split("-")[0]);
                        tailHostPart = Integer.parseInt(splitHost[hostSegmentIndex].split("-")[1]);
                        for(int prefixPartIndex = 0; prefixPartIndex < hostSegmentIndex; prefixPartIndex++) {
                            prefixPort.append(splitHost[prefixPartIndex]).append(".");
                        }
                        for(int postfixPartIndex = hostSegmentIndex+1; postfixPartIndex < 4; postfixPartIndex++) {
                            postfixPort.append(".").append(splitHost[postfixPartIndex]);
                        }
                        for(int hostPart = headHostPart; hostPart<=tailHostPart; hostPart++) {
                            allHosts.add(new Host(prefixPort.toString()+hostPart+postfixPort));
                        }
                        break;
                    }
                }
            } else {
                allHosts.add(new Host(parsedHost));
            }
        }
        return allHosts;
    }
}
