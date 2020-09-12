package duke;

import java.util.ArrayList;

class FindCommand extends Command {

    /** Contains user's search string. */
    private String search;

    FindCommand(String search) {
        this.search = search;
    }

    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<String> foundTaskListString = tasks.findTasks(search);
        return ui.showFindTaskMessage(foundTaskListString);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
