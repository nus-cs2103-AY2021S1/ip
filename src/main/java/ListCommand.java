public class ListCommand extends Command {

    private String command;

    public ListCommand(String command) {
        this.command = command;
    }

    @Override
    protected void execute(TaskList tasks, UI dukeUI) throws InvalidCommandException {
        this.checkCommandValidity();
        if (tasks.getTaskList().isEmpty()) {
            System.out.println("\nThere are currently no tasks stored!\n");
        } else {
            dukeUI.displayTasks(tasks);
        }
    }

    // Method to check whether the list command is valid
    protected void checkCommandValidity() throws InvalidCommandException{
        try {
            if (this.command.compareTo("list") != 0) {
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
            throw new InvalidCommandException();
        }
    }

    protected boolean isExit() {
        return false;
    }
}
