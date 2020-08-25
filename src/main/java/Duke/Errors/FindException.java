package Duke.Errors;

/**
 * The class FindException deals with what happens when an error occurs for task with find keyword
 */
public class FindException extends DukeException {
    private boolean description;
    private String string;

    /**
     * constructor assigns values of description and string
     * @param description value is assigned to this.description
     * @param string value is assigned to this.string
     */
    public FindException(boolean description, String string){
        this.description = description;
        this.string = string;
    }

    /**
     * overrides the toString() method
     * @return if description is present error is due to no matches being present and an error message informing
     * them would be printed. If it is not present than error is due to keywords being absent therefore an error
     * message regarding that would be released.
     */
    public String toString(){
        if(description){
            return "there are no matches to your keyword: " + string;
        }
        return "description of find cannot be empty!";
    }
}
