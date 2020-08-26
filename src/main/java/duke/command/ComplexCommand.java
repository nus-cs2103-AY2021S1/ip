package duke.command;

/**
 * Abstract class for representing user commands with parameters.
 */
public abstract class ComplexCommand extends Command{

    /**
     * Parameters for the command.
     */
    public String params;

    /**
     * Creates a complex <code>Command</code> with parameters initialised.
     * @param params Parameters for the user command of interest.
     */
    public ComplexCommand(String params) {
        super();
        this.params = params;
    }

    /**
     * Returns a String representation of the <code>ComplexCommand</code>.
     * This includes both the name and the parameters involved.
     * The parameters are printed in a simple string with separators (e.g. "/by" for <code>Deadline</code>) included.
     *
     * @return Formatted String representation.
     */
    public String toString() {
        return super.toString() + " | " + this.params;
    }

}
