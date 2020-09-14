package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

import duke.exceptions.DukeDateTimeException;
/**
 * TimedTask is a abstract class that inherits from the base Task
 * class, to add a new field of datetime into this child class which implements such functionalities
 */
abstract class TimedTask extends Task {
    protected static final DateTimeFormatter FMAT;
    protected static final LocalDate NOW = LocalDateTime.now().toLocalDate();
    static {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder.appendPattern("dd-MM-yyyy");
        dateTimeFormatterBuilder.parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0);
        dateTimeFormatterBuilder.parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0);
        dateTimeFormatterBuilder.parseDefaulting(ChronoField.HOUR_OF_DAY, 0);
        dateTimeFormatterBuilder.parseDefaulting(ChronoField.YEAR_OF_ERA, LocalDateTime.now().getYear());
        dateTimeFormatterBuilder.parseDefaulting(ChronoField.MONTH_OF_YEAR, LocalDateTime.now().getMonthValue());
        dateTimeFormatterBuilder.parseDefaulting(ChronoField.DAY_OF_MONTH, LocalDateTime.now().getDayOfMonth());
        FMAT = dateTimeFormatterBuilder
                .toFormatter();
    }
    protected final LocalDateTime dateby;
    /**
     * Constructor for a TimedTask for use by implementing subclasses, in particular to populate
     * Tasks that have been read from a text save file.
     * @param desc Description of a task
     * @param date String that is extracted from user input to be parsed into the constructor for a TimedTask object
     * @param done Done Status of a task
     * @throws DukeDateTimeException if the fields for the date are not matching autocorrection cases
     */
    protected TimedTask(String desc, String date, Boolean done) throws DukeDateTimeException {
        super(desc, done);
        try {
            if (NOW.format(FMAT).length() > date.length()) {
                date = date + NOW.format(FMAT).substring(date.length());
            }
            dateby = LocalDateTime.parse(date, FMAT);
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeException("The String you entered does not meet the "
                    + "required format of 'yyyy-MM-dd' ");
        }

    }

    /**
     * Constructor for a TimedTask for use by implementing subclasses, in particular to create
     * a new TimedTask class
     * @param desc Description of a task
     * @param date String that is extracted from user input to be parsed into the constructor for
     *            a TimedTask object
     * @throws DukeDateTimeException if the fields for the date are not matching autocorrection cases
     */
    protected TimedTask(String desc, String date) throws DukeDateTimeException {
        this(desc, date, false);
    }
    /**
     * Performs a DateTime Arithmetric calculation with the current time of execution of the program
     * in order to display a countdown of days in the Task
     * @return integer representing the number of days left or past since the task was due.
     */
    protected int timeLeft() {
        return Period.between(NOW, LocalDate.from(dateby)).getDays();
    }

    /**
     * Get the dateby for the set task
     * @return dateby for the registered task
     */
    public String getDateby() {
        return dateby.toLocalDate().format(FMAT);
    }
    /**
     * Returns a String Representation of the Event object class to write to text file.
     * @return the saved task to write to a text file
     */
    @Override
    public String saveTask() {
        return super.saveTask() + SEPERATOR + getDateby();
    }
}
