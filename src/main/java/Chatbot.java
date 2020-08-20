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
        Command cmd = Command.NONE;
        try {
             cmd = Command.valueOf(leading.toUpperCase());
        } catch (IllegalArgumentException e){
            tskPrint.display("Arghh! I do not know what you mean, are you using the right\n    commands?");
        }

        switch (cmd) {
            case LIST:
                tskManager.listAll();
                break;
            case DONE:
                int ind1 = Integer.parseInt(text.split(" ")[1]) - 1;
                tskManager.markAsDone(ind1);
                break;
            case TODO:
                try {
                    Todo task = Todo.newTodo(trailing);
                    tskManager.addTask(task);
                } catch (ChatbotException e) {
                    tskPrint.display(e.getMessage());
                }
                break;
            case DEADLINE:
                Deadline deadline = new Deadline(trailing);
                tskManager.addTask(deadline);
                break;
            case EVENT:
                Event event = new Event(trailing);
                tskManager.addTask(event);
                break;
            case DELETE:
                int ind2 = Integer.parseInt(text.split(" ")[1]) - 1;
                tskManager.removeTask(ind2);
                break;
            case BYE:
                tskPrint.display("Bye, hope to see you again soon.");
                break;
            default:
                break;
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
