package operation;

public class EchoOperation extends Operation {
    public EchoOperation(String operation) {
        super(operation);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute() {
        System.out.println(this.operation);
    }
}