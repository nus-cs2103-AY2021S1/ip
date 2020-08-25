package main.java.Task;

import main.java.DukeException.DukeArrayException;
import main.java.DukeException.DukeException;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list;

    public TaskList(ArrayList<Task> tasklist){
        this.list = tasklist;
    }

    /**
     * Deletes the task from the Task List.
     *
     * @param taskNumber Task number to be deleted.
     */
    public void deleteTask(int taskNumber){
        Task taskDeleted = list.get(taskNumber);
        list.remove(taskNumber);
        System.out.println("I have removed the task:\n" + taskDeleted.stringify() + "\n" + "Now you have " +
                    list.size() + " tasks in the list.");

    }

    /**
     * Adds the task to the Task List
     *
     * @param task Task to be added.
     */
    public void addTask(Task task){
        list.add(task);
        System.out.println(
                "I have added this task:\n"
                        + task.stringify() + "\n"
                        + "Now you have " + list.size() + " task(s) in the list.");
    }

    /**
     * Mark the task as completed.
     *
     * @param taskNumber Task number of the task to be marked as completed.
     */
    public void completeTask(int taskNumber){
        Task taskCompleted = this.list.get(taskNumber);
        taskCompleted.isComplete = true;
        System.out.println("Nice! I've marked this task as done:\n" + "[✓] " + taskCompleted.task);
    }

    /**
     * Shows the Task list.
     */
    public void showList(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1)+"."+list.get(i).stringify());
        }
    }

    public void findTask(String str){
        System.out.println("Here are the matching tasks in your list:");
        for(int i = 0; i < list.size(); i++){
            Task task = list.get(i);
            if(task.task.contains(str)){
                System.out.println((i+1)+"."+task.stringify());
            }
        }
    }
}
