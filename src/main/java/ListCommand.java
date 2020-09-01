public class ListCommand extends Command {
    private String command;

    ListCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasklist, UI ui) throws DukeEmptyTaskListException {
        try {
            if (tasklist.numOfTasks() < 1) {
                throw new DukeEmptyTaskListException(command);
            }
            ui.printListOfTasks(tasklist.getTasks());
        } catch (DukeEmptyTaskListException e) {
            ui.printFormattedMessage("OOPS!!! There are no tasks entered!.");
        }
    }
}
