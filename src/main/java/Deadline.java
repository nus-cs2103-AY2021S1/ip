public class Deadline extends Task {
    private String time;
    public Deadline(String desc, String time) {
        super(desc);
        this.time = time;
    }
    public String getTime() {
        return this.time;
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), time);
    }
    @Override
    public String toFileString() {
        return "D\n"+super.toFileString()+this.time + "\n";
    }
}
