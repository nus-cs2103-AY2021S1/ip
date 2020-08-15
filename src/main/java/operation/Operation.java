package operation;

public abstract class Operation {
    protected final String operation;

    public Operation(String operation) {
        this.operation = operation;
    }

    public abstract boolean isExit();

    public abstract void execute();
}