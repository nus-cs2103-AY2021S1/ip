import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm Duke!");
        System.out.println("What can I do for you?");

        List savedItems = new ArrayList<String>();

        String userInput;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");
            userInput = scanner.nextLine();

            switch(userInput) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                default:
                    savedItems.add(userInput);
                    System.out.println("added: " + userInput);
            }
        }
    }
}
