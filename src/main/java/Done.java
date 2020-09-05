/**
 * Marks task as Done in the task list.
 */
public class Done extends Command {

    Done(String[] splitQuery) throws DukeException {
        this.name = "done";
        this.usage = "done [TaskListNumber]";
        this.description = "Marks a task as done in the task list";
        if (splitQuery.length != 2) {
            throw new WrongUsageException(this.name, this.usage);
        }
    }

    /**
     * Returns String response after task has been marked as done.
     *
     * @param listIndex Index of task to be marked as done.
     * @return String response after task has been marked as done.
     * @throws CustomException If listIndex is not parse-able or listIndex is out of range.
     */
    public String markedAsDone(String listIndex) throws CustomException {
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
            Task curr = DataStorageInterface.markDone(idx);
            return String.format("“h-h-how did you take *BURRRRPPPP* so long to-to finish " +
                    "a task morty? HOW? Anyways you finished this\n%s\n" +
                    "Now you have just another %d meaningless things to do",
                    curr,DataStorageInterface.getTasksNotDone());
        }
    }

}
