public class AddCommand extends Command {

    Type type;
    String body;

    public AddCommand(Type type, String body) {
        this.type = type;
        this.body = body;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        switch (type) {
            case TODO:
                Todo task = Todo.newTodo(body);
                if (taskList.addTask(task)) {
                    ui.addSuccess(task, taskList.count());
                }
                break;
            case DEADLINE:
                Deadline deadline = Deadline.newDeadline(body);
                if (taskList.addTask(deadline)) {
                    ui.addSuccess(deadline, taskList.count());
                }
                break;
            case EVENT:
                Event event = Event.newEvent(body);
                if (taskList.addTask(event)) {
                    ui.addSuccess(event, taskList.count());
                }
                break;
            default:
                break;
        }

        storage.saveTasks(taskList.getTasks());
    }
}
