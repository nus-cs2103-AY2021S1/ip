public class Todo extends Task{

    public static String icon = "T";

    Todo(String taskString) {
        super(taskString);
    }

    @Override
    public String toString() {
        String statusIcon = (status)?"✓":"✗";
        return "[" + icon + "]" + "[" + statusIcon + "] " + this.taskString;
    }
}
