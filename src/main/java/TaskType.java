public enum TaskType {
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    UNKNOWN("invalid");

    private String taskKeywordStr;


    private TaskType(String taskKeywordStr) {
        this.taskKeywordStr = taskKeywordStr;
    }


    @Override
    public String toString() {
        return this.taskKeywordStr;
    }
}
