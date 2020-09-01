public class DeleteCommand extends Command {
    private String command;

    DeleteCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasklist, UI ui) throws DukeDeleteException {
        try {
            int deleteTask = Integer.parseInt(command.split(" ")[1]) - 1;
            if (deleteTask + 1 > tasklist.numOfTasks() || deleteTask < 0) {
                throw new DukeDeleteException(command);
            }
            ui.printDeleteMessage(tasklist.getTask(deleteTask), tasklist.numOfTasks() - 1);
            tasklist.deleteTask(deleteTask);
        } catch (DukeDeleteException e) {
            ui.printFormattedMessage("OOPS!!! The invalid delete number.");
        }
    }
}
