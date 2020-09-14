/**
 * Class that stores the differnt types of command constants.
 */
public enum TaskElement {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    TAG("tag"),
    FIND("find"),
    HELP("help"),
    FINDTAG("findtag");

    public final String label;

    /**
     * Returns the labels of the different constants.
     *
     * @param input The input that has been input by the user.
     */
    private TaskElement(String label) {
        this.label = label;
    }
}
