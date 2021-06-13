package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class ScheduleCommand extends Command {
    private TaskList tasks;
    private String[] args;

    /**
     * Constructor for ScheduleCommand class.
     * @param args
     * @param tasks
     */
    public ScheduleCommand(String[] args, TaskList tasks) {
        this.args = args;
        this.tasks = tasks;
    }

    @Override
    public String execute() throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("OOPS!!! There is no task in the list.");
        }

        String searchDate = args[1];
        List<Task> resultTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String[] data = currentTask.getData().split("/");

            if (data.length != 4) {
                continue;
            }

            String date = data[data.length - 1];

            if (date.contains(searchDate)) {
                resultTasks.add(currentTask);
            }
        }

        StringBuilder concat = new StringBuilder();

        for (int i = 0; i < resultTasks.size(); i++) {
            concat.append(String.format("\n\t%d. %s", i + 1, resultTasks.get(i)));
        }

        return "Here are the schedule on "
                + LocalDate.parse(searchDate).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ": " + concat;
    }
}
