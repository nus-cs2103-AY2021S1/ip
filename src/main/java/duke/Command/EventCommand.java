package duke.Command;

import duke.Exception.EventException;
import duke.Storage;
import duke.Task.Event;
import duke.Task.Task;
import duke.TaskList;
import duke.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class EventCommand extends Command {

    private final int day;
    private final int month;
    private final int year;
    private final int hour;
    private final int min;

    public EventCommand(String input, int day, int month, int year, int hour, int min) {
        super(input);
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.min = min;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EventException {

        LocalDateTime dateTime = null;
        try {
            dateTime = LocalDateTime.of(year, month, day, hour, min);
        } catch (DateTimeException e) {
            throw new EventException("Please input date time format in DD-MM-YYYY HHHH format!\n" +
                    "Eg: 2-12-2019 1800 OR 02-12-2019 1800");
        }
        System.out.println("Got it. I've added this task:");
        Task task = new Event(input, dateTime);
        tasks.add(task);
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
