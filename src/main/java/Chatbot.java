import java.util.ArrayList;
import java.util.Scanner;

public class Chatbot {

    private static ArrayList<String> tasks;
    private static TaskPrinter taskPrinter;

    private static boolean getUserInput(Scanner s) {
        System.out.print(">> ");
        return s.hasNextLine();
    }

    private static void handleUserInput(String userInput) {
        switch (userInput) {
            case "list":
                taskPrinter.list(tasks);
                break;
            case "bye":
                taskPrinter.display("Bye. Hope to see you again soon!");
                break;
            default:
                tasks.add(userInput);
                taskPrinter.display("Added: " + userInput);
        }
    }

    public static void main(String[] args) {

        System.out.println(
                "   ##############################\n" +
                "   # Hi! I'm Hanry the ChatBot. #\n" +
                "   # What can I do for you?     #\n" +
                "   ##############################"
        );

        tasks = new ArrayList<>();
        taskPrinter = new TaskPrinter();
        Scanner sc = new Scanner(System.in);

        while(getUserInput(sc)) {
            String userInput = sc.nextLine();
            handleUserInput(userInput);
        }

        sc.close();
    }
}
