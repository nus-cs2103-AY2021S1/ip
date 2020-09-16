public class DeleteCommand extends Command {
    private String userInput;
    private TaskList taskManager;

    public DeleteCommand(String userInput, TaskList taskManager) {
        super("");
        this.userInput = userInput;
        this.taskManager = taskManager;
    }

    public String getOutput() {
        int length = userInput.length();
        String index = userInput.substring(7, length);
        int realIndex = Integer.parseInt(index) - 1;

        if (realIndex >= this.taskManager.getTaskNum() || realIndex < 0) {
            ExceptionCommand exceptionCommand = new ExceptionCommand(ExceptionCommand.INVALID_INDEX);
            this.appendDukeMessage(exceptionCommand.getOutput());

        } else {
            assert this.taskManager.getTaskNum() >= realIndex + 1 : "There should be a task in task list to mark delete";

            Task taskSubject = this.taskManager.remove(realIndex);
            this.appendDukeMessage("Noted. I've removed this task:"
                    + "\n  " + taskSubject
                    + "\nNow you have " + this.taskManager.getTaskNum() + " task(s) in the list.");
        }
        return this.toString();
    }
}
