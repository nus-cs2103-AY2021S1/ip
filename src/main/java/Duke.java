import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<String> inputList = new ArrayList<>(); // List of all input items from user

    public static void main(String[] args) {
        String logo = "Hans ㋡";
                /*"_   _        _        \n"
                   + "| |_| | ___  _| | _____ \n"
                   + "|  ㋡ | | | | | |/ / _ \\\n"
                   + "| |_| | |_| |   <  __/\n"
                   + "|_| |_|/ \\_|,_|_|\\_\\___|\n"; */
        System.out.println("Hello from " + logo + "\n" + "How may I be of service " +
                "to you this fine day sire?");

        awaitInstructions();
    }

    private static void awaitInstructions() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String userInput =  sc.nextLine();

            switch (userInput) { // Determine output from user input
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    listAllItems();
                    break;
                default:
                    addItem(userInput);
                    break;
            }
        }
    }

    public static void addItem(String userInput) {
        Duke.inputList.add(userInput);
        System.out.println("added: " + userInput);
    }

    private static void listAllItems() {
        ArrayList<String> currList = Duke.inputList;
        currList.forEach(item ->
                System.out.println((currList.indexOf(item) + 1) + ". " + item));
    }

    private static void echo(String userInput) {
        System.out.println(userInput);
    }
}
