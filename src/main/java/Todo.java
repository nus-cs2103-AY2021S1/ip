public class Todo extends Task{

    public static String icon = "T";

    Todo(String taskString) {
        super(taskString);
    }

    Todo(String taskString, boolean status) {
        super(taskString);
        this.status = status;
    }

    @Override
    public String toString() {
        String statusIcon = (status)?"✓":"✗";
        return "[" + icon + "]" + "[" + statusIcon + "] " + this.taskString;
    }

    @Override
    public String toDataString() {
        return "todo/" + taskString + "/" + status;
    }
}
