package duke;

public class Command {
    public static final int EXIT = 0;
    public static final int LIST = 1;
    public static final int DONE = 2;
    public static final int DELETE = 3;
    public static final int INVALID = 99;
    public static final int CREATE_TODO = 11;
    public static final int CREATE_DEADLINE = 12;
    public static final int CREATE_EVENT = 13;

    private int commandType;
    private AdditionalInfo info;

    public Command(int commandType, AdditionalInfo additionalInfo) {
        this.commandType = commandType;
        this.info = additionalInfo;
    }

    public Command(int commandType) {
        this.commandType = commandType;
    }

    /**
     * Getter method for commandType.
     * @return Type of command in integer form.
     */
    public int getCommandType() {
        return this.commandType;
    }

    /**
     * Getter method for additionalInfo.
     * @return AdditionalInfo .
     */
    public AdditionalInfo getAdditionalInfo() {
        return this.info;
    }
}
