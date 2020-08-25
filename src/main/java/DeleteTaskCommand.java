import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteTaskCommand extends Command {

    public DeleteTaskCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        Pattern r = Pattern.compile(InputPattern.deleteTaskPattern);
        Matcher m = r.matcher(input);
        m.find();
        int taskNumber = Integer.parseInt(m.group("taskNumber"));
        try {
            Task task = taskManager.deleteTask(taskNumber);
            ui.sendMessage(MessageManager.getDeleteSuccessMessage(task, taskManager));
        } catch (DukeException | IOException exception) {
            ui.sendMessage(exception.getMessage());
        }
    }
}
