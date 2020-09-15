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
        int taskIndex;
        String dukeResponse = "";
        try {
            String[] updateCommand = userCommand.split(" ", 3);
            taskIndex = Integer.parseInt(updateCommand[1]);
            if (taskIndex > 0 && taskIndex <= tasks.getTaskList().size()) {
                Task updatingTask = tasks.getTaskList().get(taskIndex - 1);
                String detailsToUpdate = updateCommand[2];
                Task updatedTask = tasks.updateTask(updatingTask, detailsToUpdate, taskIndex);
                dukeResponse = ui.updateTask(updatingTask, updatedTask);
            } else {
                throw new InvalidTaskNumberException();
            }
            return dukeResponse;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }
}
