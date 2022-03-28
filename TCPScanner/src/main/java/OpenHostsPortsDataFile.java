import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class OpenHostsPortsDataFile {
    private final Set<Host> hosts;

    private OpenHostsPortsDataFile(Set<Host> hosts) {
        this.hosts = hosts;
    }

    public Set<Host> getHosts() {
        return hosts;
    }

    public static void writeToDataFile(Set<Host> hosts) {
        ObjectMapper mapper = new ObjectMapper();
        OpenHostsPortsDataFile dataFile = new OpenHostsPortsDataFile(hosts);
        try {
            mapper.writeValue(new File("C:/TCPScanner/src/main/resources/openHostsPorts.json"), dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
