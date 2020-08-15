import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {

        String divider = "----------------------------------------";
        greet(); // greet the user
        execute(); // execute the bot to perform intended functions

        // end the session
        System.out.println(divider);
        System.out.println("\t" + "GoodBye and I hope to see you soon! Have a fantastic day! ");
        System.out.println(divider);

    }

    public static void greet() {
        String divider = "----------------------------------------";
        String greeting = "  Hello! I am JonasBot! Nice to meet you :) \n" +
                "  I am a bot that will keep track of all your tasks. \n" +
                "  Below is a list of all my functions: \n" +
                "  You are able to enter a new task \n" +
                "  To display all tasks in your list, input 'list' \n" +
                "  To mark a task as completed, input done followed by the task ID in the list. For eg, 'done 2' \n" +
                "  To end this chat, input 'bye' \n" +
                "  How may I assist you today?";
        System.out.println(divider);
        System.out.println(greeting);
        System.out.println(divider);
    }

    public static void execute() {

        List<Task> list = new ArrayList<>();
        String divider = "----------------------------------------";
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        while (!message.equals("bye")) {
            System.out.println(divider);
            if (message.equals("list") && !list.isEmpty()) {
                System.out.println("Here is a list of all your tasks:");
                for (int i = 0; i < list.size(); i ++) {
                    int index = i + 1;
                    System.out.println("\t" + String.format("%d. %s", index, list.get(i)));
                }
            } else if (message.equals("list") && list.isEmpty()) {
              System.out.println("Your list is empty. Add a new task!");
            } else if (message.split(" ")[0].equals("done")) {
                // consider adding a feature for marking tasks as done when they are already completed
                int index = Integer.parseInt(message.split(" ")[1]);
                if (index > list.size()) {
                    System.out.println("The task does not exist. Try again!");
                } else {
                    list.get(index - 1).markAsDone();
                    System.out.println("\t" + "Nice! I've marked this task as done:");
                    System.out.println("\t  " + list.get(index - 1));
                }
            } else {
                Task toAdd = new Task(message);
                list.add(toAdd);
                System.out.println("\t" + "added: " + message);
            }
            System.out.println(divider);
            message = sc.nextLine();
        }
    }

}
