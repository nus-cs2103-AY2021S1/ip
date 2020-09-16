public class TodoCommand extends Command {
    private String userInput;
    private TaskList taskManager;

    public TodoCommand(String userInput, TaskList taskManager) {
        super("");
        this.userInput = userInput;
        this.taskManager = taskManager;
    }

    public String getOutput() {
        int length = this.userInput.length();
        if (length == 5) {
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
