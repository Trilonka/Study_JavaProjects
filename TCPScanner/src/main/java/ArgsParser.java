import org.apache.commons.cli.*;

import java.io.PrintWriter;

public class ArgsParser {
    public static CommandLine parseArgs(String[] args) {
        Option hostsOption = new Option("h",true, "Hosts");
        Option portsOption = new Option("p",true, "Ports");
        Option threadsCountOption = new Option("t",true, "ThreadsCount");

        setOptionParams(hostsOption, "hosts");
        setOptionParams(portsOption, "ports");
        setOptionParams(threadsCountOption, "threadsCount");
        threadsCountOption.setOptionalArg(true);

        Options options = new Options();
        options.addOption(hostsOption);
        options.addOption(portsOption);
        options.addOption(threadsCountOption);

        CommandLineParser cmdLineParser = new DefaultParser();
        CommandLine commandLine = null;
        try {
            commandLine = cmdLineParser.parse(options, args);
        } catch (ParseException e) {
            printHelp(options);
            System.exit(1);
        }
        return commandLine;
    }

    private static void setOptionParams(Option option, String argName) {
        option.setArgName(argName);
        option.setArgs(1);
    }

    private static void printHelp(Options options) {
        PrintWriter writer = new PrintWriter(System.out);
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp(
                writer,
                80,
                "TCPScanner",
                "Options",
                options,
                3,
                5,
                "-- HELP --",
                true);//формирование справки
        writer.flush();
        writer.close();
    }
}