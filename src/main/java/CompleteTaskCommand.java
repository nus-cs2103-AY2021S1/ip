import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompleteTaskCommand extends Command {

    public CompleteTaskCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {

        Pattern r = Pattern.compile(InputPattern.completeTaskPattern);
        Matcher m = r.matcher(input);
        m.find();
        int taskNumber = Integer.parseInt(m.group("taskNumber"));
        try {
            Task task = taskManager.completeTask(taskNumber);
            ui.sendMessage(MessageManager.getCompleteSuccessMessage(task));
        } catch (DukeException | IOException exception) {
            ui.sendMessage(exception.getMessage());
        }
    }
}
