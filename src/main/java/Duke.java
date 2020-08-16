import java.util.Arrays;
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
                String[] doneInputs = input.split(" ");
                int taskToDo = Integer.parseInt(doneInputs[1]);
                currentList.get(taskToDo - 1).markDone();
                System.out.println(lines);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + currentList.get(taskToDo - 1));
                System.out.println(lines);
            } else if (input.contains("todo")) {
                //process todo task
                String taskToDo = input.substring(5);
                ToDo newToDo = new ToDo(taskToDo);
                currentList.add(newToDo);
                System.out.println(lines + "\n" + "     Got it. I've added this task:");
                System.out.println("       " + newToDo);
                System.out.println("     Now you have " + String.valueOf(currentList.size()) + " tasks in the list.");
                System.out.println(lines);
            } else if (input.contains("deadline")) {
                String deadlineInput = input.substring(9);
                int indexSeparator = deadlineInput.indexOf("/by");
                String deadlineTaskName = deadlineInput.substring(0, indexSeparator - 1);
                String deadlineTime = deadlineInput.substring(indexSeparator + 4);
                Deadline newDeadline = new Deadline(deadlineTaskName, deadlineTime);
                currentList.add(newDeadline);
                System.out.println(lines + "\n" + "     Got it. I've added this task:");
                System.out.println("       " + newDeadline);
                System.out.println("     Now you have " + String.valueOf(currentList.size()) + " tasks in the list.");
                System.out.println(lines);
            } else if (input.contains("event")) {
                String eventInput = input.substring(6);
                int indexSeparator = eventInput.indexOf("/at");
                String eventTaskName = eventInput.substring(0, indexSeparator - 1);
                String eventTime = eventInput.substring(indexSeparator + 4);
                Event newEvent = new Event(eventTaskName, eventTime);
                currentList.add(newEvent);
                System.out.println(lines + "\n" + "     Got it. I've added this task:");
                System.out.println("       " + newEvent);
                System.out.println("     Now you have " + String.valueOf(currentList.size()) + " tasks in the list.");
                System.out.println(lines);
            } else if (input.equals("list")){
                //list out items in to-do list
                System.out.println(lines);
                System.out.println("     Here are the tasks in your list:");
                for(int i = 0; i < currentList.size(); i++) {
                    String currentLine = "      "+ (i + 1) + ". " + currentList.get(i);
                    System.out.println(currentLine);
                }
                System.out.println(lines);
            }
            input = sc.nextLine();
        }
        //greet user goodbye
        String end_Greeting = lines + "\n" + "     Bye. Hope to see you again soon!\n" + lines;
        System.out.println(end_Greeting);

    }
}
