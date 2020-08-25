package command;

/**
 * Represents a single Command with or without parameters
 *
 * @author  Ryan Lim
 */
public class Command {
    /** array of strings where each element represents a parameter tagged to the command */
    public String[] parameters;

    /**
     * Instantiate command without parameters
     */
    public Command() {
        this.parameters = new String[0];
    }

    /**
     * Instantiate command with parameters
     *
     * @param parameters Array of parameters represented as strrings.
     */
    public Command(String[] parameters) {
        this.parameters = parameters;
    }

    /**
     * Returns True if there is no parameter tagged to the Command, False otherwise.
     *
     * @return boolean value indicating if command has parameter or not.
     */
    public boolean isEmpty() {
        return this.parameters.length == 0;
    }

    /**
     * Returns an Array of string where each element is a single parameter of the command tagged to it.
     *
     * @return an array of parameters represented as strings.
     */
    public String[] getParameters() { return this.parameters ; }

}
