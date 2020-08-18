import java.util.Scanner;
import java.util.ArrayList;

public class Mocha {
    public static void main(String[] args) {
        // Contains all tasks
        ArrayList<String> listOfTasks = new ArrayList<>();

        // Introduction of Mocha
        String horizontalLine = "_______________________________________________________";
        String nameIntro = "Hello, I'm Mocha!";
        String greeting = "What's up today!";
        System.out.println(horizontalLine + "\r\n" + nameIntro + "\r\n"
                + greeting + "\r\n" + horizontalLine + "\r\n");

        Scanner userCommand = new Scanner(System.in);

        while (userCommand.hasNext()) {
            // Any command other than "list" and "bye" - add into listOfTasks
            if (!userCommand.hasNext("list") && !userCommand.hasNext("bye")) {
                String itemToBeAdded = userCommand.nextLine();
                System.out.println(horizontalLine + "\r\n" + "added: "
                        + itemToBeAdded + "\r\n" + horizontalLine + "\r\n");
                listOfTasks.add(itemToBeAdded);
            }

            // "list" command - displays all tasks
            if (userCommand.hasNext("list")) { // Process closing here
                System.out.println(horizontalLine + " \r\n");
                for (int i = 0; i < listOfTasks.size(); i++) {
                    System.out.println((i + 1) + ". " + listOfTasks.get(i));
                }
                System.out.println(horizontalLine + " \r\n");
                userCommand.nextLine();
            }

            // Mocha's farewell
            if (userCommand.hasNext("bye")) {
                System.out.println(horizontalLine + "\r\n" + "Bye! See ya soon!" + "\r\n" + horizontalLine);
                userCommand.nextLine();
            }
        }
    }
}
