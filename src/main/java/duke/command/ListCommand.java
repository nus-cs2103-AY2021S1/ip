package duke.command;

import duke.Storage;
import duke.TaskList;

public class ListCommand extends Command {
    public ListCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    @Override
    public void execute(String command, TaskList list, Storage storage) {
        String hor_line = "____________________________________\n";
        System.out.println(hor_line + "Here are the things you need to do lor: \n");
        System.out.println(list.iterateToDo() + hor_line);
    }
}
