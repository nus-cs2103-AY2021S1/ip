import java.util.List;
import java.util.LinkedList;

public class FindCommand implements Command {

    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Integer> indices = new LinkedList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getDescription().contains(query)) {
                indices.add(i);
            }
        }
        String msg = tasks.getQueryResultMessage(indices);
        ui.sendMessage(msg);
    }
}
