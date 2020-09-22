package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import task.ToDoTask;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ToDoCommand extends Command {
    public ToDoCommand(String type, String command, String time, LocalDate date, boolean hasDate) {
        super(type, command, time, date, hasDate);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> taskList = tasks.getTasks();

        String message = "";

        taskList.add(new ToDoTask(command));

        message = message + "Got it. I've added this task:\n  "
                + taskList.get(taskList.size() - 1).toString() + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.";

        try {
            storage.writeToFile(taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        return message;
    }
}
