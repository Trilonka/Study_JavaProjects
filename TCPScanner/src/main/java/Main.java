import org.apache.commons.cli.CommandLine;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        CommandLine parsedArgs = ArgsParser.parseArgs(args);
        String hosts = parsedArgs.getOptionValue("h");
        String ports = parsedArgs.getOptionValue("p");
        int scannersCount = 1;
        if(parsedArgs.hasOption("t")) {
            scannersCount = Integer.parseInt(parsedArgs.getOptionValue("t"));
        }

        Set<String> parsedHosts = HostsAndPortsScanner.parseHosts(hosts);
        Set<Integer> parsedPorts = HostsAndPortsScanner.parsePorts(ports);

        scannersCount = Math.min(parsedHosts.size(), scannersCount);
        Scanner[] scanners = new Scanner[scannersCount];
        for(int scannerIndex = 0; scannerIndex<scannersCount; scannerIndex++) {
            scanners[scannerIndex] = new Scanner(parsedPorts);
        }
        int scannerIndex = 0;
        for(String host : parsedHosts) {
            scanners[scannerIndex%scannersCount].addHost(host);
            scannerIndex++;
        }
        for(Scanner scanner : scanners) {
            scanner.start();
        }
    }
}