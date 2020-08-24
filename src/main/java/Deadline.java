public class Deadline extends Task {
    
    private String timing;

    public Deadline (String desc) {
        super(desc.split("deadline ")[1].split(" /by ")[0], "D");
        this.timing = desc.split("deadline ")[1].split(" /by ")[1];
    }

    @Override
    public String toString () {
        return super.toString() + " (by: " + timing + ")";
    }
    
    public String getTiming() {
        return this.timing;
    }
}