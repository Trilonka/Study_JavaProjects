import java.util.HashSet;
import java.util.Set;

/**
 * Class - port representation
 */
public class Port {
    private Integer address;

    public Port(Integer address) {
        this.address = address;
    }

    public Integer getAddress() {
        return address;
    }

    public static Set<Port> parse(String ports) {
        String[] parsedPorts = ports.split(",");
        Set<Port> allPorts = new HashSet<>();
        int headAddress, tailAddress;
        for(String parsedAddress : parsedPorts) {
            if(parsedAddress.contains("-")) {
                headAddress = Integer.parseInt(parsedAddress.split("-")[0]);
                tailAddress = Integer.parseInt(parsedAddress.split("-")[1]);
                for(int address = headAddress; address <= tailAddress; address++) {
                    allPorts.add(new Port(address));
                }
            } else {
                allPorts.add(new Port(Integer.parseInt(parsedAddress)));
            }
        }
        return allPorts;
    }
}
