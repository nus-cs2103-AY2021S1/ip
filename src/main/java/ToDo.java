import java.util.ArrayList;
import java.util.Arrays;

/**
 * The ToDo class is a type of listing that contains a String as the detail of the listing and a
 * boolean called isDone.
 */

public class ToDo extends Listing {

    /**
     * Creates a ToDo object by passing the string s as the Todo message.
     *
     * @param s the detail of the ToDo
     * @return a ToDo object
     */
    public ToDo(String s) {
        super(s);
    }

    /**
     * Creates a ToDo object by passing a string doneness which gets converted to a boolean and
     * assigned to this.isDone. String s gets passed as the message detail
     *
     * @param doneness a string of either 1 or 0 that gets converted to boolean
     * @param s        the message detail
     * @return a ToDo object
     */
    public ToDo(String doneness, String s, String tagList) {
        super(s);
        setDoneness(doneness);
        this.tags = new ArrayList<>(Arrays.asList(tagList));
    }

    public ToDo(String doneness, String s) {
        super(s);
        setDoneness(doneness);
    }

    /**
     * Summarises the details of the ToDo object into a size 3 string array containing a code "T" that
     * represents ToDO, isDone boolean in the form of a string and the detail message
     *
     * @return a size 3 String array containing the code of the ToDo object, the isDone in string the
     * detail of the object
     */
    public String[] toArray() {
        String[] details = new String[4];
        details[0] = "T";
        details[1] = this.isDone ? "1" : "0";
        details[2] = this.title;
        details[3] = this.tags.toString().substring(1, this.tags.toString().length() - 1);
        return details;
    }

    /**
     * @return Returns the ToDo object in string.
     */
    @Override
    public String toString() {
        return this.tags.isEmpty() ? "[T]" + super.doneness() + this.title
                : "[T]" + super.doneness() + this.title + "\n        tags:" + this.tags.toString();
    }
}
