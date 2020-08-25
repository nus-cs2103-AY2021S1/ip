package duke.tasks;

import java.io.IOException;

public class DoneCommand extends Command {
    protected String done;

    public DoneCommand(String done) {
        this.done = done;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        //remove event task from list of tasks
        tasks.done(this.done);

        //write to file
        String s = storage.genList(tasks.getTaskLs());
        storage.writeToFile("data/duke.rtf", s);
    }

    public abstract static class Command {
        public void execute(TaskList tasks, UI ui, Storage storage) throws IOException { }

        public boolean isExit() {
            return false;
        }
    }
}
