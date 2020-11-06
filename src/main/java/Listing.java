import java.util.ArrayList;

/**
 * The Listing class is the parent class of Event.java, Deadline.java and ToDo.java. It contains
 * a String that represent the details and a Boolean called isDone that is by default set to false.
 * It contains helper functions such as doneness() and checkDoneness() that are used by Printer.java
 * and TaskList.java respectively.
 */

public class Listing {

    String title;
    Boolean isDone = false;
    ArrayList<String> tags = new ArrayList<>();

    public Listing(String s) {
        this.title = s;
    }

    public void complete() {
        isDone = true;
    }

    /**
     * @return a String checkbox that represents if the listing is done or not
     */
    public String doneness() {
        if (isDone) {
            return "[Y]";
        } else {
            return "[N]";
        }
    }

    /**
     * Sets the isDone boolean based on the input String s.
     *
     * @param s which can be either 0 or 1
     */
    public void setDoneness(String s) {
        if (s.equals("1")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    public String[] toArray() {
        return new String[2];
    }

    public String toString() {
        return this.title;
    }
}
