public class DoneCommand extends Command {
    private String userInput;
    private TaskList taskManager;

    public DoneCommand(String string, TaskList taskManager) {
        super("");
        userInput = string;
        this.taskManager = taskManager;
    }

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
