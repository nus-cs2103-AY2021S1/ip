package duke;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;



/**
 * Encapsulates the user interface.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
    }
    public Ui(Scanner sc) {
        this.scanner = sc;
    }
    /**
     * Prints the default welcome message.
     */
    public static String printWelcome() {
        String welcomeMessage = ("--------------------------------------\n")
                + "Hello from doge\n"
                + "______________________$$$$$$$$\n"
                + "_______________$$$$$$$________$$$$$$$$$\n"
                + "_____________$$________________________$$$$\n"
                + "____________$$_____________________________$$\n"
                + "___________$__________________________________$$\n"
                + "___________$$___________________________________$$\n"
                + "__________ $$__$$______________________$$__________$$\n"
                + "________$$__$$___$$$$_________$$$$____$$__________$$$$\n"
                + "______$$___$$__$$$$__$$_____$$$$__$$_$$_____________$$$\n"
                + "______$$___$$____$$$$_________$$$$___$$_______________$$\n"
                + "______$$___$$________________________$$_______________$$\n"
                + "______$$____$$_______________________$$_____________$$\n"
                + "________$$__$$____$$$$$$_____________$$___________$$$\n"
                + "________$$__$$__$$______$$___________$$_________$$\n"
                + "________$$__$$__$$______$$___________$$_______$$\n"
                + "__________$$$$____$$$$$$_____________$$$$____$$$$\n"
                + "__________$$$$_____________________$$__$$____$$$\n"
                + "___________$$_$$$$$$$$$$$$_____$$$$______$$$$_$$\n"
                + "_____________$$___$$______$$$$$_______________$$\n"
                + "_____________$$_____$$$$$$$____________________$$\n"
                + "_____________$$________________________________$$\n"
                + "____________$$_________________________________$$\n"
                + "____________$$_________________________________$$\n"
                + "____________$$___________________________________$\n"
                + "____________$$___________________________________$$\n"
                + "__________$$_________________________$$___________$\n"
                + "__________$$__________$$___________$$_____________$$\n"
                + "________$$__$$________$$_________$$_______________$$\n"
                + "______$$____$$__________$$_______$$_______________$$\n"
                + "______$$____$$____________$$___$$_________________$$\n"
                + "____$$______$$_____________$$_$$_______$$_________$$\n"
                + "____$$______$$________$$____$$$________$$_________$$\n"
                + "____$$______$$________$$____$$$_______$$__________$$\n"
                + "____$$______$$________$$_______________$$__________$$\n"
                + "____$$______$$________$$_______________$$____________$\n"
                + "_$$$$_______$$________$$_______________$$____________$$\n"
                + "$___$$______$$________$$$$___________$$$$____________$$\n"
                + "$___$$______$$________$$__$$_______$$__$$____________$$\n"
                + "_$$$$$______$$________$$____$$___$$_____$$___________$$\n"
                + "____$$______$$________$$______$$_______$$___________$$\n"
                + "____$$______$$________$$_____$$________$$___________$$\n"
                + "__$$________$$________$$$$$$$$___$$$$$$__$$_________$$\n"
                + "__$$________$$________$$______$$$______$$$$_________$$\n"
                + "$$________$$__________$$_________$$$$$$__$$__________$\n"
                + "$$______$$__________$$$$$$$$$$$$$$$______$$__________$\n"
                + "$$_$$_$$$__________$$_____________$$$$$$$__$$_________$\n"
                + "_$$$$$$$___________$$______________________$$________$$\n"
                + "_____$$__$$__$$__$$_$______________________$$__________$$\n"
                + "______$$$$__$___$__$$______________________$$____________$\n"
                + "_______$$___$___$__$________________________$$_$__$$__$$__$\n"
                + "_________$$$$$$$$$$__________________________$$_$_$$$$$$$$\n"
                + "What can this good boi do for you?\n"
                + "--------------------------------------";
        System.out.println(welcomeMessage);
        return welcomeMessage;
    }
    /**
     * Prints a given string but adds "Added : " in front of the message.
     * @param msg given string
     */
    public String taskPrint(String msg) {
        String returnStr = "Added : " + msg;
        System.out.println("Added : " + msg);
        return returnStr;
    }


    /** Prints out a given ArrayList of tasks in a nice format, with the
     * given displayMessage on top.
     * @param toDoList given todolist
     * @param displayMessage message to be displayed
     */
    public String displayList(List<Task> toDoList, String displayMessage) {
        StringBuilder returnStr = new StringBuilder(displayMessage + "\n");
        System.out.println(displayMessage);
        int i = 1;
        for (Task s : toDoList) {
            String addStr = i + ". " + " [" + s.getType() + "] "
                    + s.toString() + " [" + s.getTaskStatusIcon() + "]";
            System.out.println(addStr);
            i += 1;
            returnStr.append(addStr).append("\n");
        }
        return returnStr.toString();
    }

    /**
     * Prints the completion of a duke.task
     * @param task duke.task description
     * @param taskStatus icon representing completion of duke.task
     */
    public String checkList(String task, String taskStatus) {
        String returnStr;
        returnStr = "--------------------------------------" + "\n"
                + "Such wow! I have completed the following task!" + "\n"
                + task + " [" + taskStatus + "]" + "\n"
                + "--------------------------------------\n";
        System.out.println(returnStr);
        return returnStr;
    }

    /**
     * Prints formatted message when duke.task is removed
     * @param taskId given duke.task id
     */
    public String removePrint(int taskId) {
        assert taskId > 0 : "Task number provided cannot be less than 1!";
        Task tr = TaskList.getList().get(taskId - 1);
        String returnStr;
        returnStr = "Task successfully removed!" + "\n"
                + "-> " + tr.toString() + "\n";
        System.out.println(returnStr);
        return returnStr;
    }

    /**
     * Formats and prints input String
     * @param print input string
     */
    public String printFormat(String print) {
        String returnStr;
        returnStr = "--------------------------------------" + "\n"
                + "Added to list : " + print + "\n"
                + "--------------------------------------\n";
        System.out.println(returnStr);
        return returnStr;
    }

    /**
     * Prints number of tasks left.
     * @param listSize number of tasks left.
     */
    public String printNumberOfTasks(int listSize) {
        String returnStr;
        returnStr = "You have a total of " + listSize + " tasks" + "\n"
                + "--------------------------------------";
        System.out.println(returnStr);
        return returnStr;
    }

    /**
     * Returns Goodbye message.
     * @return Bye message.
     */
    public String byeMessage() {
        String returnStr = "Doge would like to see you soon!";
        if (scanner != null) {
            scanner.close();
        }
        return returnStr;
    }
}
