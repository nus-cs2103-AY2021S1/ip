package main.java.duke;

import main.java.duke.task.Deadline;
import main.java.duke.task.Event;
import main.java.duke.task.Task;
import main.java.duke.task.ToDo;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    final ArrayList<Task> taskArrayList;

    TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    boolean isEmpty() {
        return taskArrayList.size() == 0;
    }

    void addToList(Task addTask) {
        this.taskArrayList.add(addTask);
    }

    Task getTaskAt(int taskIndex) {
        return this.taskArrayList.get(taskIndex);
    }

    /**
     * Deletes the task with index specified by taskIndex
     * @param taskIndex current index of the task
     * @return Deleted task if deletion is successful, null otherwise
     */
    public Task deleteTaskAt(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < this.taskArrayList.size()) {
            Task task = this.getTaskAt(taskIndex);
            this.taskArrayList.remove(taskIndex);
            return task;
        } else {
            return null;
        }
    }

    int getNumOfTasks() {
        return this.taskArrayList.size();
    }

    /**
     * Creates a new ToDo and adds it to the task list
     * @param description Description of the todo
     * @param isComplete Whether the todo is complete
     * @return Created todo
     */
    public ToDo addTodo(String description, boolean isComplete) {
        ToDo todo = new ToDo(description, isComplete);
        this.taskArrayList.add(todo);
        return todo;
    }

    /**
     * Creates a new deadline and adds it to the task list
     * @param description Description of the deadline
     * @param isComplete Whether the deadline is complete
     * @param date Date of the deadline
     * @return Created deadline
     */
    public Deadline addDeadline(String description, boolean isComplete, LocalDate date) {
        Deadline deadline = new Deadline(description, isComplete, date);
        this.taskArrayList.add(deadline);
        return deadline;
    }

    /**
     * Creates a new event and adds it to the task list
     * @param description Description of the event
     * @param isComplete Whether the event is complete
     * @param date Date of the event
     */
    public Event addEvent(String description, boolean isComplete, LocalDate date) {
        Event event = new Event(description, isComplete, date);
        this.taskArrayList.add(event);
        return event;
    }

    @Override
    public String toString() {
        String textIndentation = "     ";
        if (this.isEmpty()) {
            return textIndentation + "Your task list is currently empty. YAY!!! :D";
        } else {
            StringBuilder output = new StringBuilder(textIndentation + "Here are the tasks in your list:\n");
            int listSize = this.taskArrayList.size();
            for (int i = 0; i < listSize; i++) {
                String eachTaskString = textIndentation + (i + 1) + ". " + this.taskArrayList.get(i) + "\n";
                output.append(eachTaskString);
            }
            return output.deleteCharAt(output.length()-1).toString();
        }
    }

    /**
     * Marks the task with index specified by taskIndex as complete
     * @param taskIndex current index of the task
     * @return Completed task if completion is successful, null otherwise
     */
    public Task completeTaskAt(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < this.taskArrayList.size()) {
            Task task = this.taskArrayList.get(taskIndex);
            task.completeTask();
            return task;
        } else {
            return null;
        }
    }
}
