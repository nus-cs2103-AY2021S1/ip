package duke.errors;

import duke.helpers.ShortCuts;

/**
 * used when there is an error in user input while adding short cut
 */
public class ShortCutException extends DukeException {
    private boolean isDescriptionAbsent; //true if the description is absent in user input, false otherwise
    private boolean isShortCutAbsent; //true if short cut is not provided
    private boolean isShortCutAlreadyPresent; //true if short cut is already defined, false otherwise
    private boolean isUselessShortCut; //if short cut is defined for a command not read by Duke
    private String shortCut;

    /**
     * constructor which assigns the above 4 member variables into its respective values
     *
     * @param isDescriptionAbsent true is description is not given by user, false otherwise
     * @param isShortCutAbsent true if short form is not given by user, false otherwise
     * @param isShortCutAlreadyPresent true if short cut is already present
     * @param isUselessShortCut true if short cut's original value means nothing to Duke
     * @param shortCut shortcut provided by user
     */
    public ShortCutException(boolean isDescriptionAbsent, boolean isShortCutAbsent,
                             boolean isShortCutAlreadyPresent, boolean isUselessShortCut, String shortCut) {
        this.isDescriptionAbsent = isDescriptionAbsent;
        this.isShortCutAbsent = isShortCutAbsent;
        this.isShortCutAlreadyPresent = isShortCutAlreadyPresent;
        this.isUselessShortCut = isUselessShortCut;
        this.shortCut = shortCut;
    }

    /**
     * overrides built-in toString method
     *
     * @return String for the reason this Exception is thrown
     */
    public String toString() {
        if (isDescriptionAbsent) {
            return descriptionAbsent(); //when description is absent
        } else if (isShortCutAbsent) {
            return shortCutAbsent(); //when short cut is absent
        } else if (isShortCutAlreadyPresent) {
            return shortCutAlreadyPresent(); //when short cut is already present
        } else if (isUselessShortCut) {
            return uselessShortCut(); //when short cut has no meaning
        } else {
            return "default";
        }
    }

    /**
     * Describes that description of short cannot be empty
     *
     * @return String informing user that description of short cannot be empty"
     */
    private String descriptionAbsent() {
        return "  The description of short cannot be empty";
    }

    /**
     * Describes that short cut cannot be empty
     *
     * @return String informing user that short cut is not provided.
     */
    private String shortCutAbsent() {
        return "short cut is not provided";
    }

    /**
     * Describes that short cut is already present
     *
     * @return String informing user that short cut is already present
     */
    private String shortCutAlreadyPresent() {
        assert ShortCuts.getShortCuts().containsKey(this.shortCut);
        return this.shortCut + " is already present as a short form for" + ShortCuts.getShortCuts().get(this.shortCut);
    }

    /**
     * Describes that short cut has no meaning
     *
     * @return String informing user that short cut has no meaning
     */
    private String uselessShortCut() {
        return this.shortCut + " has no meaning";
    }
}
