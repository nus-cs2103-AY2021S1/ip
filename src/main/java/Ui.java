import java.util.Scanner;

public class Ui {

    private Scanner sc;

    private static final String HORIZONTAL_LINE = "_________________________________________________________________";

    public Ui(){
        sc = new Scanner(System.in);
    }

    public String uiForAdd(TaskList tasks, Task task){
        String output = "   " + HORIZONTAL_LINE
                + "\n   Got it. I've added this task:"
                + "\n       " + task
                + String.format("\n   Now you have %d task(s) in the list.", tasks.getSize())
                + "\n   " + HORIZONTAL_LINE;
        System.out.println(output);
        return output;
    }

    public void greet(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "\n   " + HORIZONTAL_LINE
                + "\n   Hi, I'm Duke!"
                + "\n   How can I help you today?"
                + "\n   " + HORIZONTAL_LINE;
        System.out.println(greeting);
    }

    public String bye(){
        String output = "   " + HORIZONTAL_LINE
                + "\n   " + "Bye. Hope to see you again soon!"
                + "\n   " + HORIZONTAL_LINE;
        System.out.println(output);
        return output;
    }

    public String uiForDone(Task task){
        String output = "   " + HORIZONTAL_LINE
                + "\n   " + "Nice! I've marked this task as done:"
                + "\n   " + task
                + "\n   " + HORIZONTAL_LINE;
        System.out.println(output);
        return output;
    }

    public String uiForDelete(TaskList tasks, Task task){
        String output = "   " + HORIZONTAL_LINE
                + "\n   " + "Noted. I've removed this task: "
                + "\n   " + task
                + String.format("\n   Now you have %d task(s) in the list.", tasks.getSize())
                + "\n   " + HORIZONTAL_LINE;
        System.out.println(output);
        return output;
    }

    public String uiForList(TaskList tasks){
        if(tasks.getSize() == 0){
            System.out.println("   " + HORIZONTAL_LINE
                    + "\n   " + "You have no tasks"
                    + "\n   " + HORIZONTAL_LINE);
            return "no tasks";
        } else {
            StringBuilder output = new StringBuilder("   " + HORIZONTAL_LINE);
            for (int i = 0; i < tasks.getSize(); i++) {
                output.append("\n    ").append(i + 1).append(". ").append(tasks.getTask(i + 1));
            }
            output.append("\n   " + HORIZONTAL_LINE);
            System.out.println(output);
            return output.toString();
        }
    }

    public String uiForFind(TaskList tasks){
        if(tasks.getSize() == 0){
            return ("   " + HORIZONTAL_LINE
                    + "\n   " + "You have no tasks"
                    + "\n   " + HORIZONTAL_LINE);
        } else {
            StringBuilder output = new StringBuilder("   " + HORIZONTAL_LINE + "\n Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.getSize(); i++) {
                output.append("\n    ").append(i + 1).append(". ").append(tasks.getTask(i + 1));
            }
            output.append("\n   " + HORIZONTAL_LINE);
            System.out.println(output);
            return output.toString();
        }
    }

    public String read(){
        return sc.nextLine();
    }

}
