package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.FindException;
import duke.task.Task;

public class FindCommand extends Command {

    String description;

    public FindCommand(String description) {
        this.description = description;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String list = "";
        int index = 1;
        for (Task task : taskList.getList()) {
            if (task.toString().contains(description)) {
                list += "\n" + index + ". " + task.toString();
                index++;
            }
        }
        if (list.equals("")) {
            throw new FindException("There is no matching results. Please re-enter:");
        } else {
            System.out.println("Here are the matching tasks in your list:" + list);
        }

    }
}
