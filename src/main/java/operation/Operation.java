package operation;

public abstract class Operation {
    protected final String[] commands;

    public Operation(String[] commands) {
        this.commands = commands;
    }

    public abstract boolean isExit();

    public abstract void execute();
}