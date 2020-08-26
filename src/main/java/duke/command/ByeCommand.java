package duke.command;

import duke.main.TaskList;

public class ByeCommand extends Command {

    @Override
    public void perform(TaskList tasks) {
        System.out.println(" Bye! Hope to see you again in the future!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
