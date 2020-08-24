package Duke.Errors;
/**
 * This DeleteException is used to print out exceptions when there is an incomplete input where the ID is absent or the ID
 * of that Task hasnt been defined yet or it has previously been deleted.
 */
public class DeleteException extends DukeException {
    /**
     * IDabsent tests shows whether the ID is present in the input of the user or not.
     * If ID is not present it is true, else it is false
     * deleted checks whether the task was previously deleted, if deleted it is true else false.
     */
    private boolean IDabsent;
    private boolean deleted;
    /**
     * constructor that assigns tne 2 variables its respective values
     * @param IDabsent input, depneding on whether the ID is present or not in the input.txt file. If present it is false
     *   else it is true.
     */
    public DeleteException(boolean IDabsent, boolean deleted){
        this.IDabsent = IDabsent;
        this.deleted = deleted;
    }

    /**
     * doesn't take in any arguments, overrides the in-built toString() method.
     * @return returns a string depending on the scenario. If the IDabsent is true, then description that the description of
     * delete cannot be empty. If the ID is previously deleted, it prints that it was deleted.
     * Else, it means that the ID, of the Task given is not defined yet.
     */
    @Override
    public String toString() {
        if(deleted){
            return "  '\u2639' OOPS!!! This task is previously deleted.";
        }
        if(IDabsent){
            return "  '\u2639' OOPS!!! The description of delete cannot be empty.";
        }
        return "  '\u2639' OOPS!!! The ID is not yet defined.";
    }
}