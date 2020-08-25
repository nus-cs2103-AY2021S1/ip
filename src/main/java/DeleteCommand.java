public class DeleteCommand extends Command {

    int index;

    public static final String MESSAGE_SUCCESS = "Noted. I've removed this task:\n%s";

    DeleteCommand(int index) {
        this.index = index;
    }

    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = taskList.get(index -1 );
        taskList.deleteTask(index);
        storage.writeToFile(taskList);
        ui.printReply(String.format(MESSAGE_SUCCESS, deletedTask ));
    }

    boolean isExit() {
        return false;
    }
}
