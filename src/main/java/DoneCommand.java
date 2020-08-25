public class DoneCommand extends Command {

    public DoneCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException {
        int index;

        try {
            index = Integer.parseInt(command.substring(5));
            Task task = taskList.storage.get(index - 1);
            task.markAsDone();
            ui.print("     Nice! I have marked this task as done :-)");
        } catch (Exception ex) {
            if (ex instanceof StringIndexOutOfBoundsException) {
                throw new SparklesException("     OOPS!! Task in the list to be marked as done is not specified!");
            } else {
                if(taskList.storage.isEmpty()) {
                    throw new SparklesException( "     OOPS!! Task list is empty!");

                } else {
                    throw new SparklesException("     OOPS!! Task does not exist!");
                }
            }
        } finally {
            storage.updateFile(taskList.storage);
        }
    }
}
