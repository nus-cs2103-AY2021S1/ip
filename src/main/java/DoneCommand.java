public class DoneCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    public DoneCommand(String input) {
        super(IS_EXIT);
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitStr = input.split(" ");
        if (splitStr.length == 1) {
            throw new DukeException("☹ OOPS!!! I don't know which task to mark as done.");
        }
        int taskIndex = Integer.parseInt(splitStr[1]) - 1;
        Task task = tasks.getTask(taskIndex);
        task.markAsDone();
        if (task.getTaskType().equals("T")) {
            storage.editCurrentDataInFile(taskIndex + 1, task.getTaskType(), "1", task.getDescription(),
                    "", tasks.getSize());
        } else if (task.getTaskType().equals("D")) {
            Deadline deadline = (Deadline) task;
            storage.editCurrentDataInFile(taskIndex + 1, task.getTaskType(), "1", task.getDescription(),
                    deadline.getBy(), tasks.getSize());
        } else {
            Event event = (Event) task;
            storage.editCurrentDataInFile(taskIndex + 1, task.getTaskType(), "1", task.getDescription(),
                    event.getTime(), tasks.getSize());
        }
        System.out.println("    Nice! I've marked this task as done:\n      " + task);
    }
}
