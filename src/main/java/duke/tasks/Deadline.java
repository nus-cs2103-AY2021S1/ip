package duke.tasks;

import duke.DukeException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <code>Deadline</code> inherits from <code>Task</code>
 * and is used to represent a deadline.
 */
public class Deadline extends Task implements Comparable<Deadline> {
    private Date dueDate;

    /**
     * Constructor for creating a new <code>Deadline</code> object.
     * Requires a <code>name</code> and a <code>dueDate</code>.
     * @param name the name of this object
     * @param dueDate the due date of this object in the format of "dd MM yyyy"
     * @throws DukeException if the date format given is invalid
     */
    public Deadline(String name, String dueDate) throws DukeException {
        super(name);
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
        try {        
            this.dueDate = formatter.parse(dueDate);
        } catch (ParseException e) {
            throw new DukeException("Date format is invalid!");
        }
    }

    /**
     * Returns a comma seperated <code>String</code> containing the relevant information
     * about this deadline. This information will then be saved in a text file.
     * @return the comma seperated text of this deadline
     */
    @Override
    public String saveText() {
        String completeStatus = super.isCompleted() ? "1" : "0";
        DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        return "D," + completeStatus + "," + super.getName() + "," + dateFormat.format(dueDate);
    }

    /**
     * Returns the string representation of this deadline.
     * @return a string representation of this deadline
     */
    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("[D]%s (due: %s)", super.toString(), dateFormat.format(dueDate));
    }

    /**
     * Compares with another deadline. Both are equal if they have the same name and same date.
     * Else, they will be compared according to their date.
     * @param other the other deadline to be compared with.
     * @return a value 0 if both deadlines are equal;
     * a value greater than 0 if this Deadline is later than the Deadline argument;
     * a value less than 0 if this deadline is before the Deadline argument.
     */
    @Override
    public int compareTo(Deadline other) {
        boolean sameName = getName().equals(other.getName());
        boolean sameDate = dueDate.compareTo(other.dueDate) == 0;
        if (sameName && sameDate) {
            return 0;
        } else{
            return dueDate.compareTo(other.dueDate);
        }
    }
}
