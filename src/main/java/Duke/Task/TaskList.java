package main.java.Duke.Task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list;

    public TaskList(ArrayList<Task> tasklist) {
        this.list = tasklist;
    }

    /**
     * Deletes the task from the Task List.
     *
     * @param taskNumber Task number to be deleted.
     */
    public String deleteTask(int taskNumber) {
        Task taskDeleted = list.get(taskNumber);
        list.remove(taskNumber);
        return ("I have removed the task:\n" + taskDeleted.stringify() + "\n" + "Now you have "
                + list.size() + " tasks in the list.");

    }

    /**
     * Adds the task to the Task List
     *
     * @param task Task to be added.
     */
    public String addTask(Task task) {
        list.add(task);
        return
                "I have added this task:\n"
                        + task.stringify() + "\n"
                        + "Now you have " + list.size() + " task(s) in the list.";
    }

    /**
     * Mark the task as completed.
     *
     * @param taskNumber Task number of the task to be marked as completed.
     */
    public String completeTask(int taskNumber) {
        Task taskCompleted = this.list.get(taskNumber);
        taskCompleted.isComplete = true;
        return "Nice! I've marked this task as done:\n" + "[✓] " + taskCompleted.task ;
    }

    /**
     * Shows the Task list.
     */
    public String showList(){
        System.out.println("Here are the tasks in your list:");
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += ((i+1)+"." + list.get(i).stringify() + "\n");
        }
        return str;
    }

    public String findTask(String str) {
        String returnStr = ("Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.task.contains(str)) {
                returnStr += ((i + 1) + "." + task.stringify());
            }
        }
        return returnStr;
    }
}
