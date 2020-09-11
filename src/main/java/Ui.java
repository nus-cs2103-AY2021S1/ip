import java.io.IOException;
import java.util.Scanner;

public class Ui {
    
    public String showLoadingError() {
        return "ERROR: file does not exist.";
    }
    
    public String showError(String msg) {
        return msg;
    }
    
    public String showWelcome() {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        String greeting = "Hello! I'm Duke \n" + "What can I do for you?";
//        System.out.println("Hello from\n" + logo + "\n" + greeting);
        return "Welcome!";
    }

    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }
    
    public String showLine() {
        return "    ____________________________________________________________\n";
    }
    
    public String showDeletedTask(Task task, int length) {
        assert length >= 0 : "length must not be negative";
        return "Noted. I've removed this task:\n" + task + "\n" 
                + "Now you have " + length + " tasks in the list.\n";
    }
    
    public String showAddedTask(Task task, int length) {
        assert length >= 0 : "length must not be negative";
        return "Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + length + " tasks in the list.\n";
    }
    
    public String showDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }
    
    public String showList(TaskList taskList) {
        StringBuilder display = new StringBuilder();
        if (taskList.taskListLength() == 0) {
            return "You have no tasks!";
        } else {
            display.append("Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.taskListLength(); i++) {
                Task task = taskList.getTaskList().get(i);
                display.append(i + 1).append(". ").append(task).append("\n");
            }
        }    
        return display.toString();
    } 
    
    public String showSortedList(TaskList taskList) {
        return "Here is your sorted list:\n" + showList(taskList);
    }
}
