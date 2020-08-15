import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> myTasks = new ArrayList<>();

    public static void greet(){
        String greeting = "\n   _________________________________________________________________"
                + "\n   Hi, I'm Duke!"
                + "\n   How can I help you today?"
                + "\n   ______________________________________________________________________";
        System.out.println(greeting);
    }

    public static void addTask(String input){
        myTasks.add(new Task(input));
        String output = "   ______________________________________________________________"
                        + "\n   added: " + input
                        + "\n   ______________________________________________________________";
        System.out.println(output);
    }

    public static void listTasks(){
        String output = "   ______________________________________________________________";
        for(int i = 0; i < myTasks.size(); i++){
            output +="\n    " + (i + 1) +". " + myTasks.get(i);
        }
        output += "\n   ______________________________________________________________";
        System.out.println(output);
    }

    public static void bye(){
        String output = "   ______________________________________________________________"
                + "\n   " + "Bye. Hope to see you again soon!"
                + "\n   ______________________________________________________________";
        System.out.println(output);
    }

    private static void markAsDone(int i) {
        Task task = myTasks.get(i-1);
        task.markDone();
        String output = "   ______________________________________________________________"
                + "\n   " + "Nice! I've marked this task as done:"
                + "\n   " + task
                + "\n   ______________________________________________________________";
        System.out.println(output);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine();
            String firstWord = "";
            int i = 0;
            if(input.contains(" ")) {
                i = input.indexOf(" ");
                firstWord = input.substring(0, i);
            }
            if(input.equals("bye") || input.equals("Bye")){
                bye();
                break;
            } else if(input.equals("list")){
                listTasks();
            } else if (firstWord.equals("done")){
                int index = Integer.parseInt(input.substring(i+1));
                markAsDone(index);
            }
            else {
                addTask(input);
            }
        }

    }

}
