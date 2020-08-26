public class ToDo extends Listing{

    public ToDo(String s) {
        super(s);
    }

    public ToDo(String doneness, String s) {
        super(s);
        checkDoneness(doneness);
    }

    public String[] toArray() {
        String[] details = new String[3];
        details[0] = "T";
        if (this.done) {
            details[1] = "1";
        } else {
            details[1] = "0";
        }
        details[2] = this.title;
        return details;
    }

    @Override
    public String toString() {
        return "[T]" + super.doneness() + this.title;
    }
}
