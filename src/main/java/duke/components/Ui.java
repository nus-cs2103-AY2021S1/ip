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


    /**
     * prints a task list.
     *
     * @param taskList task list to be printed
     */
    public String returnTaskList(ArrayList<Task> taskList){

        String output = "";

        for (int i = 0; i < taskList.size(); i++) {

            Task currentTask = taskList.get(i);

            output = output+((i + 1) + ". " + currentTask.toString()+"\n");
        }
        return output;
    }

    /**
     * prints a message when the task is marked as done.
     *
     * @param currentTask task to be marked as done.
     */

    public String returnDoneTask(Task currentTask){
        return
                "_______________________________\n" +
                        "Nice! I've marked this task as done:\n"+
                        currentTask.toString()+"\n"+
                        "_______________________________";

    }

    public String returnAddTask(Task currentTask, ArrayList<Task> currentTaskList){
        return(
                "_______________________________\n" +
                        "Got it. I've added this task:\n" +
                        currentTask.toString() + "\n" +
                        "Now you have " + currentTaskList.size() + " task(s) in the list.\n" +
                        "_______________________________");

    }

    public String returnBye(ArrayList<Task> reminderTaskList){

        String BYE_TEXT = ("_______________________________\n"+
                "Bye. Hope to see you again soon!");
        String reminderText = "Remember to finish up these tasks!\n"+
                returnTaskList(reminderTaskList);
        return reminderText+BYE_TEXT;

    }

    public String returnNotValid(){

        return(
                "_______________________________\n" +
                        " OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "_______________________________");

    }

    public String returnDeleteTask(Task currentTask, ArrayList<Task> currentTaskList){
        return(
                "_______________________________\n" +
                        "Noted. I've removed this task:\n" +
                        currentTask.toString()+"\n"+
                        "Now you have "+currentTaskList.size()+" tasks in the list.\n"+
                        "_______________________________");

    }
    public String returnAddReminder(Task currentTask, ArrayList<Task> currentReminderList){
        return(
                "_______________________________\n" +
                        "Noted. I've added a reminder to this task:\n" +
                        currentTask.toString()+"\n"+
                        "Now you have "+currentReminderList.size()+" reminders.\n"+
                        "_______________________________");

    }

    public String returnFindTask(ArrayList<Task> foundTaskList){
        return(
                "_______________________________\n" +
                        "Here are the matching tasks in your list:\n"+
        returnTaskList(foundTaskList)+
        "_______________________________");
    }

    public String returnStartUpText(ArrayList<Task> reminderTaskList){
        String START_UP_TEXT = "Hi, this is Duke, what can I do for you?\n" +
                "REMINDERS FOR TODAY:\n";
        String reminders = returnTaskList(reminderTaskList);
        return START_UP_TEXT+reminders;

    }

    public String returnReminderList(ArrayList<Task> reminderList){
        String REMINDER_TEXT = "Your reminders: \n";
        return REMINDER_TEXT+returnTaskList(reminderList);
    }
    public String returnDuplicateReminder(){

        return "_______________________________\n" +
                "This task is already in the reminder list!\n"+
                "_______________________________";
    }


}
