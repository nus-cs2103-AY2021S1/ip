package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with users
 */
public class Ui {
    private final String HORIZONTAL_LINE = "_______________________________________________________";
    
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

    public Parser createParser() {
        return new Parser();
    }
    
    public Scanner createUserInputScanner() {
        return new Scanner(System.in);
    }
    
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
    
    public void markTaskDone(Task task) {
        System.out.println(HORIZONTAL_LINE
                + "\r\n"
                + "Nice! One thing done: \r\n"
                + task.toString()
                + "\r\n"
                + HORIZONTAL_LINE);
    }
    
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
    
    public void sayGoodbye() {
        System.out.println(HORIZONTAL_LINE
                + "\r\n"
                + "Bye! See ya soon!"
                + "\r\n"
                + HORIZONTAL_LINE);
    }
    
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
//        return HORIZONTAL_LINE
//                + "\r\n"
//                + "Oops! I couldn't understand what you mean :("
//                + "\r\n"
//                + HORIZONTAL_LINE;
//    }
}
