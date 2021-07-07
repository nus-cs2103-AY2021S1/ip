package duke.commands;

import java.util.stream.IntStream;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;



/**
 * Finds all tasks that contains the keyword the user specified.
 */
public class FindCommand extends Command {
    public FindCommand(String command) {
        super(command);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the matching tasks in your list: \n");

        IntStream.range(0, tasks.size()).forEach(index -> {
            try {
                // Check if user input matches any task description in list
                if (tasks.getTask(index + 1).getDescription().contains(super.command)) {
                    String message = (index + 1) + "." + tasks.getTask(index + 1) + "\n";
                    stringBuilder.append(message);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        });
        String response = stringBuilder.toString();
        assert response != "" : "Response is an empty string";
        return response;
    }
}



