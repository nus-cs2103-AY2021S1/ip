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
        List<String> listOfUserInput = new ArrayList<>();
        Scanner scn = new Scanner(System.in);
        while (!exitProgram) {
            System.out.println();
            String userInput = scn.nextLine();
            Duke.printHorizontalLine();
            if (userInput.equals("bye")) {
                System.out.println("Goodbye! Shutting down now...");
                exitProgram = true;
            } else if (userInput.equals("list")) {
                int n = listOfUserInput.size();
                for (int i = 0; i < n; i++) {
                    System.out.println(String.format("%d. %s", i + 1, listOfUserInput.get(i)));
                }
            } else {
                listOfUserInput.add(userInput);
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
