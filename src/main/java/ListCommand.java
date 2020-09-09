public class ListCommand extends Command {

    @Override
    public String handle(String input, TaskManager taskManager, Storage fileHandler) {
        return taskManager.toString();
    }
}
