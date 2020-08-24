/**
 * Represents the command from the user when they want to
 * delete a command
 */
public class DeleteCommand extends Command{
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Executes the delete on the task that the user specified
     * @param tasks list of tasks given
     * @param ui handles the output to print
     * @param storage writes the save file
     * @throws InvalidInputException if the input for the delete is incorrect
     * @throws InvalidSaveFileException if there is an issue writing the save file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException, InvalidSaveFileException {
        if(super.input.length() <= 7) {
            throw new InvalidInputException("\t☹ OOPS!!! The description of a delete operation cannot be empty / invalid index.");
        }
        int index = Integer.parseInt(super.input.substring(7));
        if( index >= tasks.getTasks().size() || index<0) {
            throw new InvalidInputException("\t☹ OOPS!!! The description of a delete operation cannot be empty / invalid index.");
        }
        Task task = tasks.getTasks().get(index-1);
        tasks.getTasks().remove(index-1);
        ui.printOutput("\tNoted. I've removed this task:\n"+"\t"+task.toString()+
                "\n\tNow you have " + tasks.getTasks().size() + " tasks in the list.");
        storage.saveFile(tasks.getTasks());
    }

    /**
     * Lets main logic know that it cannot exit loop
     * @return false to prevent exiting
     */
    public boolean isExit() {
        return false;
    }
}
