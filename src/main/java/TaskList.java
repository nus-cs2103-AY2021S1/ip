package main.java;

import java.time.LocalDate;
import java.util.ArrayList;

class TaskList {

    final ArrayList<Task> taskArrayList;

    TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    private boolean isEmpty() {
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
     * @return true if the task is deleted successfully, false otherwise
     */
    boolean deleteTaskAt(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < this.taskArrayList.size()) {
            this.taskArrayList.remove(taskIndex);
            return true;
        } else {
            return false;
        }
    }

    int getNumOfTasks() {
        return this.taskArrayList.size();
    }

    /**
     * Creates a new ToDo and adds it to the task list
     */
    void addTodo(String description, boolean isComplete) {
        ToDo todo = new ToDo(description, isComplete);
        this.taskArrayList.add(todo);
    }

    /**
     * Creates a new deadline and adds it to the task list
     */
    void addDeadline(String description, boolean isComplete, LocalDate date) {
        Deadline deadline = new Deadline(description, isComplete, date);
        this.taskArrayList.add(deadline);
    }

    /**
     * Creates a new event and adds it to the task list
     */
    void addEvent(String description, boolean isComplete, LocalDate date) {
        Event event = new Event(description, isComplete, date);
        this.taskArrayList.add(event);
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
     * @return true if the task is marked complete successfully, false otherwise
     */
    boolean completeTaskAt(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < this.taskArrayList.size()) {
            this.taskArrayList.get(taskIndex).completeTask();
            return true;
        } else {
            return false;
        }
    }
}
