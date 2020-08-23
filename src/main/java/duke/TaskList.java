package duke;

import java.time.LocalDateTime;

import java.util.ArrayList;

import task.Task;
import task.Event;
import task.Todo;
import task.Deadline;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String listTasks() {
        String listOfTasks = "";

        for (int i = 0; i < this.tasks.size(); i++) {
            listOfTasks = listOfTasks + (i + 1) + ". " + this.tasks.get(i).toString()
                    + (i == this.tasks.size() - 1 ? "" : "\n");
        }

        return listOfTasks;
    }

    public Task createTask(TaskType.TypeOfTask typeOfTask, String description, LocalDateTime timing, boolean isDone) {

        switch (typeOfTask) {
        case TODO:
            return new Todo(description, isDone);
        case DEADLINE:
            return new Deadline(description, timing, isDone);
        case EVENT:
            return new Event(description, timing, isDone);
        default:
            return new Task(description, isDone);
        }
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(int task) {
        this.tasks.remove(task);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
