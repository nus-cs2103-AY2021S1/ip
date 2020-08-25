
import java.time.LocalDate;

import java.io.Serializable;


/**
 * Task class that stores what to do, type, deadline and if finished
 */
public class Task implements Serializable {

    private String type;// can be [T] [E] or [D]
    private boolean done = false;
    private String details;
    private String deadLine = null;


    public LocalDate localDeadline;


    /**
     * Tells if tasks is finish
     *
     * @return boolean of whether task is finished
     */
    public boolean finished() {
        return this.done;
    }

    /**
     * Sets done to true. Used when finished task
     */
    public void setDone() {
        this.done = true;
    }


    /**
     * Constructor to create task
     *
     * @param type    string value of ["T"] etc
     * @param details consists of task information
     */
    public Task(String type, String details) {
        this.type = type;
        this.details = details;
    }


    public boolean relevant(String keyWord) {
        if (details.contains(keyWord)) {
            return true;
        }
        return false;
    }

    /**
     * Constructor to create task
     *
     * @param type     string value of ["T"] etc
     * @param details  consists of task information
     * @param deadLine deadline of task
     */
    public Task(String type, String details, String deadLine) {
        this.type = type;
        this.details = details;
        this.deadLine = deadLine;
    }

    /**
     * Constructor to create task
     *
     * @param type          string value of ["T"] etc
     * @param details       consists of task information
     * @param localDeadline deadline of task in LocalDate instance
     */

    public Task(String type, String details, LocalDate localDeadline) {
        this.type = type;
        this.details = details;
        this.localDeadline = localDeadline;

        deadLine = localDeadline.toString();

    }

    /**
     * properly formats string value
     *
     * @returns string of all relevant information of myTasks
     */
    @Override
    public String toString() {
        String checkbox = done ? "[✓]" : "[✗]";
        String myDeadline = deadLine == null ? "" : "-------" + deadLine;

        return this.type + checkbox + " " + this.details + " " + myDeadline;
    }
}
