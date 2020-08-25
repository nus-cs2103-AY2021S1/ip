package duke.tasks;

import java.io.IOException;

public class DeadlineCommand extends Command {
    protected String deadline;

    public DeadlineCommand(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        //add event task to list of tasks
        tasks.deadline(this.deadline);

        //write to file
        String s = storage.genList(tasks.getTaskLs());
        storage.writeToFile("data/duke.rtf", s);
    }
}
