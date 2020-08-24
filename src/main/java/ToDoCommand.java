public class ToDoCommand extends Command {
    private final String description;
    private ToDoCommand(String description, IDuke duke) {
        super(-1, duke);
        this.description = description;
    }

    public static ToDoCommand getToDoCommand(String description) {
        return new ToDoCommand(description, null);
    }

    @Override
    public IDuke execute() {
        if (duke == null) {
            throw new RuntimeException(Message.ERR_DUKE_NOT_INIT.toString());
        }
        return handleToDo(description);
    }

    private IDuke handleToDo(String description) throws DukeIllegalArgumentException {
        if (description.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The description of todo cannot be empty!");
        }
        ITask task = ToDo.getToDo(description);
        IDuke newDuke = storeTask(task);
        System.out.println(TextFormatter.getFormattedText(
                "Got it. I've added this task:\n\t" + task.toString()
                        + "\nNow you have "
                        +  newDuke.getNumTask() + " task(s) in the list."));
        return newDuke;
    }

    public IDuke storeTask(ITask task) {
        Storage storage = duke.getStorage();
        TaskList newList = new TaskList(duke.getTasks().getList());
        newList.add(task);
        storage.save(newList.getList());
        return new Duke(newList, storage);
    }

    @Override
    public Command setDuke(IDuke duke) {
        return new ToDoCommand(description, duke);
    }
}
