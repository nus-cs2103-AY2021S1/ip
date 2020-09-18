/**
 * Represents a DoneCommand command which is a subclass of Command.
 */
public class DoneCommand extends Command {
    private String userInput;
    private TaskList taskManager;

    /**
     * Creates a DoneCommand object.
     * Marks a task as done, task is specified by user via task id.
     *
     * @param userInput represents the user String input.
     * @param taskManager reference to the same TaskList that manages list of Tasks.
     */
    public DoneCommand(String userInput, TaskList taskManager) {
        super("");
        this.userInput = userInput;
        this.taskManager = taskManager;
    }

    /**
     * Returns the done message.
     *
     * @return a String of done message as Duke response.
     */
    public String getOutput() {
        int length = userInput.length();
        String index = userInput.substring(5, length);
        int realIndex = Integer.parseInt(index) - 1;

        if (realIndex >= this.taskManager.getTaskNum() || realIndex < 0) {
            ExceptionCommand exceptionCommand = new ExceptionCommand(ExceptionCommand.INVALID_INDEX);
            this.appendDukeMessage(exceptionCommand.getOutput());

        } else {
            assert this.taskManager.getTaskNum() >= realIndex + 1 : "There should be a task in task list to mark done";

            Task taskSubject = this.taskManager.markDone(realIndex);
            this.appendDukeMessage("Nice! I've marked this task as done:\n"
                    + "  " + taskSubject);
        }

        return this.toString();
    }
}
