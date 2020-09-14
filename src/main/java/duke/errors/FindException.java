package duke.errors;

/**
 * The class FindException deals with what happens when an error occurs for task with find keyword
 */
public class FindException extends DukeException {
    private boolean noMatches; //true if there are no matches from user input to the Task names
    private boolean isDescriptionAbsent; //true if description is not given by input, false otherwise
    private String description; //the description given by user(words to search for in tasks)

    /**
     * constructor assigns values of description and string
     *
     * @param noMatches value is assigned to noMatches
     * @param isDescriptionAbsent value is assigned to this.description
     * @param description value is assigned to this.string
     */
    public FindException(boolean noMatches, boolean isDescriptionAbsent, String description) {
        this.noMatches = noMatches;
        this.isDescriptionAbsent = isDescriptionAbsent;
        this.description = description;
    }

    /**
     * overrides the toString() method
     *
     * @return if description is present error is due to no matches being present and an error message informing
     * them would be printed. If it is not present than error is due to keywords being absent therefore an error
     * message regarding that would be released.
     */
    public String toString() {
        if (noMatches) {
            return noMatches(); //when there are no matches
        } else if (isDescriptionAbsent) {
            return descriptionAbsent(); //when description is absent
        } else {
            return "default";
        }
    }

    /**
     * Returns when there are no matches for the keywords given
     *
     * @return String that there are no matches to the keywords given
     */
    public String noMatches() {
        return "  there are no matches to your keyword: " + description;
    }

    /**
     * Returns when there are no keywords given by user
     *
     * @return String that the keywords are absent.
     */
    private String descriptionAbsent() {
        return "  description of find cannot be empty!";
    }
}
