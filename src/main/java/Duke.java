import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static void handleInputs() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            switch(input) {
                case "bye":
                    PrintDuke.printExitMessage();
                    System.exit(0);
                case "list":
                    PrintDuke.printList(list);
                    break;
                default:
                    list.add(input);
                    PrintDuke.printAddTask(input);
                    break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        PrintDuke.printLogo();
        handleInputs();
    }
}