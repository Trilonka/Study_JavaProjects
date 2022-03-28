package zoo;

import com.beust.jcommander.Parameter;
import lombok.Getter;

@Getter
public class ConfigArgs {

    @Parameter(names = "-configfile")
    private String configFile;

    @Parameter(names = "-configtype")
    private String configType;
}
