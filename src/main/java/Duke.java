import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;

public class Duke {

    private static final String LINE = "_______________________________________________";
    private static final String ADDED = "added: ";

    private final Scanner sc;
    private ArrayList<Task> listTasks;

    Duke() {
        this.sc = new Scanner(System.in);
        this.listTasks = new ArrayList<>();
    }

    // activate the Duke Bot
    public void echo() {
        greetings();
        while(sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputArr = input.trim().split(" ", 2);
            if (inputArr[0].equals("bye")) {
                goodBye();
                break;
            } else if (inputArr[0].equals("list")) {
                showListTasks(listTasks);
            } else if (inputArr[0].equals("done") && isNumber(inputArr[1])) {
                int num = parseInt(inputArr[1]);
                int size = listTasks.size();
                if (num <= 0) {
                    messageFormatter(() -> System.out.println("Invalid input!"));
                } else if (size < num) {
                    messageFormatter(() -> System.out.printf("You have only %d task in your list\n", size));
                } else {
                    Task task = listTasks.get(num - 1);
                    messageFormatter(() -> task.markAsDone());
                }
            } else {
                Task task = new Task(input);
                listTasks.add(task);
                messageFormatter(() -> System.out.println(ADDED + input));
            }
        }
    }

    private void greetings() {
        messageFormatter(() -> {
            System.out.println("Hello! I'm Duke ^.^");
            System.out.println("What can I do for you?");
        });
    }

    private void goodBye() {
        messageFormatter(() -> System.out.println("Bye ^.^, Hope to see you again soon!!!"));
    }

    // Formatter to format any message. Easily customizable
    private void messageFormatter(Runnable func) {
        System.out.println(LINE);
        func.run();
        System.out.println(LINE);
        System.out.println(); // optional
    }

    // Printing out the items in the list
    private void showListTasks(ArrayList<Task> listTasks) {
        if (listTasks.size() == 0) {
            messageFormatter(() -> System.out.println("Your list is empty!!!"));
        } else {
            messageFormatter(() -> {
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i <= listTasks.size(); i++) {
                    System.out.println(i + ". " + listTasks.get(i - 1));
                }
            });
        }
    }

    private boolean isNumber(String str) {
        try {
            int num = parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
