package duke;

import duke.commands.Commands;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<String> stringList) {
        taskList = new ArrayList<>();
        stringList.forEach(this::parseLine);
    }

    public List<Task> getList() {
        return taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    private void parseLine(String line) {
        String[] lineSplit = line.split("\\|");
        boolean done = lineSplit[2].equals("1");
        if (lineSplit[0].equals("T")) {
            addTask(lineSplit[1], Commands.TODO, "", done);
        } else if (lineSplit[0].equals("D")) {
            addTask(lineSplit[1], Commands.DEADLINE, lineSplit[3], done);
        } else {
            addTask(lineSplit[1], Commands.EVENT, lineSplit[3], done);
        }
    }

    private void addTask(String task, Commands type, String ddl, boolean done) {
        Task newTask;
        if (type == Commands.TODO) {
            newTask = new Todo(task);
        } else if (type == Commands.DEADLINE) {
            newTask = new Deadline(task, LocalDate.parse(ddl));
        } else {
            newTask = new Event(task, LocalDate.parse(ddl));
        }

        if (done) {
            newTask.markDone();
        }
        taskList.add(newTask);
    }

    public Task addTask(String task) {
        return addTask(new Todo(task));
    }

    public Task addTask(String task, boolean isEvent) throws DukeException {
        Task newTask;
        String[] taskSplit;
        if (isEvent) {
            taskSplit = task.split("/at");
            if (taskSplit.length != 2) {
                throw new DukeException("Invalid description for an event. Use /at followed by date");
            }
            LocalDate dateTime = validateDateTime(taskSplit[1].strip());
            newTask = new Event(taskSplit[0].strip(), dateTime);
        } else {
            taskSplit = task.split("/by");
            if (taskSplit.length != 2) {
                throw new DukeException("Invalid description for a deadline. Use /by followed by date");
            }
            LocalDate dateTime = validateDateTime(taskSplit[1].strip());
            newTask = new Deadline(taskSplit[0].strip(), dateTime);
        }
        return addTask(newTask);
    }

    private Task addTask(Task task) {
        taskList.add(task);
        return task;
    }

    public String[] listTasks() {
        String[] taskOutputs = new String[taskList.size() + 1];
        taskOutputs[0] = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            taskOutputs[i + 1] = (i + 1) + ". " + taskList.get(i).toString();
        }
        return taskOutputs;
    }

    public Task markDone(int position) throws DukeException {
        if (position < 0 || position > taskList.size()) {
            throw new DukeException("Invalid task number provided");
        }
        Task task = taskList.get(position - 1);
        task.markDone();
        return task;
    }

    public Task deleteTask(int position) throws DukeException {
        if (position < 0 || position > taskList.size()) {
            throw new DukeException("Invalid task number provided");
        }
        Task task = taskList.remove(position - 1);
        return task;
    }

    private LocalDate validateDateTime(String time) throws DukeException {
        if (time.equals("")) {
            throw new DukeException("duke.tasks.Task date cannot be empty.");
        }
        LocalDate parsed;
        try {
            parsed = LocalDate.parse(time);
            return parsed;
        } catch (DateTimeParseException ex) {
            throw new DukeException("Invalid date entered. Use format YYYY-MM-DD");
        }
    }
}
