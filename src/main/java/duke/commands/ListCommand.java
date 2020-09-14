package duke.commands;

import java.util.stream.IntStream;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Lists all tasks from the task list.
 */
public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return listAllTasks(tasks);
    }

    private String listAllTasks(TaskList tasks) {
        tasks.sortByPriority();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("These are your tasks Oppa!!! \n");
        IntStream.range(0, tasks.size()).forEach(index -> {
            try {
                String output = (index + 1) + ". " + tasks.getTask(index + 1) + "\n";
                stringBuilder.append(output);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        });
        String response = stringBuilder.toString();
        assert response != "" : "Response is an empty string";
        return response;
    }
}


