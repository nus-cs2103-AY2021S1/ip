import java.io.IOException;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Exits the program.
     * @param input user input.
     * @param taskManager task manager that contains a list of tasks.
     * @param fileHandler saves the input into a file.
     * @return String message to end the program.
     * @throws IOException
     */
    public String handle(String input, TaskManager taskManager, Storage fileHandler) throws IOException {
        fileHandler.writeToFile(taskManager);
        return "bye bye!";
    }
}
