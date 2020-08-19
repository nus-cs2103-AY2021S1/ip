public class Deadline extends Task {

    public String type;

    public Deadline(String desc, boolean isDone) {
        super(desc, isDone);
        type = "D";
    }

    @Override
    public String getType() {
        return this.type;
    }
}
