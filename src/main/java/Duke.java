import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    public static void main(String[] args) {
        String name = "Omega";
        Duke.printHorizontalLine();
        System.out.println("Hi! I am " + name + ", your personal assistant.");
        System.out.println("How may I help you today?");
        Duke.printHorizontalLine();
        Duke.interactWithUser();
    }

    private static void interactWithUser() {
        boolean exitProgram = false;
        List<Task> listOfTasks = new ArrayList<>();
        Scanner scn = new Scanner(System.in);
        while (!exitProgram) {
            System.out.println();
            String userInput = scn.nextLine();
            Duke.printHorizontalLine();
            if (userInput.equals("bye")) {
                System.out.println("Goodbye! Shutting down now...");
                exitProgram = true;
            } else if (userInput.equals("list")) {
                int n = listOfTasks.size();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < n; i++) {
                    System.out.println(String.format("%d.%s", i + 1, listOfTasks.get(i)));
                }
            } else if (userInput.split(" ")[0].equals("done")) {
                int idx = Integer.parseInt(userInput.split(" ")[1]);
                Task task = listOfTasks.get(idx - 1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);
            } else {
                listOfTasks.add(new Task(userInput));
                System.out.println("added: " + userInput);
            }
            Duke.printHorizontalLine();
        }
    }

    private static void printHorizontalLine() {
        String horizontalLine = "---------------------------------------------";
        System.out.println(horizontalLine);
    }
}
