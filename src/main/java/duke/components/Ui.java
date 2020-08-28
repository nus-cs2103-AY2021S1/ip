package duke.components;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Ui class handles the creation of an instance of a Ui that interacts with the user.
 * the 'Ui' class supports operators.
 * Supported operators includes
 * (i) printing the appropriate message for the user on what has been done.
 * and (ii) taking in inputs from the user
 */

public class Ui {

    Scanner scan;

    public Ui(){
        scan = new Scanner(System.in);
    }

    /**
     * prints a task list.
     *
     * @param taskList task list to be printed
     */
    public void printTaskList(ArrayList<Task> taskList){
        for (int i = 0; i < taskList.size(); i++) {

            Task currentTask = taskList.get(i);

            System.out.println((i + 1) + ". " + currentTask.toString());
        }
    }

    /**
     * prints a message when the task is marked as done.
     *
     * @param currentTask task to be marked as done.
     */

    public void printDoneTask(Task currentTask){
        System.out.println(
                "_______________________________\n" +
                        "Nice! I've marked this task as done:\n"+
                        currentTask.toString()+"\n"+
                        "_______________________________");

    }

    public void printAddTask(Task currentTask, ArrayList<Task> currentTaskList){
        System.out.println(
                "_______________________________\n" +
                        "Got it. I've added this task:\n" +
                        currentTask.toString() + "\n" +
                        "Now you have " + currentTaskList.size() + " task(s) in the list.\n" +
                        "_______________________________");

    }

    public void printBye(){

        System.out.println("_______________________________\n"+
                "Bye. Hope to see you again soon!");
    }

    public void printNotValid(){

        System.out.println(
                "_______________________________\n" +
                        " OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "_______________________________");

    }

    public void printDeleteTask(Task currentTask, ArrayList<Task> currentTaskList){
        System.out.println(
                "_______________________________\n" +
                        "Noted. I've removed this task:\n" +
                        currentTask.toString()+"\n"+
                        "Now you have "+currentTaskList.size()+" tasks in the list.\n"+
                        "_______________________________");

    }

    public String waitForNextInput(){
        return scan.nextLine();
    }
    public void printFindTask(ArrayList<Task> foundTaskList){
        System.out.println(
                "_______________________________\n" +
                        "Here are the matching tasks in your list:\n");
        printTaskList(foundTaskList);
        System.out.println("_______________________________");
    }


}
