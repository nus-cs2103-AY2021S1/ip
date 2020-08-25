import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddTodoCommand extends Command {

    public AddTodoCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        Pattern r = Pattern.compile(InputPattern.addTodoPattern);
        Matcher m = r.matcher(input);
        m.find();
        String content = m.group("content");
        try {
            Todo todo = taskManager.addTodo(content);
            ui.sendMessage(MessageManager.getAddSuccessMessage(todo, taskManager));
        } catch (DukeException | IOException exception) {
            ui.sendMessage(exception.getMessage());
        }
    }
}
