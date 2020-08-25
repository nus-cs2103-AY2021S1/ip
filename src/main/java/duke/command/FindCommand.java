package duke.command;

import duke.common.CustomException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {

    private static String keyword;

    public FindCommand(String keyword) {
        duke.command.FindCommand.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CustomException {
        try {
            ArrayList<Task> matching = tasks.findTask(keyword);
            if (!matching.isEmpty()) {
                Ui.displayMatchingTasks(matching);
            } else {
                Ui.display("No matching tasks!");
            }
        } catch (Exception e) {
            throw new CustomException("Please include a keyword to search for!");
        }
    }
}

