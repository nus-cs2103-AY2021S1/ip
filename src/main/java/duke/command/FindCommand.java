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

public class FindCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, String input) throws InvalidTaskIdException, EmptyDescriptionException, InvalidFormatException {
        String find = Parser.parseFind(input);
        List<Task> tasks = taskList.getTasks();
        List<Task> filtered = tasks.stream().
                filter(task -> task.getTitle().contains(find)).collect(Collectors.toList());
        ui.filteredList(filtered);
    }
}
