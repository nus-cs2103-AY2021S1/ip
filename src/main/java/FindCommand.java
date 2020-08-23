import java.util.ArrayList;

public class FindCommand extends Command {

    private String command;

    public FindCommand(String command) {
        this.command = command;
    }

    @Override
    void execute(TaskList tasks, UI dukeUI) throws DukeException {
        try {
            String[] keywords = this.command.split(" ", 2);
            String keyword = keywords[1];
            ArrayList<Task> foundTasks = tasks.findTask(keyword);
            dukeUI.findTask(foundTasks);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidKeywordException();
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
