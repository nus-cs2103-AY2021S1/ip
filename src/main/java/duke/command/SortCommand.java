package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class SortCommand extends Command {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Comparator<? super Task> comparator = (task1, task2) -> {

            if (task1.getDate().isEmpty() && task2.getDate().isEmpty()) {
                return 0;
            }

            if (task1.getDate().isEmpty()) {
                return 1;
            }

            if (task2.getDate().isEmpty()) {
                return -1;
            }

            LocalDate firstTaskDate = task1.getDate().get();
            LocalDate secondTaskDate = task2.getDate().get();
            LocalTime firstTaskTime = task1.getTime().get();
            LocalTime secondTaskTime = task2.getTime().get();

            if (firstTaskDate.compareTo(secondTaskDate) == 0) {
                return firstTaskTime.compareTo(secondTaskTime);
            }

            return firstTaskDate.compareTo(secondTaskDate);
        };

        list.sortList(list.getList().stream()
                                    .sorted(comparator)
                                    .collect(Collectors.toList()));
        storage.generateTxt(list);
        return ui.showSort() + "\n" + new ListCommand()
                .execute(list, ui, storage);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
