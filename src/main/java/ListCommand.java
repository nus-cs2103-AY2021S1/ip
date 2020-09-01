public class ListCommand extends Command {
    private String command;

    ListCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeEmptyTaskListException {
        String message = "";
        try {
            if (tasklist.numOfTasks() < 1) {
                throw new DukeEmptyTaskListException(command);
            }
            message = ui.printListOfTasks(tasklist.getTasks());
        } catch (DukeEmptyTaskListException e) {
            message = e.getMessage();
        }
        return message;
    }
}
