package duke.commands;

import duke.Parser;
import duke.Task;
import duke.TaskList;
import duke.ToDo;
import duke.Deadline;
import duke.Event;
import duke.Ui;
import duke.Storage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class AddCommand extends Command {
    private String commandContent;

    public AddCommand(String commandContent) {
        this.commandContent = commandContent;
    }

    public String run(TaskList taskList, Storage storage) {
        Task newTask;
        newTask = new Task(commandContent);
        taskList.add(newTask);

        return Ui.addTask(newTask, taskList);
    }
}
