import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String lines = "    ____________________________________________________________";
        String defaultGreeting = lines + "\n" + "     Hello! I'm Duke \n" + "     What can I do for you?\n" + lines + "\n";
        System.out.println(defaultGreeting);
        Scanner sc = new Scanner(System.in);
        List<Task> currentList = new ArrayList<>();
        String input = sc.nextLine();
        while (!input.equals("bye")){
            if (input.contains("done")) {
                String[] inputs = input.split(" ");
                int taskToDo = Integer.parseInt(inputs[1]);
                currentList.get(taskToDo - 1).markDone();
                System.out.println(lines);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + currentList.get(taskToDo - 1));
            } else if (!input.equals("list")) {
                //echo back the responses
                String response = lines + "\n      added: " + input + "\n" + lines + "\n";
                System.out.println(response);
                currentList.add(new Task(input));
            } else if (input.equals("list")){
                //list out items in to-do list
                System.out.println(lines);
                System.out.println("      Here are the tasks in your list:");
                for(int i = 0; i < currentList.size(); i++) {
                    String currentLine = "      "+ (i + 1) + ". " + currentList.get(i);
                    System.out.println(currentLine);
                }
                System.out.println(lines);
            }
            input = sc.nextLine();
        }
        //greet user goodbye
        String end_Greeting = lines + "\n" + "      Bye. Hope to see you again soon!\n" + lines;
        System.out.println(end_Greeting);

    }
}
