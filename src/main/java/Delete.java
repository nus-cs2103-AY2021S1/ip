/**
 * Deletes a task from the task list.
 */
public class Delete extends Command {

    Delete(String[] splitQuery) throws WrongUsageException {
        this.name = "delete";
        this.usage = "delete [TaskListNumber]";
        this.description = "Deletes a task in the task list";
        if (splitQuery.length != 2) {
            throw new WrongUsageException(this.name, this.usage);
        }
    }

    /**
     * Parses the String provided into a number and deletes the task in the task list.
     *
     * @param listIndex Index of task to be deleted.
     * @return String response for delete.
     * @throws CustomException If listIndex is not parse-able or listIndex is out of range.
     */
    public String deleteTask(String listIndex) throws CustomException {
        int idx;
        try {
            idx = Integer.parseInt(listIndex) - 1;
        } catch (Exception e) {
            throw new CustomException("Error: Dammit Morty, give me a *BUURRRP* valid integer!");
        }
        if (idx < 0 || idx > DataStorageInterface.getSize() - 1) {
            throw new CustomException("Error: Morty y-you idiot give me a number *BUURRP*" +
                    "that is in range of the task numbers");
        } else {
            Task curr = DataStorageInterface.remove(idx);
            return String.format("Wow Morty look at you go huh just deleting things like:\n%s\n" +
                    "Now you have just another %d meaningless things to do",
                    curr, DataStorageInterface.getTasksNotDone());
        }
    }
}
