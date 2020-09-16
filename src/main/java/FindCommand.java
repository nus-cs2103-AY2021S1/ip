import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFindMessage(tasks.filter(keyword));
    }

    public boolean isExit() {
        return false;
    }
}
