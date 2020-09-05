public class UpdateCommand extends Command {

    private String userCommand;

    public UpdateCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    @Override
    String execute(TaskList tasks, UI ui) throws DukeException {
        try {
            String[] parsedCommand = userCommand.split(" ");
            int taskNumber = Integer.parseInt(parsedCommand[1]);
            Task updatingTask = tasks.updateTask(taskNumber);
            String dukeResponse = ui.enterNewUpdateForTask(updatingTask);
            return dukeResponse;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }
}
