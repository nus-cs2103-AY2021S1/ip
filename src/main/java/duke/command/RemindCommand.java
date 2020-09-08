package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class RemindCommand extends Command {

    private final int hours;

    /**
     * Represents a RemindCommand
     * @param number the number of days till the occurrence/deadline of the task
     * @throws DukeException if the user input is invalid
     */
    public RemindCommand(String number) throws DukeException {
        try {
            this.hours = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid user input detected...");
        }
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {

        String out = ui.showRemind();

        Function<Task, Optional<LocalDateTime>> mapper = x -> {
            if (x.getDate().isEmpty()) {
                return Optional.empty();
            }

            if(x.isDone()) {
                return Optional.empty();
            }

            assert x.getTime().isPresent() : "ToDo task or done task detected";

            return Optional.ofNullable(LocalDateTime.of(x.getDate().get(), x.getTime().get()));
        };

        Predicate<Optional<LocalDateTime>> test = x -> {
            if (x.isEmpty()) {
                return false;
            }

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime taskTime = x.get();
            if (taskTime.isBefore(now)) {
                return false;
            }

            return Duration.between(now, taskTime).toHours() <= this.hours;
        };

        int count = 1;
        for (Task task : list.getList()) {
            if (test.test(mapper.apply(task))) {
                out = out + ui.showTask(count, task) + "\n";
                count++;
            }
        }

        return out;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
