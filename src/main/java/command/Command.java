package command;

public abstract class Command {
    private boolean exit;

    public Command(boolean exit){
        this.exit = exit;
    }
}
