import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui(){
        sc = new Scanner(System.in);
    }

    public String uiForAdd(TaskList tasks, Task task){
        String output = "Got it. I've added this task:"
                + "\n       " + task
                + String.format("\n   Now you have %d task(s) in the list.", tasks.getSize());
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

        String greeting =
                "\n   Hi, I'm Duke!"
                + "\n   How can I help you today?";
        System.out.println(greeting);
    }

    public String bye(){
        String output = "\n   " + "Bye. Hope to see you again soon!"
                + "\n   ";
        System.out.println(output);

        return output;
    }

    public String uiForDone(Task task){
        String output = "\n   " + "Nice! I've marked this task as done:"
                + "\n   " + task;
        System.out.println(output);
        return output;
    }

    public String uiForDelete(TaskList tasks, Task task){
        String output ="\n   " + "Noted. I've removed this task: "
                + "\n   " + task
                + String.format("\n   Now you have %d task(s) in the list.", tasks.getSize());
        System.out.println(output);
        return output;
    }

    public String uiForList(TaskList tasks){
        if(tasks.getSize() == 0){
            System.out.println("\n   " + "You have no tasks");
            return "no tasks";
        } else {
            StringBuilder output = new StringBuilder("   ");
            for (int i = 0; i < tasks.getSize(); i++) {
                output.append("\n    ").append(i + 1).append(". ").append(tasks.getTask(i + 1));
            }
            System.out.println(output);
            return output.toString();
        }
    }

    public String uiForFind(TaskList tasks){
        if(tasks.getSize() == 0){
            return ("\n   " + "You have no tasks");
        } else {
            StringBuilder output = new StringBuilder("\n Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.getSize(); i++) {
                output.append("\n    ").append(i + 1).append(". ").append(tasks.getTask(i + 1));
            }
            output.append("\n   ");
            System.out.println(output);
            return output.toString();
        }
    }

    public String uiForError(){
        return "";
    }

    public String read(){
        return sc.nextLine();
    }

}
