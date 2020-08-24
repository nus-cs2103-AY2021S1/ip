public class CompleteTaskCommand extends Command {

    public CompleteTaskCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(this.parsedCommand[1]);
            if (index > tasks.getListSize()) {
                String err = "Invalid Task! The task does not exist. " +
                        "Input 'list' to view the correct task ID of your desired task.";
                throw new InvalidTaskException(err);
            } else if (index <= 0) {
                String err = "The task ID you provided is not valid. " +
                        "Input 'list' to view the correct task ID of your desired task.";
                throw new InvalidFunctionException(err);
            } else {
                Task task = tasks.getTask(index - 1);
                if (task.isDone) {
                    String message = "  This task has already been completed:";
                    ui.printReply(message);
                } else {
                    task.markAsDone();
                    String message = "  Nice! I've marked this task as done:";
                    ui.printReply(message);
                }
                String successReply = "\t" + task;
                ui.printReply(successReply);
            }
            storage.saveFile(tasks);
        } catch (ArrayIndexOutOfBoundsException ex) {
            String err = "No Task ID provided! Please input the ID of the task you wish to mark as completed.";
            throw new InvalidFunctionException(err);
        } catch (NumberFormatException ex) {
            String err = "Your input is not a recognised command. You have to provide the ID of " +
                    "the task you wish to mark as done. \n" + "Input '/commands' to view a list of my commands. ";
            throw new InvalidFunctionException(err);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
