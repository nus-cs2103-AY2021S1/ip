package duke.patterns;

/**
 * Represents the different types of input patterns.
 */
public class InputPattern {

    public static final String DELETE_TASK = "^delete (?<taskNumber>[0-9]+)$";
    public static final String COMPLETE_TASK = "^done (?<taskNumber>[0-9]+)$";
    public static final String ADD_EVENT = "^event (?<content>.+) /at (?<datetime>.+)$";
    public static final String ADD_DEADLINE = "^deadline (?<content>.+) /by (?<datetimeDue>.+)$";
    public static final String ADD_TODO = "^todo (?<content>.+)$";
    public static final String LIST = "^list$";
    public static final String BYE = "^bye$";
}
