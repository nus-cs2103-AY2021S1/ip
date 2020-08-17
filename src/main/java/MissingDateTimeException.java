public class MissingDateTimeException extends Exception {

    public String taskType;

    public MissingDateTimeException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return String.format("☹ OOPS!!! The %s does not have a date/time attached.", taskType);
    }

}
