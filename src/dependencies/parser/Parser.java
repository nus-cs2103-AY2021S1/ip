package dependencies.parser;

import dependencies.executor.Executor;

public class Parser {
    /** Object for executing the commands. */
    private static final Executor exec = Executor.initExecutor();

    /**
     * Private constructor for a Parser object.
     */
    private Parser() {}

    /**
     * Initializer for Parser.
     *
     * @return the Parser
     */
    public static Parser initParser() {
        return new Parser();
    }

    /**
     * Parses given command and determines if it is a valid command,
     * and calls an executor to execute a valid command.
     *
     * @param command
     */
    public void parseAndExec(String command) {

    }

    private void parse() {

    }

    private void exec() {

    };


}
