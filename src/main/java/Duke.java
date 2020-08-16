import java.util.Scanner;

public class Duke {
    private static void handleInputs() {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String input = scanner.next();
            if (input.equals("bye")) {
                PrintDuke.printExitMessage();
                break;
            }
            PrintDuke.printWithDashes(" " + input);
        }
        scanner.close();
    }

    public static void main(String[] args) {
        PrintDuke.printLogo();
        handleInputs();
    }
}