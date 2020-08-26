package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;


public class FindTaskCommand extends Command {

    public FindTaskCommand(String command) {
        super(command);
    }

    public void execute(TaskList list, Ui ui, Storage saveData) {
        try {
            if (this.command.trim().length() == 4) {
                throw new DukeException("☹ OOPS!!! Check find formatting, include keyword");
            }
            String keyword = this.command.substring(5);
            TaskList searchedList = new TaskList();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDescription().contains(keyword)) {
                    searchedList.add(list.get(i));
                }
            }
            ui.showList(searchedList);
        } catch (DukeException e) {
            ui.saySomthing(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
