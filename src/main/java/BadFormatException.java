public class BadFormatException extends DukeException {
    private static String line = "_______________________________________";
    private String taskType;

    public BadFormatException(String errorMessage, String taskType) {
        super(errorMessage);
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        String separator = taskType.equals("deadline") ? "/by" : "/at";
        String ret = line + "\n Oh no, the format of a " + this.taskType + " is: " + this.taskType
                + " <YOUR TASK HERE> " + separator + " <DATE>\n" + line;
        return ret;
    }
}
