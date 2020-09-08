public class ListCommand extends IOHandler {

    @Override
    public String handleIO(String input, TaskManager taskManager, FileHandler fileHandler) {
        return taskManager.toString();
    }
}
