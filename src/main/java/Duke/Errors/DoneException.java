/**
 * This DoneException is used to print out exceptions when there is an incomplete input where the ID is absent or the ID
 * of that Task hasnt been defined yet.
 */
package Duke.Errors;
public class DoneException extends DukeException{
    /**
     * IDabsent tests shows whether the ID is present in the input of the user or not.
     * If ID is not present it is true, else it is false
     */
    private boolean IDabsent;
    private boolean deleted;
    /**
     *
     * @param IDabsent input, depneding on whether the ID is present or not in the input.txt file. If present it is false
     *   else it is true.
     */
    public DoneException(boolean IDabsent, boolean deleted){
        this.IDabsent = IDabsent;
        this.deleted = deleted;
    }
    /**
     * doesn't take in any arguments, overrides the in-built toString() method.
     * @return returns a string depending on the scenario. If the IDabsent is true, then description that the description of
     * done cannot be empty. Else, it means that the ID, of the Task given is not defined yet.
     */
    @Override
    public String toString() {
        if(deleted){
            return "  '\u2639' OOPS!!! This task has already been deleted.\n";
        }
        if(IDabsent){
            return "  '\u2639' OOPS!!! The description of a done cannot be empty.\n";
        }
        return "  '\u2639' OOPS!!! The ID is not yet defined.\n";
    }
}