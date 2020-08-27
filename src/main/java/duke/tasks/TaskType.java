package duke.tasks;


/**
 * TaskType enum.
 */
public enum TaskType {
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    INVALID("invalid");

    private final String taskKeywordStr;


    TaskType(String taskKeywordStr) {
        this.taskKeywordStr = taskKeywordStr;
    }


    @Override
    public String toString() {
        return this.taskKeywordStr;
    }
}
