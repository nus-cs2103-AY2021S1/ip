import java.io.IOException;

import java.util.Scanner;

public class Duke {
    private static Storage initialiseStorage() throws ReadFailedException {
        String dataFilePath = "data/tasks.txt";
        Storage storage;
        try {
            storage = new Storage(dataFilePath);
        } catch (IOException ex) {
            throw new ReadFailedException("data");
        }
        return storage;
    }
    
    private static void handleInputs() {
        Scanner scanner = new Scanner(System.in);
        Storage storage = null;
        Tasks list = new Tasks();
        try {
            storage = initialiseStorage();
            list = storage.getTasks();
        } catch (ReadFailedException ex) {
            PrintDuke.printDukeException(ex);
        }
        
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0];
            try {
                switch (command) {
                    case "bye":
                        PrintDuke.printExitMessage();
                        System.exit(0);
                    case "list":
                        PrintDuke.printList(list.tasks);
                        break;
                    case "done":
                        list.markTaskAsDone(storage, input);
                        break;
                    case "delete":
                        list.deleteTask(storage, input);
                        break;
                    case "todo":
                        list.addTask(storage, TaskType.TODO, input);
                        break;
                    case "event":
                        list.addTask(storage, TaskType.EVENT, input);
                        break;
                    case "deadline":
                        list.addTask(storage, TaskType.DEADLINE, input);
                        break; 
                    case "find":
                        list.find(input);
                        break;
                    default:
                        throw new UnknownInputException();
                }
            } catch (DukeException ex) {
                PrintDuke.printDukeException(ex);
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        PrintDuke.printLogo();
        handleInputs();
    }
}
