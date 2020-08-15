package task;

public class Deadline extends Task {
    private String datetime;

    private static String DEADLINE = "[D]";
    public static String DEADLINE_BREAK = "/by";

    Deadline(String task, boolean completed, String datetime) {
        super(task, completed);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        String byDatetime = String.format("(by: %s)", this.datetime);
        return DEADLINE + toStringSuffix() + " " + byDatetime;
    }
}