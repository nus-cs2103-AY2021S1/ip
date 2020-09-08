import java.io.IOException;

public class ByeCommand extends IOHandler {

    @Override
    public String handleIO(String input, TaskManager taskManager, FileHandler fileHandler) throws IOException {
        fileHandler.writeToFile(taskManager);
        return "bye bye!";
    }
}
