package duke.Command;

import duke.Exception.DeadlineException;
import duke.Storage;
import duke.Task.Deadline;
import duke.Task.Task;
import duke.TaskList;
import duke.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command {

    private final int day;
    private final int month;
    private final int year;
    private final int hour;
    private final int min;

    /**
     * Constructs a Deadline object with it's input, date and time specified
     * @param input User's input that is processed by the Deadline Object
     * @param day the day-of-month to represent, from 1 to 31
     * @param month the month-of-year to represent, from 1 (January) to 12 (December)
     * @param year the year to represent, from -999999999 to +999999999
     * @param hour the hour-of-day to represent, from 0 to 23
     * @param min the minute-of-hour to represent, from 0 to 59
     */
    public DeadlineCommand(String input, int day, int month, int year, int hour, int min) {
        super(input);
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.min = min;
    }

    /**
     * Invokes the DeadlineCommand object to process the User's request based on User's input
     * @param tasks TaskList that contains an ArrayList of Task
     * @param ui Ui object that interacts with User
     * @param storage Storage object that reads from/write to specified filePath
     * @throws DeadlineException if User's input is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeadlineException {

        LocalDateTime dateTime = null;
        try {
            dateTime = LocalDateTime.of(year, month, day, hour, min);
        } catch (DateTimeException e) {
            throw new DeadlineException("Please input date time format in DD-MM-YYYY HHHH format!\n" +
                    "Eg: 2-12-2019 1800 OR 02-12-2019 1800");
        }

        System.out.println("Got it. I've added this task:");
        Task task = new Deadline(input, dateTime);
        tasks.add(task);
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

    }

    /**
     * Returns false as DeadlineCommand is not for termination
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
