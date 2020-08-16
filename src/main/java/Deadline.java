public class Deadline extends ToDo {
    private String time;
    public Deadline(String desc, String time) {
        super(desc);
        this.time = time;
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), time);
    }
}
