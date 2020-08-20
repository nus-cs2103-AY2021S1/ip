import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public static void displayMessage(String s) {
        System.out.println("\t---------------------------\n\t" +
                s +
                "\n\t---------------------------"
        );
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List list1 = List.startList();

        displayMessage("Hello! I'm Duke\n\tWhat can I do for you?");
        String input;
        while (true) {
            input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                displayMessage("Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                String listString = list1.listToString();
                displayMessage(listString);
            } else {
                String addedMsg = list1.addToList(input);
                displayMessage(addedMsg);
            }
        }
    }
}
