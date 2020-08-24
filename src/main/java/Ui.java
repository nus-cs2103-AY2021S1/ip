import java.util.Scanner;

public class Ui {
    
    private static String greeting = "Hello~ I'm Duke!\n" + "What can I do for you?";
    private static String farewell = "Goodbye~";
    private static String doneMessage = "Nice! I've set this task as done~";
    private static String addTaskMessage = "Got it~ I've added this task:";
    private static String numberOfTaskMessage = "You now have %d tasks in the list~";
    private static String removeTaskMessage = "Alright~ I've removed this task:";
    private static String seperator = "____________________________________________________________";
    private static String listMessage = "Here are your tasks~";
    
    private Scanner sc;
    
    public Ui() {
        sc = new Scanner(System.in);
    }
    
    public void greet() {
        System.out.println(greeting);
    }
    
    public void farewell() {
        System.out.println(seperator + "\n" + farewell + "\n" + seperator);
    }
    
    public void doneText(Task doneTask) {
        System.out.println(seperator + "\n" + doneMessage + "\n" + doneTask + 
                "\n" + seperator);
    }
    
    public void addTaskText(Task addTask, TaskList result) {
        System.out.println(seperator + "\n" + addTaskMessage + "\n" + addTask +
                "\n" + String.format(numberOfTaskMessage, result.getSize()) + 
                "\n" + seperator);
    }
    
    public void deleteTaskText(Task deleteTask, TaskList result) {
        System.out.println(seperator + "\n" + removeTaskMessage + "\n" + deleteTask +
                "\n" + String.format(numberOfTaskMessage, result.getSize()) +
                "\n" + seperator);
    }
    
    public void listText(TaskList taskList) {
        System.out.println(seperator + "\n" + listMessage + "\n");
        taskList.printTaskList();
        System.out.println("\n" + seperator);
    }
    
    public void printError(Exception e) {
        System.out.println(e);
    } 
    
    public String readCommand() {
        return sc.nextLine().toLowerCase();
    }
    
    
}
