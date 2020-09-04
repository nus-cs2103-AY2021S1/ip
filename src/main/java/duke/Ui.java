package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui deals with interactions with users.
 */
public class Ui {
    private final String HORIZONTAL_LINE = "_______________________________________________________";
    
     /**
     * Introduction of Mocha.
     */
    public void sayIntroduction() {

        String nameIntro = "Hello, I'm Mocha!";
        String greeting = "What's up today!";
        System.out.println(HORIZONTAL_LINE
                + "\r\n"
                + nameIntro
                + "\r\n"
                + greeting
                + "\r\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Creates a new Parser object.
     * 
     * @return a Parser object.
     */
    public Parser createParser() {
        return new Parser();
    }

    /**
     * Creates a new Scanner object that uses the same list of Tasks as the existing file.
     * 
     * @return s Scanner object.
     */
    public Scanner createUserInputScanner() {
        return new Scanner(System.in);
    }

    /**
     * Displays the message when a task is added.
     * 
     * @param task  Takes in a task object.
     * @param sizeOfTasks The size of the list of tasks.
     */    
    public void addTask(Task task, int sizeOfTasks) {
        if (task.getTaskType() == "ToDo") {
            
            System.out.println(HORIZONTAL_LINE
                    + "\r\n"
                    + "One new ToDo Task added: "
                    + "\r\n"
                    + task.toString()
                    + "\r\n"
                    + "Total number of tasks in list: "
                    + sizeOfTasks
                    + "\r\n"
                    + HORIZONTAL_LINE);

        } else if (task.getTaskType() == "Deadline") {
            
            System.out.println(HORIZONTAL_LINE
                    + "\r\n"
                    + "One new Deadline added: "
                    + "\r\n"
                    + task.toString()
                    + "\r\n"
                    + "Total number of tasks in list: "
                    + sizeOfTasks
                    + "\r\n"
                    + HORIZONTAL_LINE);
            
        } else if (task.getTaskType() == "Event") {
            System.out.println(HORIZONTAL_LINE 
                    + "\r\n"
                    + "One new Deadline Task added: "
                    + "\r\n"
                    + task.toString()
                    + "\r\n"
                    + "Total number of tasks in list: "
                    + sizeOfTasks
                    + "\r\n"
                    + HORIZONTAL_LINE);
        } else {
            return;   
        }
    }

    /**
     * Displays the message when a task is marked done.
     * 
     * @param task Takes in a task object.
     */    
    public void markTaskDone(Task task) {
        System.out.println(HORIZONTAL_LINE
                + "\r\n"
                + "Nice! One thing done: \r\n"
                + task.toString()
                + "\r\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Displays all tasks.
     * 
     * @param tasks Takes in a list of tasks.
     */
    public void listAllTasks(TaskList tasks) {
        System.out.println(HORIZONTAL_LINE
                + "\r\n"
                + "Here are all of your tasks:"
                + "\r\n");

        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i).toString());
        }

        System.out.println("\r\n"
                + "You have a total of "
                + tasks.getSize()
                + " tasks."
                + "\r\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Mocha's salutations.
     */    
    public void sayGoodbye() {
        System.out.println(HORIZONTAL_LINE
                + "\r\n"
                + "Bye! See ya soon!"
                + "\r\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Displays the message when a task is deleted.
     * 
     * @param task Takes in a task object.
     * @param sizeOfTasks Takes in the size of the list of tasks.
     */
    public void deleteTask(Task task, int sizeOfTasks) {
        System.out.println(HORIZONTAL_LINE
                + "\r\n"
                + "Noted. Removing the following task:"
                + "\r\n"
                + task.toString()
                + "\r\n"
                + "Total number of tasks left in the list: "
                + sizeOfTasks
                + "\r\n"
                + HORIZONTAL_LINE);
    }
    
//    public void commandNotRecognisedExceptionMessage() {
//        System.out.println(HORIZONTAL_LINE
//                + "\r\n"
//                + "Oops! I couldn't understand what you mean :("
//                + "\r\n"
//                + HORIZONTAL_LINE);
//    }
    
//    public String commandNotRecognised() {
<<<<<<< HEAD
//        return HORIZONTAL_LINE
//                + "\r\n"
//                + "Oops! I couldn't understand what you mean :("
//                + "\r\n"
//                + HORIZONTAL_LINE;
//    }
=======
//        return horizontalLine
//                + "\r\n"
//                + "Oops! I couldn't understand what you mean :("
//                + "\r\n"
//                + horizontalLine;
//    }
    
    public void findTask(ArrayList<Task> matchingTasks) {

        System.out.println(horizontalLine
                + "\r\n"
                + "Here are the matching tasks in your list:"
                + "\r\n");

        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) 
                    + "." 
                    + matchingTasks.get(i).toString());
        }
        
        System.out.println(horizontalLine);
    }
>>>>>>> branch-Level-9
}
