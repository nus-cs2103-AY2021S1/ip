import java.util.Scanner;
import ChatbotPkg.*;

public class Chatbot {

    private static TaskManager tskManager;

    private static boolean getUserInput(Scanner s) {
        return s.hasNextLine();
    }

    private static void handleUserInput(String userInput) {

        TaskPrinter tskPrint = new TaskPrinter();
        String text = userInput.trim();
        String leading = text.split(" ")[0].trim();
        String trailing = text.substring(leading.length()).trim();

        switch (leading) {
            case "list":
                tskManager.listAll();
                break;
            case "done":
                int index = Integer.parseInt(text.split(" ")[1]) - 1;
                tskManager.markAsDone(index);
                break;
            case "todo":
                try {
                    Todo task = Todo.newTodo(trailing);
                    tskManager.addTask(task);
                } catch (ChatbotException e) {
                    tskPrint.display(e.getMessage());
                }
                break;
            case "deadline":
                Deadline deadline = new Deadline(trailing);
                tskManager.addTask(deadline);
                break;
            case "event":
                Event event = new Event(trailing);
                tskManager.addTask(event);
                break;
            case "bye":
                tskPrint.display("Bye. Hope to see you again soon!");
                break;
            default:
                tskPrint.display("Arghh! I do not know what you mean, are you using the right\n    commands?");
        }
    }

    public static void main(String[] args) {

        System.out.println(
                "   ################################################\n" +
                "   #                                              #\n" +
                "   #  Hey there, I'm Hanry the impatient ChatBot. #\n" +
                "   #  What can I do for you? (-.-)                #\n" +
                "   #                                              #\n" +
                "   ################################################"
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
