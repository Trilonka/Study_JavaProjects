import java.util.HashSet;
import java.util.Set;

public class HostsAndPortsParser {
    /*public static Set<Host> parseHosts(String hosts) {
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

    public static Set<Integer> parsePorts(String ports) {
        String[] parsedPorts = ports.split(",");
        Set<Integer> allPorts = new HashSet<>();
        int headPort, tailPort;
        for(String parsedPort : parsedPorts) {
            if(parsedPort.contains("-")) {
                headPort = Integer.parseInt(parsedPort.split("-")[0]);
                tailPort = Integer.parseInt(parsedPort.split("-")[1]);
                for(int port = headPort; port <= tailPort; port++) {
                    allPorts.add(port);
                }
            } else {
                allPorts.add(Integer.parseInt(parsedPort));
            }
        }
        return allPorts;
    }*/
}
