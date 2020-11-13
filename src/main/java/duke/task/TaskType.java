package duke.task;

public enum TaskType {
    TODO, EVENT, DEADLINE;

    /**
     * return string of the enum
     */
    public String toString() {
        switch (this) {
        case TODO:
            return "todo";
        case EVENT :
            return "event";
        case DEADLINE:
            return "deadline";
        default:
            return null;
        }
    }

    /**
     * return true if a string taskType is task
     */
    public static boolean isTask(String taskType) {
        return taskType.equals(TODO.toString())
                || taskType.equals(EVENT.toString()) || taskType.equals(DEADLINE.toString());
    }
}



