import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDeadlineCommand extends Command{

    public AddDeadlineCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        Pattern r = Pattern.compile(InputPattern.addDeadlinePattern);
        Matcher m = r.matcher(input);
        m.find();
        String content = m.group("content");
        String datetimeDue = m.group("datetimeDue");
        try {
            Deadline deadline = taskManager.addDeadline(content, datetimeDue);
            ui.sendMessage(MessageManager.getAddSuccessMessage(deadline, taskManager));
        } catch (DukeException | IOException exception) {
            ui.sendMessage(exception.getMessage());
        }
    }
}
