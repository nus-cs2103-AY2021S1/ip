package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * The TaskList class contains the task list
 */
public class TaskList {
    public ArrayList<Task> todoList = new ArrayList<Task>();
    public TaskList(ArrayList<Task> ls) {
        this.todoList = ls;
    }
}
