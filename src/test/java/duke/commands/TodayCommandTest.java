package duke.commands;

import static duke.utils.Messages.MESSAGE_TODAY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.utils.DukeDateTime;

public class TodayCommandTest {

    @Test
    public void testExecute() {
        LocalDateTime todayNoTime = LocalDate.now().atStartOfDay();
        LocalDateTime todayWithTime = LocalDateTime.now();
        LocalDateTime tomorrow = todayNoTime.plusDays(1);
        Deadline deadline1 = new Deadline("deadline1",
                new DukeDateTime(todayNoTime, false));
        Deadline deadline2 = new Deadline("deadline2",
                new DukeDateTime(todayWithTime, true));
        Deadline deadline3 = new Deadline("deadline3",
                new DukeDateTime(tomorrow, false));
        TaskList taskList = new TaskList();
        taskList.addTask(deadline1);
        taskList.addTask(deadline2);
        taskList.addTask(deadline3);
        TodayCommand command = new TodayCommand();
        CommandResult actual = command.execute(taskList);
        String response = MESSAGE_TODAY + "\t 1." + deadline1.toString() + "\n" + "\t 2."
                + deadline2.toString();
        CommandResult expected = new CommandResult(response, false);
        assertEquals(expected, actual);

    }
}
