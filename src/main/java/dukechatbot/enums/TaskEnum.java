package dukechatbot.enums;

/**
 * Represents enum of the possible task.
 */
public enum TaskEnum {
    TODO("T"),
    EVENT("E"),
    DEADLINE("D");

    final String taskLetter;

    TaskEnum(String taskLetter) {
        this.taskLetter = taskLetter;
    }

    /**
     * Returns taskLetter String.
     * @return taskLetter String
     */
    public String getTaskLetter() {
        return taskLetter;
    }
}
