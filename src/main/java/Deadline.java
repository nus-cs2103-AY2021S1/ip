public class Deadline extends Task {
    protected String by;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }
    
    @Override
    public String writeMessage() {
        String done = "";
        if (this.done) {
            done = "✓";
        } else {
            done = "✗";
        }
        return String.format("D | %s | %s | %s", done, this.task, this.by);
    }

    @Override
    public String toString() {
        String[] bys = by.split(" ");
        String str = " (";
        for (int i = 0; i < bys.length; i++) {
            if (i == 0) {
                str += bys[i] + ": ";
            } else if (i == bys.length - 1) {
                str += bys[i];
            } else {
                str += bys[i] + " ";
            }
        }
        return "[D][" + (this.done ? "✓" : "✗") + "] " + this.task + str + ")";
    }
}
