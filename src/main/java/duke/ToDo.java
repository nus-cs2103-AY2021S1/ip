package duke;

/**
 * The todo class to store todo information
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   27/8/2020
 */
public class ToDo extends Task {

    /**
     * ToDo constructor to initialize a ToDo object with the name
     * @param name name of ToDo
     */
    ToDo(String name) {
        super(name);
    }

    /**
     * toString method which converts the object to a String
     * @return String
     */
    @Override
    public String toString() {
        if (super.getDone()) {
            return "[T]" + "[\u2714] " + super.getName();
        } else {
            return "[T]" + "[\u2718] " + super.getName();
        }
    }
}
