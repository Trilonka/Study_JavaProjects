import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * Class for representing data about hosts, used to write data to a json file
 */
public class OpenHostsPortsDataFile {
    private Set<Host> hosts;

    private OpenHostsPortsDataFile(Set<Host> hosts) {
        this.hosts = hosts;
    }

    public Set<Host> getHosts() {
        return hosts;
    }

    public static void writeToDataFile(Set<Host> hosts, String dataFilePath) {
        ObjectMapper mapper = new ObjectMapper();
        OpenHostsPortsDataFile dataFile = new OpenHostsPortsDataFile(hosts);
        try {
            mapper.writeValue(new File(dataFilePath), dataFile);
        } catch (IOException e) {
            System.out.println("Wrong dataFilePath. No data was written.");
            e.printStackTrace();
        }
    }
}
