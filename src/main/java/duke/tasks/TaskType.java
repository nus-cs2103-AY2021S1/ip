package duke.tasks;

public enum TaskType {
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    INVALID("invalid");

    private final String taskKeywordStr;


    private TaskType(String taskKeywordStr) {
        this.taskKeywordStr = taskKeywordStr;
    }


    @Override
    public String toString() {
        return this.taskKeywordStr;
    }
}
