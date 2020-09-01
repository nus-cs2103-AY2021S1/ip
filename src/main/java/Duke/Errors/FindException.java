package Duke.Errors;

/**
 * The class FindException deals with what happens when an error occurs for task with find keyword
 */
public class FindException extends DukeException {
    private boolean descriptionAbsent;
    private String description;

    /**
     * constructor assigns values of description and string
     *
     * @param descriptionAbsent value is assigned to this.description
     * @param description value is assigned to this.string
     */
    public FindException(boolean descriptionAbsent, String description){
        this.descriptionAbsent = descriptionAbsent;
        this.description = description;
    }

    /**
     * overrides the toString() method
     *
     * @return if description is present error is due to no matches being present and an error message informing
     * them would be printed. If it is not present than error is due to keywords being absent therefore an error
     * message regarding that would be released.
     */
    public String toString(){
        if(descriptionAbsent){
            return "there are no matches to your keyword: " + description;
        }
        return "description of find cannot be empty!";
    }
}
