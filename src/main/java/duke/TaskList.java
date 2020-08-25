package duke;

import duke.exception.WrongFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public TaskList(List<String> listOfTasks) throws WrongFormatException {
        initiateTaskList(listOfTasks);
    }

    private void initiateTaskList(List<String> listOfTasks) throws WrongFormatException {
        for (String s : listOfTasks) {
            String[] splitLine = s.split("\\|");
            switch (splitLine[0]) {
            case "[T]": // To-Do
                tasks.add(new ToDo(splitLine[2], !splitLine[1].equals("0")));
                break;
            case "[E]": // duke.task.Event
                tasks.add(new Event(splitLine[2], splitLine[3], !splitLine[1].equals("0")));
                break;
            case "[D]": // duke.task.Deadline
                tasks.add(new Deadline(splitLine[2], LocalDateTime.parse(splitLine[3]).format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HHmm")), !splitLine[1].equals("0")));
                break;
            default:
                System.err.println("Error in last save. Now loading a new, empty task list.");
                break;
            }
        }
    }

    public List<Task> getTaskList() {
        return tasks;
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int taskIndex) {
        return tasks.remove(taskIndex);
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    @Override
    public String toString() {
        int index = 1;
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append("\n").append(index++).append(".").append(task);
        }
        return result.toString();
    }
}
