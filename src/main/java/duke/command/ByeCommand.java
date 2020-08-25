package duke.command;

import duke.Storage;
import duke.TaskList;

public class ByeCommand extends Command {
    public ByeCommand(String command, TaskList list, Storage storage) {
        super(command, list, storage);
    }

    @Override
    public void execute(String command, TaskList list, Storage storage) {
        String hor_line = "____________________________________\n";
        System.out.println(hor_line + "Bye bye. See you soon bro!\n" + hor_line);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
