import java.io.IOException;

public class ByeCommand extends Command {

    @Override
    public String handle(String input, TaskManager taskManager, Storage fileHandler) throws IOException {
        fileHandler.writeToFile(taskManager);
        return "bye bye!";
    }
}
