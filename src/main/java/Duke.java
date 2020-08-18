import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        PrintFunctions.printGreeting();
        String userInput;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals(StringConstants.EXIT_COMMAND)) {
                break;
            } else {
                PrintFunctions.printMessageBetweenLines(userInput);
            }
        }

        PrintFunctions.printMessageBetweenLines(StringConstants.EXIT_MESSAGE);
    }
}
