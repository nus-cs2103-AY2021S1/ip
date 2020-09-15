package duke.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import duke.TimeModification;
import duke.tasks.Task;
import duke.tasks.TaskEvent;
import duke.tasks.TaskList;

public class CommandReschedulePostponeTest {

    @Test
    public void postponeCommand_correctEventInput_executePostponeEvent() {
        TaskList taskList = new TaskList();
        Task event = new TaskEvent("john mayer concert", LocalDate.of(2020, 9, 10),
                LocalTime.of(18, 0), LocalTime.of(21, 0));
        taskList.add(event);
        TimeModification timeMod = new TimeModification(0, 2, ChronoUnit.HOURS);
        Command postponeCommand = new CommandReschedulePostpone(taskList, timeMod);

        String newTime = "Sep 10 2020, 2000hrs - 2300hrs";
        boolean containsCorrectOutput = postponeCommand.execute().contains(newTime);
        assertTrue(containsCorrectOutput);
    }

}
