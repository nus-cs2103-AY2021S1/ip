public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public void print(int numOfIndents) {
        String indents = "  ";
        for (int i = 1; i < numOfIndents; i++) {
            indents += indents;
        }
        System.out.println(indents + this.toString());
    }
}