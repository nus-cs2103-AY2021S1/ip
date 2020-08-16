import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private final List<Task> list;

    public Duke() {
        this.list = new ArrayList<>();
    }

    public static void main(String[] args) {
        Duke session = new Duke(); // start a new session with JonasBot
        session.greet(); // greet the user
        session.execute(); // execute the bot to perform intended functions
        session.end(); // end the current session with JonasBot
    }

    public void greet() {
        String divider = "----------------------------------------";
        String greeting = "  Hello! I am JonasBot! Nice to meet you :) \n" +
                "  \n  I am a bot that will keep track of all your tasks. \n" +
                "  \n  Below is a list of all my functions: \n" +
                "  1. Create a new task \n" +
                "\t  1.1 Todo: Input 'todo' followed by the todo description. For eg, todo eat \n" +
                "\t  1.2 Deadline: Input 'deadline' followed by task description, then a '/by' and lastly the due date." +
                " For eg, deadline return book /by Sunday \n" +
                "\t  1.3 Event: Input 'event', followed by task description, then a '/at' and lastly the event time." +
                " For eg, event project meeting /at Mon 2-4pm \n" +
                "  \n  2. To display all tasks in your list, input 'list' \n" +
                "  \n  3. To mark a task as completed, input 'done' followed by the task ID in the list. For eg, 'done 2' \n" +
                "  \n  4. To end this chat, input 'bye' \n" +
                "  \n  Now that you are familiar with the commands, how may I assist you today?";
        System.out.println(divider);
        System.out.println(greeting);
        System.out.println(divider);
    }

    public void execute() {
        String divider = "----------------------------------------";
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        while (!message.equals("bye")) {
            System.out.println(divider);
            if (message.equals("list") && !this.list.isEmpty()) {
                System.out.println("Here is a list of all your tasks:");
                for (int i = 0; i < this.list.size(); i ++) {
                    int index = i + 1;
                    System.out.println("\t" + String.format("%d. %s", index, this.list.get(i)));
                }
            } else if (message.equals("list")) {
              System.out.println("Your list is empty. Add a new task!");
            } else if (message.split(" ")[0].equals("done")) {
                int index = Integer.parseInt(message.split(" ")[1]);
                if (index > this.list.size()) {
                    System.out.println("The task does not exist. Try again!");
                } else {
                    this.list.get(index - 1).markAsDone();
                    System.out.println("  Nice! I've marked this task as done:");
                    System.out.println("\t" + this.list.get(index - 1));
                }
            } else {
                createTask(message);
            }
            System.out.println(divider);
            message = sc.nextLine();
        }
    }

    public void createTask(String message) {
        String taskType = message.split(" ")[0];
        String description = "";
        String time = "";
        Task toAdd = null;
        if (taskType.equals("todo")) {
            description = message.split("todo")[1];
            toAdd = new Todo(description);
        } else if (taskType.equals("deadline")) {
            String info = message.split("deadline")[1];
            description = info.split("/by")[0];
            time = info.split("/by")[1];
            toAdd = new Deadline(description, time);
        } else if (taskType.equals("event")) {
            String info = message.split("event")[1];
            description = info.split("/at")[0];
            time = info.split("/at")[1];
            toAdd = new Event(description, time);
        }
        this.list.add(toAdd);
        System.out.println("  Success! This task has been added:");
        System.out.println("\t" + toAdd);
        System.out.println("  You have " + list.size() + " tasks in your list now.");
    }

    public void end() {
        String divider = "----------------------------------------";
        System.out.println(divider);
        System.out.println("  GoodBye and I hope to see you soon! Have a fantastic day! ");
        System.out.println(divider);
    }

}
