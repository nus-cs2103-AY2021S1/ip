public class InputPattern {

    static String deleteTaskPattern = "^delete (?<taskNumber>[0-9]+)$";
    static String completeTaskPattern = "^done (?<taskNumber>[0-9]+)$";
    static String addEventPattern = "^event (?<content>.+) /at (?<datetime>.+)$";
    static String addDeadlinePattern = "^deadline (?<content>.+) /by (?<datetimeDue>.+)$";
    static String addTodoPattern = "^todo (?<content>.+)$";
    static String listPattern = "^list$";
    static String byePattern = "^bye$";
}
