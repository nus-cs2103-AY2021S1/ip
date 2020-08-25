package enums;

public enum TaskEnum {
    TODO("T"),
    EVENT("E"),
    DEADLINE("D");

    String taskLetter;

    TaskEnum(String taskLetter) {
        this.taskLetter = taskLetter;
    }

    public String getTaskLetter() {
        return taskLetter;
    }
}
