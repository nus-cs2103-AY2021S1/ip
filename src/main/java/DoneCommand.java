public class DoneCommand extends Command {
    int index;

    public static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done:\n%s";

    DoneCommand(int index) {
        this.index = index;
    }

    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task completedTask = taskList.get(index - 1);
        taskList.completeTask(index);
        storage.writeToFile(taskList);
        ui.printReply(String.format(MESSAGE_SUCCESS, completedTask));
    }

    boolean isExit() {
        return false;
    }
}
