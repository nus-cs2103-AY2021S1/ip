import java.io.IOException;

public class AddCommand extends Command {

    String description;
    String date;
    TaskType type;

    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:\n%s\n%s";

    AddCommand(String description, String date, TaskType type) {
        this.description = description;
        this.date = date;
        this.type = type;
    }

    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(description, date, type);
        storage.writeToFile(taskList);
        ui.printReply(String.format(MESSAGE_SUCCESS, taskList.get(taskList.size() - 1), taskList));
    }

    boolean isExit() {
        return false;
    }
}
