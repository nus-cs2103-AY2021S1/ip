package rock.utility;

/**
 * String that can be passed between classes and modified
 */
public class RockResponse {
    private String res;

    /**
     * Constructor.
     */
    public RockResponse() {
        res = "";
    }

    /**
     * Add x to the current string and start a new line.
     * @param x string that need to be appended.
     */
    public void addNewLines(String x) {
        res += x + "\n";
    }

    @Override
    public String toString() {
        return res;
    }
}
