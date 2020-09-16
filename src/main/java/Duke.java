import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * The Duke program keeps track of the list of tasks to be done.
 */
public class Duke {

    private TaskManager taskManager;
    private Storage fileHandler;

    public Duke() throws IOException {
        String filePath = "data/duke.txt";
        this.fileHandler = new Storage(filePath);
        this.taskManager = new TaskManager();

        try {
            List<String> files = Storage.readSavedFile(filePath);

            assert files != null;
            for (String value : files) {
                Task task = TextAndTaskConverter.convertTextToTask(value);
                taskManager.getTaskList().add(task);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo +"Hello! I'm Duke\n" + "What can I do for you?");
        Duke duke = new Duke();
        duke.run();
    }

    private void run() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (input != null) {
            Command command = Parser.parseInput(input);
            assert command != null;
            System.out.println(command.handle(input, taskManager, fileHandler));
            input = scanner.nextLine();
        }
        scanner.close();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws IOException {
        String trimmedInput = input.trim();
        // String e = DukeExceptionHandler.handleException(trimmedInput);
/*        if (e != null) {
            return e;
        } else {*/
            Command command = Parser.parseInput(trimmedInput);
            assert command != null;
            return command.handle(trimmedInput, taskManager, fileHandler);
        // }
    }
}
