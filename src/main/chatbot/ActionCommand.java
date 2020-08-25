public class ActionCommand extends Command {

    Type type;
    String body;

    public ActionCommand(Type type, String body) {
        this.body = body;
        this.type = type;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        int index = -1;
        try {
            index = Integer.parseInt(body) - 1;
        } catch (NumberFormatException e) {
            throw new ChatbotException("Please give me a valid number.");
        }
        switch (type) {
            case DELETE:
                Task deletedTask = taskList.removeTask(index);
                ui.deleteSuccess(deletedTask, taskList.count());
                break;
            case DONE:
                Task taskDone = taskList.markAsDone(index);
                ui.markDoneSuccess(taskDone);
                break;
            default:
                break;
        }

        storage.saveTasks(taskList.getTasks());
    }
}
