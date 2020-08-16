import java.util.Scanner;

public class Duke {

    private static boolean getUserInput(Scanner s) {
        System.out.print(">> ");
        return s.hasNext();
    }

    private static void handleUserInput(String userInput) {
        switch (userInput) {
            case "bye":
                Printer.display("Bye. Hope to see you again soon!");
                break;
            default:
                Printer.display(userInput);
        }
    }

    public static void main(String[] args) {
        System.out.println(
                "   ##############################\n" +
                "   # Hi! I'm Hanry the ChatBot. #\n" +
                "   # What can I do for you?     #\n" +
                "   ##############################"
        );
        Scanner sc = new Scanner(System.in);
        while(getUserInput(sc)) {
            String userInput = sc.next();
            handleUserInput(userInput);
        }
    }
}
