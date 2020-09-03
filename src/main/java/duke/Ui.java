package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with users
 */
public class Ui {
    private final String horizontalLine = "_______________________________________________________";
    
    public void mochaIntroduction() {
        String nameIntro = "Hello, I'm Mocha!";
        String greeting = "What's up today!";
        System.out.println(horizontalLine
                + "\r\n"
                + nameIntro
                + "\r\n"
                + greeting
                + "\r\n"
                + horizontalLine);
    }

    public Parser createParser() {
        return new Parser();
    }
    
    public Scanner createUserInputScanner() {
        return new Scanner(System.in);
    }
    
    public void taskAdded(Task task, int sizeOfTasks) {
        if (task.getTaskType() == "ToDo") {
            
            System.out.println(horizontalLine
                    + "\r\n"
                    + "One new ToDo Task added: "
                    + "\r\n"
                    + task.toString()
                    + "\r\n"
                    + "Total number of tasks in list: "
                    + sizeOfTasks
                    + "\r\n"
                    + horizontalLine);

        } else if (task.getTaskType() == "Deadline") {
            
            System.out.println(horizontalLine
                    + "\r\n"
                    + "One new Deadline added: "
                    + "\r\n"
                    + task.toString()
                    + "\r\n"
                    + "Total number of tasks in list: "
                    + sizeOfTasks
                    + "\r\n"
                    + horizontalLine);
            
        } else if (task.getTaskType() == "Event") {
            System.out.println(horizontalLine 
                    + "\r\n"
                    + "One new Deadline Task added: "
                    + "\r\n"
                    + task.toString()
                    + "\r\n"
                    + "Total number of tasks in list: "
                    + sizeOfTasks
                    + "\r\n"
                    + horizontalLine);
        } else {
            return;   
        }
    }
    
    public void taskDone(Task task) {
        System.out.println(horizontalLine
                + "\r\n"
                + "Nice! One thing done: \r\n"
                + task.toString()
                + "\r\n"
                + horizontalLine);
    }
    
    public void listAllTasks(TaskList tasks) {
        System.out.println(horizontalLine
                + "\r\n"
                + "Here are all of your tasks:"
                + "\r\n");

        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }

        System.out.println("\r\n"
                + "You have a total of "
                + tasks.getSize()
                + " tasks."
                + "\r\n"
                + horizontalLine);
    }
    
    public void mochaGoodbye() {
        System.out.println(horizontalLine
                + "\r\n"
                + "Bye! See ya soon!"
                + "\r\n"
                + horizontalLine);
    }
    
    public void deleteTask(Task task, int sizeOfTasks) {
        System.out.println(horizontalLine
                + "\r\n"
                + "Noted. Removing the following task:"
                + "\r\n"
                + task.toString()
                + "\r\n"
                + "Total number of tasks left in the list: "
                + sizeOfTasks
                + "\r\n"
                + horizontalLine);
    }
    
//    public void commandNotRecognisedExceptionMessage() {
//        System.out.println(horizontalLine
//                + "\r\n"
//                + "Oops! I couldn't understand what you mean :("
//                + "\r\n"
//                + horizontalLine);
//    }
    
    public String commandNotRecognised() {
        return horizontalLine
                + "\r\n"
                + "Oops! I couldn't understand what you mean :("
                + "\r\n"
                + horizontalLine;
    }
}
