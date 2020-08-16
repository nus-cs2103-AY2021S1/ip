import main.java.Deadline;
import main.java.Event;
import main.java.Task;
import main.java.Todo;

import java.util.Scanner;
import java.util.ArrayList;
public class Bob {
    static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetings = "Hello! I'm Bob\nWhat can I do for you?";
        String exit = "Bye! Hope to see you again.";
        System.out.println("Hello from\n" + logo);
        System.out.println(greetings);
        String command = sc.nextLine();
        while(!command.equals("bye")) {
            respond(command);
            command = sc.nextLine();
        }
        System.out.println(exit);
    }
    static void respond(String command) {
        if (command.equals("list")) {
            int count = 1;
            for(Task item: list) {
                System.out.println(count +"." + item.toString());
                count++;
            }
        } else if(command.contains("done")) {
            int index = Integer.parseInt(command.substring(command.length()-1));
            Task task = list.get(index-1);
            task.markAsDone();
            System.out.println("Good job! I have marked this task as done:");
            System.out.println("\t" + index + "." + task.toString());
        } else if (command.contains("todo") || command.contains("deadline") || command.contains("event")){
            Task task = null;
            if (command.contains("todo")) {
                task = new Todo(command.substring(5));
            } else if (command.contains("deadline")) {
                int index = command.indexOf(47);
                task = new Deadline(command.substring(9, index), command.substring(index + 4));
            } else if(command.contains("event")) {
                int index = command.indexOf(47);
                task = new Event(command.substring(6, index), command.substring(index + 4));
            }
            list.add(task);
            System.out.println("Got it! I have added a new task to the list.");
            System.out.println("added: " + task.toString());
        } else {
            System.out.println("Sorry,I do not understand your request.");
        }
    }
}
