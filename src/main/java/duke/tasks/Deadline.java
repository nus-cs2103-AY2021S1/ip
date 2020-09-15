package duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.errors.DeadlineException;
import duke.errors.DukeException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;

/**
 * The deadline is a subclass of Task and it is used to describe tasks that has to be completed by a specific day.
 */
public class Deadline extends Task {
    private String dayAndOrTime; //the day and or time that Task deadline had to be completed by
    private LocalDate deadlineInDate;
    private LocalDateTime deadlineInDateAndTime;
    private LocalTime deadlineInTime;

    /**
     * Assigns the name, dayAndOrTime, deadlineInDate with values initializes Deadline task
     *
     * @param name assigns this.name with value
     * @param dayAndOrTime assigns this.dayAndOrTime with value(String format of deadlineInDate)
     * @param deadlineInDate assigns this.deadlineInDate with value
     */
    private Deadline(String name, String dayAndOrTime, LocalDate deadlineInDate) {
        super(name);
        this.dayAndOrTime = dayAndOrTime;
        this.deadlineInDate = deadlineInDate;
    }

    /**
     * Assigns the name, dayAndOrTime, deadlineInDateAndTime with values initializes Deadline task
     *
     * @param name assigns this.name with value
     * @param dayAndOrTime assigns this.dayAndOrTime with value(String format of deadlineInDateAndTime)
     * @param deadlineInDateAndTime assigns this.deadlineInDate with value
     */
    public Deadline(String name, String dayAndOrTime, LocalDateTime deadlineInDateAndTime) {
        super(name);
        this.dayAndOrTime = dayAndOrTime;
        this.deadlineInDateAndTime = deadlineInDateAndTime;
    }

    /**
     * Assigns the name, dayAndOrTime, deadlineInDate with values initializes Deadline task
     *
     * @param name assigns this.name with value
     * @param dayAndOrTime assigns this.dayAndOrTime with value(String format of deadlineInTime)
     * @param deadlineInTime assigns this.deadlineInDate with value
     */
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

    /**
     * Used to add a deadline task to tasks in TaskList and update the tasks file in storage
     *
     * @param tasks contains all the current Tasks in a list, and to update this
     * @param ui to set its DukeException if it is thrown
     * @param storage contains all the current Tasks in a file, and to update this
     * @param userInput String of the user input
     * @return String message that Deadline has been added successfully
     * @throws DukeException thrown if error is present in user input
     */
    public static String addDeadlineTask(TaskList tasks, Ui ui, Storage storage, String userInput)
            throws DukeException {
        try {
            String[] dataSplit = splitData(userInput);
            //Split userInput into description name and time and/or date
            Deadline d = deadlineTask(dataSplit[0], dataSplit[1]); //gives the Deadline
            return updateTaskList(storage, d, tasks); //updates the tasks and file in storage
        } catch (DukeException dukeException) {
            ui.setDukeException(dukeException);
            throw dukeException;
        }
    }
    /**
     * splits the userInput into Deadline description and the Deadline date and/ or time.
     * If the date and/or time is absent then DeadlineException is thrown.
     *
     * @return the String array where the first String is the name of the Deadline and the second is the date
     * and/or time of deadline
     * @throws DeadlineException thrown when the time and/or date is absent.
     */
    private static String[] splitData(String userInput) throws DeadlineException {
        String s = "";
        int index = -1;
        boolean time = false;
        for (int i = 8; i < userInput.length(); i++) {
            if (userInput.charAt(i) == '/') {
                index = i;
                time = true; //since date appears after
                break;
            }
            s = s + userInput.charAt(i);
        }
        if (!time) {
            throw new DeadlineException(false, false, true);
        }
        assert !s.substring(1, s.length() - 1).contains("/"); // description should not contain /
        assert !userInput.substring(index + 4).contains("/by"); ////date and/or time should not contain /at
        String[] dataSplit = new String[]{s.substring(1, s.length() - 1), userInput.substring(index + 4)};
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
                e = new Deadline(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")),
                        parsedDate);
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
     * @param done true if task is done and false if not done and is assigned to isDone variable
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
                LocalDateTime parsedDate = stringToLocalDateTimeExistingTask(dateTime);
                //converts string to date and time
                e = new Deadline(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")),
                        parsedDate);
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
