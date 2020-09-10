package duke.commands;

/**
 * Represents a {@code CommandOutput} object which stores the command output from a command execution and also a
 * boolean value to indicate to the system whether the command requires the program to terminate.
 */
public class CommandOutput {
    private String commandOutput;
    private boolean isExit;

    /**
     * Constructor
     *
     * @param commandOutput The string that will be outputted as a result of the respective command
     * @param isExit Indicate whether or not the the program needs to exit
     */
    public CommandOutput(String commandOutput, boolean isExit) {
        this.commandOutput = commandOutput;
        this.isExit = isExit;
    }

    /**
     * Returns a string representation of the output from a command execution.
     *
     * @return the string representation of the output from a command execution
     */
    public String getCommandOutput() {
        return this.commandOutput;
    }

    /**
     * Returns a boolean value to indicate to the program whether or not it should terminate
     *
     * @return a boolean value to indicate to the program whether or not it should terminate
     */
    public boolean isExit() {
        return this.isExit;
    }

}
