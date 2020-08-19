public class Deadline extends Task {
    private static String format(String s) {
        String[] split = s.split("/");
        return split[0] +  "(" + split[1].split(" ")[0] + ":" + split[1].substring(split[1].indexOf(' ')) + ")";
    }

    public Deadline(String contents) {
        super(Deadline.format(contents));
    }

    public String toString() {
        return "[D]" + super.toString();
    }
}
