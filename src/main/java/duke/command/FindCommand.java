package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidTaskIdException;
import duke.task.Task;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represent a find command.
 */
public class FindCommand implements Command {

    /**
     * Parses the input to extract the detail: searchWord.
     * Shows the list of filtered tasks that contains the searchWord.
     *
     * @param taskList
     * @param ui
     * @param input
     * @throws EmptyDescriptionException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String input) throws EmptyDescriptionException {
        String searchWord = Parser.parseFind(input);
        List<Task> tasks = taskList.getTasks();
        List<Task> filtered = tasks.stream().
                filter(task -> task.getTitle().contains(searchWord)).collect(Collectors.toList());
        ui.filteredList(filtered);
    }
}
