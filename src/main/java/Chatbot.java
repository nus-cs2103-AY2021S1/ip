import java.util.ArrayList;
import java.util.Scanner;

public class Chatbot {

    private static ArrayList<Task> tasks;
    private static TaskPrinter taskPrinter;

    private static boolean getUserInput(Scanner s) {
        System.out.print(">> ");
        return s.hasNextLine();
    }

    private static void handleUserInput(String userInput) {
        String processed = userInput.trim();
        String first = processed.split(" ")[0];
        switch (first) {
            case "list":
                taskPrinter.list(tasks);
                break;
            case "done":
                int index = Integer.parseInt(processed.split(" ")[1]) - 1;
                tasks.set(index, tasks.get(index).markDone());
                taskPrinter.display("Nice! I've marked this task as done: \n" + tasks.get(index));
                break;
            case "bye":
                taskPrinter.display("Bye. Hope to see you again soon!");
                break;
            default:
                String text = userInput.trim();
                if (text != "") {
                    Task newTask = new Task(text);
                    tasks.add(newTask);
                    taskPrinter.display("Got it. I've added this task: \n" + newTask);
                }
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
