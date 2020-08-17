import java.util.ArrayList;
import java.util.Scanner;

public class Chatbot {

    private static TaskManager tskManager;

    private static boolean getUserInput(Scanner s) {
        System.out.print(">> ");
        return s.hasNextLine();
    }

    private static void handleUserInput(String userInput) {
        String text = userInput.trim();
        String first = text.split(" ")[0];
        String remainder = text.substring(first.length()).trim();
        switch (first) {
            case "list":
                tskManager.listAll();
                break;
            case "done":
                int index = Integer.parseInt(text.split(" ")[1]) - 1;
                tskManager.markAsDone(index);
                break;
            case "todo":
                Task todo = new Todo(remainder);
                tskManager.addTask(todo);
                break;
            case "deadline":
                Task deadline = new Deadline(remainder);
                tskManager.addTask(deadline);
                break;
            case "event":
                Task event = new Event(remainder);
                tskManager.addTask(event);
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            default:
                // todo
        }
    }

    public static void main(String[] args) {

        System.out.println(
                "   ##############################\n" +
                "   # Hi! I'm Hanry the ChatBot. #\n" +
                "   # What can I do for you?     #\n" +
                "   ##############################"
        );

        tskManager = TaskManager.getInstance();
        Scanner sc = new Scanner(System.in);

        while(getUserInput(sc)) {
            String userInput = sc.nextLine();
            handleUserInput(userInput);
        }

        sc.close();
    }
}
