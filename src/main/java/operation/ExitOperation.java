package operation;

public class ExitOperation extends Operation {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute() {
        System.out.println("Goodbye. Hope to see you again soon.");
    }
}