public class Deadline extends Task {
    
    private String timing;

    public Deadline (String desc) {
        super(desc.split("deadline ")[1].split(" /by ")[0], "D");
        this.timing = desc.split("deadline ")[1].split(" /by ")[1];
    }

    public Deadline(String desc, String timing) {
        super(desc, "D");
        this.timing = timing;
    }

    @Override
    public String toString () {
        return super.toString() + " (by: " + timing + ")";
    }
    
    @Override
    public String getTiming() {
        return this.timing;
    }
}