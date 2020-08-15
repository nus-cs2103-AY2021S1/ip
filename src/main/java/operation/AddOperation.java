package operation;

public class AddOperation extends Operation {
    public AddOperation(String operation) {
        super(operation);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute() {
        System.out.println("added: " + this.operation);
    }
}