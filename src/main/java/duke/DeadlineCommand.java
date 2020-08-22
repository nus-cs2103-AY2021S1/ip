package duke;

import java.util.ArrayList;
import java.util.List;

public class DeadlineCommand implements Command {
    String description;
    String deadline;

    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException {
        Task deadlineTask = new DeadlineTask(description, deadline);
        tasks.add(deadlineTask);
        ui.printWithWrapper(new ArrayList<>(List.of(
                "Sure! I have added the following deadline task to your list: ",
                deadlineTask.toString(),
                tasks.getListStatus())), false, false);
    }
}
