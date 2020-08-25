import java.util.ArrayList;

public class Ui {
    public Ui() {
        
    }
    
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printLine(logo + "\nHello! I'm Duke" + "\nWhat can I do for you?");
    }
    
    public void printLine(String string) {
        System.out.println(string);
    }
    
    public String drawBorder(String string) {
        String line = "_________________________________________________________________";
        return line + "\n" + string + "\n" + line;
    }
    
    public void bye() {
        printLine(drawBorder("Bye. Hope to see you again soon!"));
    }
    
    public void printAddTask(Task task, TaskList lst) {
        printLine(drawBorder("Got it. I've added this task:\n" + "  " + task.toString() + "\n" +
                "Now you have " + lst.getSize() + " tasks in the list."));
    }
    
    public void printDoneTask(Task task) {
        printLine(drawBorder("Nice! I've marked this task as done: \n" + "  " +
                task.toString()));
    }
    
    public void printDeleteTask(Task task, TaskList lst) {
        printLine(drawBorder("Noted. I've removed this task:\n" + "  " +
                task.toString() + "\n" +
                "Now you have " + lst.getSize() + " tasks in the list."));
    }
    
    public void printTaskList(TaskList lst) {
        printLine(drawBorder( "Here are the tasks in your list:\n" + lst.toString()));
    }
    
    public void printMatchingTasks(TaskList lst) {
        printLine(drawBorder( "Here are the matching tasks in your list:\n" + lst.toString()));
    }
}
