import org.apache.commons.cli.CommandLine;

import java.util.Set;

/**
 * Main class, entrance to the program
 * Receives data from the command line,
 * distributes hosts to open port scanners for hosts.
 * After the completion of all scanners, writes the result to a json file
 */
public class Main {
    public static void main(String[] args) {
        CommandLine parsedArgs = ArgsParser.parseArgs(args);
        String hosts = parsedArgs.getOptionValue("h");
        String ports = parsedArgs.getOptionValue("p");
        int scannersCount;

        Set<Host> parsedHosts = Host.parse(hosts);
        Set<Port> parsedPorts = Port.parse(ports);

        if(parsedArgs.hasOption("t")) {
            scannersCount = Integer.parseInt(parsedArgs.getOptionValue("t"));
        } else {
            scannersCount = parsedHosts.size();
        }

        scannersCount = Math.min(parsedHosts.size(), scannersCount);
        Scanner[] scanners = new Scanner[scannersCount];
        for(int scannerIndex = 0; scannerIndex<scannersCount; scannerIndex++) {
            scanners[scannerIndex] = new Scanner(parsedPorts);
        }
        int scannerIndex = 0;
        for(Host host : parsedHosts) {
            scanners[scannerIndex%scannersCount].addHost(host);
            scannerIndex++;
        }
        for(Scanner scanner : scanners) {
            scanner.start();
        }
        for(Scanner scanner : scanners) {
            try {
                scanner.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String dataFilePath = "src/main/resources/openHostsPorts.json";
        OpenHostsPortsDataFile.writeToDataFile(parsedHosts, dataFilePath);
    }
}