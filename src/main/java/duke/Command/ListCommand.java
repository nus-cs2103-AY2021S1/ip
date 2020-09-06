package duke.Command;

import duke.TaskList;

public class ListCommand extends Command {

    TaskList list;

    public ListCommand(String input, TaskList list) {
        super(input);
        this.list = list;
    }

    public String execute() {
        return list.toString();
    }
}
