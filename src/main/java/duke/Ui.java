package duke;

import java.util.List;

/**
 * The Ui class to handle user interface methods
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   27/8/2020
 */
public class Ui {
    String line = "--------------------------------------------------"; //50 dashes

    /**
     * Ui constructor
     */
    Ui(){

    }

    /**
     * drawLine method which outputs a line
     */
    public void drawLine(){
        System.out.println(line);
    }

    /**
     * addTask method which outputs the task information
     */
    public void addTask(Task task){
        System.out.println("added: " + task.toString());
    }

    /**
     * deleteTask method which outputs the task information
     */
    public void deleteTask(Task task){
        if (task == null){
            deleteAll();
        } else {
            System.out.println("Noted. I've removed this task: " + "\n" + task.toString());
        }

    }

    /**
     * doneTask method which outputs the task information
     */
    public void doneTask(Task task){
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task.toString());
    }

    /**
     * listCount method which outputs the number of tasks
     */
    public void listCount(int count){
        System.out.println("Now you have "+count+" tasks in the list.");
    }

    /**
     * bye method which outputs a message when the user exits
     */
    public void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * deleteAll method which outputs a message when the user deletes all tasks
     */
    public void deleteAll(){
        System.out.println("All tasks have been deleted!");
    }

    /**
     * printList method which outputs all the tasks in the list
     * @param list list of tasks
     */
    public void printList(List<Task> list){
        int tempIndex = 1;
        for (Task x: list){
            System.out.println(tempIndex + "." + x.toString());
            tempIndex += 1;
        }
    }
}
