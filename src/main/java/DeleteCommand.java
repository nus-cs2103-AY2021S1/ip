public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) throws TaskNotFoundException, InvalidTaskNumberException, InvalidFileException {
        ui.printDelTask(tasks, index);
        deleteTask(index, tasks);
        storage.writeToFile("data.txt", tasks.writeString());
    }

    /**
     * Deletes an input task from the list
     * @param idx Number of the task indicated to be deleted
     * @throws TaskNotFoundException If input task number is not found in the list
     * @throws InvalidTaskNumberException If user enters a non-integer input
     */
    public void deleteTask(int idx, TaskList tasks) throws TaskNotFoundException, InvalidTaskNumberException {
        try {
            tasks.getTasks().remove(idx - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException("Sorry, I couldn't find that task.");
        } catch (NumberFormatException ex) {
            throw new InvalidTaskNumberException("Please enter a valid number!");
        }
    }
}
