public class DeleteCommand extends Command {
    private String command;

    DeleteCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeDeleteException {
        String message = "";
        try {
            int deleteTask = Integer.parseInt(command.split(" ")[1]) - 1;
            if (deleteTask + 1 > tasklist.numOfTasks() || deleteTask < 0) {
                throw new DukeDeleteException(command);
            }
            message = ui.printDeleteMessage(tasklist.getTask(deleteTask), tasklist.numOfTasks() - 1);
            tasklist.deleteTask(deleteTask);
        } catch (DukeDeleteException e) {
            message = e.getMessage();
        }
        return message;
    }
}
