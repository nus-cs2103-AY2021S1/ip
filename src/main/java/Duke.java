import java.util.Scanner;

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
        Scanner scn = new Scanner(System.in);
        while (!exitProgram) {
            System.out.println();
            String userInput = scn.nextLine();
            Duke.printHorizontalLine();
            if (userInput.equals("bye")) {
                System.out.println("Goodbye! Shutting down now...");
                exitProgram = true;
            } else {
                System.out.println(userInput);
            }
            Duke.printHorizontalLine();
        }
    }

    private static void printHorizontalLine() {
        String horizontalLine = "---------------------------------------------";
        System.out.println(horizontalLine);
    }
}
