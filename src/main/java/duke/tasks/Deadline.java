package duke.tasks;

import duke.errors.DeadlineException;
import duke.errors.DukeException;
import duke.errors.FileAbsentException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The deadline is a subclass of Task and it is used to describe tasks that has to be completed by a specific day.
 */
public class Deadline extends Task {
    private String dayAndOrTime; //the day and or time that Task deadline had to be completed by
    private LocalDate deadlineInDate;
    private LocalDateTime deadlineInDateAndTime;
    private LocalTime deadlineInTime;
    /**
     * Assigns the name and day values, initializes Deadline task
     *
     * @param name super(name) so that it does whatever is mentioned in the parent class
     * @param day assigns this.day to day value
     */
    public Deadline(String name, String day) {
        super(name);
        this.dayAndOrTime = day;
    }

    /**
     * Assigns the name, done and day values, initializes Deadline task
     *
     * @param name argument in super class constructor
     * @param done argument in super class constructor
     * @param dayAndOrTime assigns to this.day
     */
    public Deadline(String name, boolean done, String dayAndOrTime) {
        super(name, done);
        this.dayAndOrTime = dayAndOrTime;
    }

    public Deadline(String name, String dayAndOrTime, LocalDate deadlineInDate) {
        super(name);
        this.dayAndOrTime = dayAndOrTime;
        this.deadlineInDate = deadlineInDate;
    }
    public Deadline(String name, String dayAndOrTime, LocalDateTime deadlineInDateAndTime) {
        super(name);
        this.dayAndOrTime = dayAndOrTime;
        this.deadlineInDateAndTime = deadlineInDateAndTime;
    }
    public Deadline(String name, String dayAndOrTime, LocalTime deadlineInTime) {
        super(name);
        this.dayAndOrTime = dayAndOrTime;
        this.deadlineInTime = deadlineInTime;
    }
    /**
     * Takes no arguments and overrides the toString method
     *
     * @return the specific representation for deadline class as mentioned with [D]
     * indicating that it is a deadline class
     * and also mentions the deadline.
     */
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.dayAndOrTime + ")";
    }

    /**
     * Gives a specific string representation for that in the tasks.txt file and overrides that in Task to make
     * it unique to that for Deadline
     *
     * @return the string representation
     */
    public String inputListFormat() {
        String s = super.inputListFormat();
        return "D" + super.inputListFormat() + "| " + this.dayAndOrTime; //format of Tasks to appear in file in Storage
    }

    public static String addDeadlineTask(TaskList tasks, Ui ui, Storage storage, String commandDescription)
            throws DukeException {
        try {
            String[] dataSplit = splitData(commandDescription); //Split into description name and time and/or date
            Deadline d = deadlineTask(dataSplit[0], dataSplit[1]); //gives the Deadline
            return updateTaskList(storage, d, tasks); //updates the tasks and file in storage
        } catch (DukeException dukeException) {
            ui.setDukeException(dukeException);
            throw dukeException;
        }
    }
    /**
     * splits the data into Deadline description and the Deadline date and/ or time. If the date and/or time is absent
     * then DeadlineException is thrown.
     *
     * @return the String array where the first String is the name of the Deadline and the second is the date
     * and/or time of deadline
     * @throws DeadlineException thrown when the time and/or date is absent.
     */
    private static String[] splitData(String commandDescription) throws DeadlineException {
        String s = "";
        int index = -1;
        boolean time = false;
        for (int i = 8; i < commandDescription.length(); i++) {
            if (commandDescription.charAt(i) == '/') {
                index = i;
                time = true; //since date appears after
                break;
            }
            s = s + commandDescription.charAt(i);
        }
        if (!time) {
            throw new DeadlineException(false, false, true);
        }
        assert !s.substring(1, s.length() - 1).contains("/"); // description should not contain /
        assert !commandDescription.substring(index + 4).contains("/by"); ////date and/or time should not contain /at
        String[] dataSplit = new String[]{s.substring(1, s.length() - 1), commandDescription.substring(index + 4)};
        return dataSplit;
    }

    /**
     * This method creates a deadline task by checking whether the date and/or time given is in the correct
     * format. If it is then Deadline task is returned else, DeadlineException is returned.
     *
     * @param name description of Deadline task
     * @param dateTime gives the dateTime, to check whether they are in the correct format
     * @return deadline if the dateTime is in the correct format
     * @throws DeadlineException if the dateTime is in the incorrect format
     */
    private static Deadline deadlineTask(String name, String dateTime) throws DeadlineException {
        Deadline e;
        try {
            LocalDate parsedDate = stringToLocalDate(dateTime); //converts string to date
            e = new Deadline(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy")), parsedDate);
        } catch (DateTimeException d) {
            try {
                LocalDateTime parsedDate = stringToLocalDateTime(dateTime); //converts string to date and time
                e = new Deadline(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")), parsedDate);
            } catch (DateTimeException g) {
                try {
                    LocalTime parsedDate = stringToLocalTime(dateTime); //converts string to date
                    e = new Deadline(name, parsedDate.format(DateTimeFormatter.ofPattern("HH:mm")), parsedDate);
                } catch (DateTimeException f) {
                    throw new DeadlineException(false, true, false);
                }
            }
        }
        return e;
    }
    /**
     * This method creates a deadline task by checking whether the date and/or time given is in the correct
     * format. If it is then Deadline task is returned else, DeadlineException is returned.
     *
     * @param name description of Deadline task
     * @param dateTime gives the dateTime, to check whether they are in the correct format
     * @return deadline if the dateTime is in the correct format
     * @throws DeadlineException if the dateTime is in the incorrect format
     */
    public static Deadline deadlineTask(String name, String dateTime, boolean done) throws DeadlineException {
        Deadline e;
        try {
            LocalDate parsedDate = stringToLocalDateExistingTask(dateTime); //converts string to date
            e = new Deadline(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy")), parsedDate);
        } catch (DateTimeException d) {
            try {
                LocalDateTime parsedDate = stringToLocalDateTimeExistingTask(dateTime); //converts string to date and time
                e = new Deadline(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")), parsedDate);
            } catch (DateTimeException g) {
                try {
                    LocalTime parsedDate = stringToLocalTimeExistingTask(dateTime); //converts string to date
                    e = new Deadline(name, parsedDate.format(DateTimeFormatter.ofPattern("HH:mm")), parsedDate);
                } catch (DateTimeException f) {
                    throw new DeadlineException(false, true, false);
                }
            }
        }
        e.setDone(done);
        return e;
    }

}
