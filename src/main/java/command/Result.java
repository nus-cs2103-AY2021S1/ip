package command;

public class Result {
    private boolean isSuccessful;
    private String resultMessage;

    /**
     * Constructor for the result produced after executing a command
     * @param resultMessage the message that is received after executing the commands
     * @param isSuccessful a boolean value on whether the command has been executed successfully
     */
    public Result(String resultMessage, boolean isSuccessful) {
        this.resultMessage = resultMessage;
        this.isSuccessful = isSuccessful;
    }

    public boolean isSuccessful() {
        return this.isSuccessful;
    }

    @Override
    public String toString() {
        return this.resultMessage;
    }
}
