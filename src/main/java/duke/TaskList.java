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

    private List<Task> taskList = new ArrayList<>();

    public TaskList() {}

    public TaskList(List<String> listOfTasks) throws WrongFormatException {
        initiateTaskList(listOfTasks);
    }

    private void initiateTaskList(List<String> listOfTasks) throws WrongFormatException {
        for (String s : listOfTasks) {
            String[] splitLine = s.split("\\|");
            switch (splitLine[0]) {
            case "[T]": // To-Do
                taskList.add(new ToDo(splitLine[2], !splitLine[1].equals("0")));
                break;
            case "[E]": // duke.task.Event
                taskList.add(new Event(splitLine[2], splitLine[3], !splitLine[1].equals("0")));
                break;
            case "[D]": // duke.task.Deadline
                taskList.add(new Deadline(splitLine[2], LocalDateTime.parse(splitLine[3]).format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HHmm")), !splitLine[1].equals("0")));
                break;
            default:
                System.err.println("Error in last save. Now loading a new, empty task list.");
                break;
            }
        }
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task removeTask(int taskIndex) {
        return taskList.remove(taskIndex);
    }

    public int getNumberOfTasks() {
        return taskList.size();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    @Override
    public String toString() {
        int index = 1;
        StringBuilder result = new StringBuilder();
        for (Task task : taskList) {
            result.append("\n").append(index++).append(".").append(task);
        }
        return result.toString();
    }
}
