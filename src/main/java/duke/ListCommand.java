package duke;

/**
 * Represents a command to list tasks.
 */
public class ListCommand extends Command {
    
    @Override
    public String execute(TaskList taskList, Ui ui, Storage store) throws DukeException {
        if (taskList.getSize() == 0) {
            return ui.showEmptyList();
        } else {
            String message = "";
            for (int i = 0; i < taskList.getSize(); i++) {
                message = message + (i + 1) + ": " + taskList.get(i) + "\n";
            }
            return message;
        }
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
