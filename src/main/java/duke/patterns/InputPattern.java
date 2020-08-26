package duke.patterns;

public class InputPattern {

    public static String deleteTaskPattern = "^delete (?<taskNumber>[0-9]+)$";
    public static String completeTaskPattern = "^done (?<taskNumber>[0-9]+)$";
    public static String addEventPattern = "^event (?<content>.+) /at (?<datetime>.+)$";
    public static String addDeadlinePattern = "^deadline (?<content>.+) /by (?<datetimeDue>.+)$";
    public static String addTodoPattern = "^todo (?<content>.+)$";
    public static String listPattern = "^list$";
    public static String byePattern = "^bye$";
    public static final String FIND_ALL_CONTAINING = "^find (?<content>.+)$";
}
