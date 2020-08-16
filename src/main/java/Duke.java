import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static String LINE = "_______________________________________________";
    private static String ADDED = "added: ";

    private final Scanner sc;
    private ArrayList<String> listItems;

    Duke() {
        sc = new Scanner(System.in);
        listItems = new ArrayList<>();
    }

    // activate the Duke Bot
    void echo() {
        greetings();
        while(sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                goodBye();
                break;
            } else if (input.equals("list")) {
                showListItems(listItems);
            } else {
                listItems.add(input);
                messageFormatter(() -> System.out.println(ADDED + input));
            }
        }
    }

    void greetings() {
        messageFormatter(() -> {
            System.out.println("Hello! I'm Duke ^.^");
            System.out.println("What can I do for you?");
        });
    }

    void goodBye() {
        messageFormatter(() -> System.out.println("Bye ^.^, Hope to see you again soon!!!"));
    }

    void messageFormatter(Runnable func) {
        System.out.println(LINE);
        func.run();
        System.out.println(LINE);
        System.out.println(); // optional
    }

    // Printing out the items in the list
    void showListItems(ArrayList<String> listItems) {
        if (listItems.size() == 0) {
            messageFormatter(() -> System.out.println("Your list is empty!!!"));
        } else {
            messageFormatter(() -> {
                for(int i = 1; i <= listItems.size(); i++) {
                    System.out.println(i + ". " + listItems.get(i - 1));
                }
            });
        }
    }

}
