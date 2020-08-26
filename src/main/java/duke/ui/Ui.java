package duke.ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    
    private static final String MESSAGE_GREETING = "Hello~ I'm Duke!\n" + "What can I do for you?";
    private static final String MESSAGE_FAREWELL = "Goodbye~";
    private static final String MESSAGE_DONE = "Nice! I've set this task as done~";
    private static final String MESSAGE_ADD_TASK = "Got it~ I've added this task:";
    private static final String MESSAGE_NUMBER_OF_TASKS = "You now have %d tasks in the list~";
    private static final String MESSAGE_REMOVE_TASK = "Alright~ I've removed this task:";
    private static final String MESSAGE_SEPERATOR = "____________________________________________________________";
    private static final String MESSAGE_LIST = "Here are your tasks~";
    
    private Scanner sc;
    
    public Ui() {
        sc = new Scanner(System.in);
    }
    
    public void greet() {
        System.out.println(MESSAGE_GREETING);
    }
    
    public void farewell() {
        System.out.println(MESSAGE_SEPERATOR + "\n" + MESSAGE_FAREWELL + "\n" + MESSAGE_SEPERATOR);
    }
    
    public void doneText(Task doneTask) {
        System.out.println(MESSAGE_SEPERATOR + "\n" + MESSAGE_DONE + "\n" + doneTask + 
                "\n" + MESSAGE_SEPERATOR);
    }
    
    public void addTaskText(Task addTask, TaskList result) {
        System.out.println(MESSAGE_SEPERATOR + "\n" + MESSAGE_ADD_TASK + "\n" + addTask +
                "\n" + String.format(MESSAGE_NUMBER_OF_TASKS, result.getSize()) + 
                "\n" + MESSAGE_SEPERATOR);
    }
    
    public void deleteTaskText(Task deleteTask, TaskList result) {
        System.out.println(MESSAGE_SEPERATOR + "\n" + MESSAGE_REMOVE_TASK + "\n" + deleteTask +
                "\n" + String.format(MESSAGE_NUMBER_OF_TASKS, result.getSize()) +
                "\n" + MESSAGE_SEPERATOR);
    }
    
    public void listText(TaskList taskList) {
        System.out.println(MESSAGE_SEPERATOR + "\n" + MESSAGE_LIST + "\n");
        taskList.printTaskList();
        System.out.println("\n" + MESSAGE_SEPERATOR);
    }
    
    public void printError(Exception e) {
        System.out.println(e);
    } 
    
    public String readCommand() {
        return sc.nextLine().toLowerCase();
    }
    
    
}
