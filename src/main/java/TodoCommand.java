/**
 * Represents a TodoCommand command which is a subclass of Command.
 */
public class TodoCommand extends Command {
    private String userInput;
    private TaskList taskManager;

    /**
     * Creates a TodoCommand object.
     * It generates a message output showing user that a todo task is created.
     *
     * @param userInput represents the user String input.
     * @param taskManager reference to the same TaskList that manages list of Tasks.
     */
    public TodoCommand(String userInput, TaskList taskManager) {
        super("");
        this.userInput = userInput;
        this.taskManager = taskManager;
    }

    /**
     * Returns the todo creation message.
     *
     * @return a String of todo creation message as Duke response.
     */
    public String getOutput() {
        userInput = userInput.trim();
        int length = this.userInput.length();

        if (length <= 5) {
            ExceptionCommand exceptionCommand = new ExceptionCommand(ExceptionCommand.NO_DESCRIPTION);
            this.appendDukeMessage(exceptionCommand.getOutput());

        } else {
            Todo newTodo = new Todo(userInput.substring(5, length));
            this.taskManager.store(newTodo);
            this.appendDukeMessage("Got it. I've added this task:"
                    + "\n  " + newTodo
                    + "\nNow you have " + this.taskManager.getTaskNum() + " task(s) in the list.");
        }

        return this.toString();
    }
}
