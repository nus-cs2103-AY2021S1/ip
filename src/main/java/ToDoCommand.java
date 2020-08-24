/**
 * Adds the todos entry into Duke's list
 */
public class ToDoCommand extends Command {
    public ToDoCommand(String input) {
        super(input);
    }

    /**
     * Executes the command to add the todos entry
     * @param tasks list of tasks given
     * @param ui handles the output to print
     * @param storage writes the save file
     * @throws InvalidInputException if input of todos is wrong
     * @throws InvalidSaveFileException if writing the save file goes wrong
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException, InvalidSaveFileException {
        if(super.input.length() <= 5) {
            throw new InvalidInputException("\t☹ OOPS!!! The description of a todo cannot be empty.");
        }
        ToDos task = new ToDos(super.input.substring(5));
        tasks.getTasks().add(task);
        ui.printOutput("\tGot it. I've added this task:\n" + "\t"+task.toString() +
                "\n\tNow you have " + tasks.getTasks().size() + " tasks in the list.");
        storage.saveFile(tasks.getTasks());
    }

    /**
     * Lets the main logic know that it can not quit yet
     * @return false to prevent the loop for exiting
     */
    public boolean isExit() {
        return false;
    }
}
