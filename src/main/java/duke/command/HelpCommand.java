package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand extends Command {
    private final List<String> helpList;

    public HelpCommand() {
        helpList = new ArrayList<>();
    }

    /**
     * Displays a simple user guide on the different commands.
     *
     * @param tasks The list of {@link Task}s.
     * @param storage The Storage object of Duke.
     * @return A String representing the user guide.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        helpList.add("Welcome to Duke's help utility!\n\n");
        helpList.add("To list all tasks on your agenda, type `list`.\n");
        helpList.add("To terminate the application, type `bye`.\n");
        helpList.add("To add a new ToDo, type `todo {description}`.\n");
        helpList.add("To add a new Event, type `event {description} /at {yyyy-mm-dd}.\n");
        helpList.add("To add a new Deadline, type `deadline {description} /by {yyyy-mm-dd}.\n");
        helpList.add("To mark a task as completed, type `done {index}`.\n");
        helpList.add("To delete a task, type `delete {index}`.\n");
        helpList.add("To find all tasks containing certain keywords, type `find {keywords}`.\n");
        helpList.add("To list all completed tasks, type `completed`.\n");
        helpList.add("To list all pending tasks, type `pending`.\n");
        helpList.add("To list all tasks that happen or due on a certain date, type `show {yyyy-mm-dd}`.\n");

        StringBuilder output = new StringBuilder();
        for (String s : helpList) {
            output.append(s);
        }
        return output.toString();
    }
}
