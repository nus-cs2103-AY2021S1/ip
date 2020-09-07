package duke.tasks;

import java.io.IOException;

/**
 * Represents a Done Command. This command handles the done input
 * from users.
 */
public class DoneCommand extends Command {
    private String done;

    public DoneCommand(String done) {
        this.done = done;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        try {
            //remove event task from list of tasks
            tasks.done(this.done);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "Either input number is out of range of the list " +
                    "of tasks or the input is not a number";
        }

        //write to file
        String s = storage.genList(tasks.getTaskLs());
        storage.writeToFile("data/duke.rtf", s);

        return tasks.doneString(this.done);
    }

    public abstract static class Command {
        public void execute(TaskList tasks, UI ui, Storage storage) throws IOException { }

        public boolean isExit() {
            return false;
        }
    }
}
