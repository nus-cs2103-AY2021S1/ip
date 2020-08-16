import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static void handleInputs() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0];
            switch(command) {
                case "bye":
                    PrintDuke.printExitMessage();
                    System.exit(0);
                case "list":
                    PrintDuke.printList(list);
                    break;
                case "done":
                    int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
                    list.get(taskIndex).markAsDone();
                    PrintDuke.printMarkTaskAsDone(list.get(taskIndex));
                    break;
                default:
                    Task task = new Task(input);
                    list.add(task);
                    PrintDuke.printAddTask(task);
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