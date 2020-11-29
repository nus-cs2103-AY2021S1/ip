/**
 * Class to handle commands to add a Todo to the bot's list.
 * @author vanGoghhh
 */

public class TodoCommand extends Command{

    private String command;

    /**
     * Constructor for a todocommand object.
     * @param command Complete line of the event command entered by the user.
     */
    public TodoCommand(String command) {
        this.command = command;
    }

    /**
     * Method to execute entirely when a todo command is entered by the user.
     * @param tasks TaskList containing all the tasks.
     * @param dukeUI UI to print all string responses by the bot.
     * @return String response by the bot.
     * @throws InvalidTaskDescriptionException when an inaccurate task description is entered.
     */
    @Override
    protected String execute(TaskList tasks, UI dukeUI) throws InvalidTaskDescriptionException {
        try {
            if (this.command.contains("//")) {
                throw new InvalidTaskDescriptionException();
            }
            String[] todoDetails = this.command.split(" ", 2);
            Todo newTodo = new Todo(todoDetails[1]);
            tasks.addTask(newTodo);
            String dukeResponse = dukeUI.addTask(tasks, newTodo);
            return dukeResponse;
        } catch (IndexOutOfBoundsException e){
            throw new InvalidTaskDescriptionException();
        }
    }
}
