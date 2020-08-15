import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> myTasks = new ArrayList<>();

    public static void greet(){
        String greeting = "\n   _________________________________________________________________"
                + "\n   Hi, I'm Duke!"
                + "\n   How can I help you today?"
                + "\n   _________________________________________________________________";
        System.out.println(greeting);
    }


    public static void addTask(Task task){
        myTasks.add(task);
        String output = "   ______________________________________________________________"
                        + "\n   Got it. I've added this task:"
                        + "\n       " + task
                        + String.format("\n   Now you have %d tasks in the list.", myTasks.size())
                        + "\n   ______________________________________________________________";
        System.out.println(output);
    }

    public static void listTasks(){
        StringBuilder output = new StringBuilder("   ______________________________________________________________");
        for(int i = 0; i < myTasks.size(); i++){
            output.append("\n    ").append(i + 1).append(". ").append(myTasks.get(i));
        }
        output.append("\n   ______________________________________________________________");
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
            else if(firstWord.equals("todo")) {
                String name = input.substring(i+1);
                Task task = new TodoTask(name);
                addTask(task);
            } else if(firstWord.equals("deadline")){
                int t = input.indexOf("/");
                String name = input.substring(i+1, t);
                String time = input.substring(t+4);
                Task task = new DeadlineTask(name, time);
                addTask(task);
            }else if(firstWord.equals("event")){
                int t = input.indexOf("/");
                String name = input.substring(i+1, t);
                String time = input.substring(t+4);
                Task task = new EventTask(name, time);
                addTask(task);
            }
        }

    }

}
