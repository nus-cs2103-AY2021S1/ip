package duke.commands;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.tasks.TaskList;

public abstract class CommandReschedule extends Command {

    public static final List<ChronoUnit> VALID_TIME_UNITS = new ArrayList<>(Arrays.asList(ChronoUnit.MINUTES,
            ChronoUnit.HOURS, ChronoUnit.DAYS, ChronoUnit.MONTHS, ChronoUnit.YEARS));

    public static final String COMMAND_STRING = "reschedule";

    protected int taskIndex;

    /**
     * Constructor for {@code CommandReschedule}.
     *
     * @param taskList
     */
    public CommandReschedule(TaskList taskList, int taskIndex) {
        super(taskList);
        this.taskIndex = taskIndex;
    }
}
