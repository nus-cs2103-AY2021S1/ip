package duke.tasks;

import java.io.IOException;

public class FindCommand extends Command {
    protected String toFind;

    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        //find tasks with matching keywords in the string
        tasks.find(this.toFind);
    }
}
