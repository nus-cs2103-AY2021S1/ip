/**
 * Class to handle update commmands entered by the user.
 */

public class UpdateCommand extends Command {

    private String userCommand;

    /**
     * Constructor for the update command.
     * @param userCommand
     */
    public UpdateCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Method to execute entirely when a todo command is entered by the user.
     * @param tasks TaskList containing all the tasks.
     * @param ui UI to print all string responses by the bot.
     * @return String response by the bot.
     * @throws DukeException
     */
    @Override
    String execute(TaskList tasks, UI ui) throws DukeException {
        try {
            String[] parsedCommand = userCommand.split(" ", 2);
            String dukeResponse = "";
            if (parsedCommand[1].matches("\\d+")) {
                int taskNumber = Integer.parseInt(parsedCommand[1]);
                Task updatingTask = tasks.getTaskToUpdate(taskNumber);
                updatingTask.toBeUpdated();
                dukeResponse = ui.enterNewUpdateForTask(updatingTask);
            } else {
                String detailToChange = parsedCommand[1];
                Task updatedTask = tasks.getUpdatedTask(detailToChange);
                tasks.updateTask(updatedTask);
                dukeResponse = ui.updatedTask(updatedTask);
            }
            return dukeResponse;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }
}
