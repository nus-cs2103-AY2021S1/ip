import java.util.Scanner;

public class Duke {
    private static void handleInputs() {
        Scanner scanner = new Scanner(System.in);
        Tasks list = new Tasks();
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0];
            try {
                switch(command) {
                    case "bye":
                        PrintDuke.printExitMessage();
                        System.exit(0);
                    case "list":
                        PrintDuke.printList(list.tasks);
                        break;
                    case "done":
                        list.markTaskAsDone(input);
                        break;
                    case "delete":
                        list.deleteTask(input);
                        break;
                    case "todo":
                        list.addTask(TaskType.TODO, input);
                        break;
                    case "event":
                        list.addTask(TaskType.EVENT, input);
                        break;
                    case "deadline":
                        list.addTask(TaskType.DEADLINE, input);
                        break; 
                    case "find":
                        list.find(input);
                        break;
                    default:
                        throw new UnknownInputException();
                }
            } catch (DukeException ex) {
                PrintDuke.printException(ex);
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        PrintDuke.printLogo();
        handleInputs();
    }
}
