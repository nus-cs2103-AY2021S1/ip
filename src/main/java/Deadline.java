public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

    @Override
    public void print(int numOfIndents) {
        String indents = "  ";
        for (int i = 1; i < numOfIndents; i++) {
            indents += indents;
        }
        System.out.println(indents + this.toString());
    }

    @Override
    public String stringToSave() {
        char status = this.isDone ? '1' : '0';
        return "D " + "| " + status + " | " + this.description + "|" + this.by;
    }

}