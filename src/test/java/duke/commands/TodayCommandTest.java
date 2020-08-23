package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.ui.Ui;
import duke.utils.DukeDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodayCommandTest {

    private final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private final PrintStream ORIGINAL_OUT = System.out;
    private final String LINE = "\t" + "_".repeat(75) + "\n";

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(OUT_CONTENT));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(ORIGINAL_OUT);
    }

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
        command.execute(taskList, new Ui());
        String expected = LINE + "\t Here are your tasks today:\n"
                + "\t 1." + deadline1.toString() + "\n"
                + "\t 2." + deadline2.toString()
                + "\n"
                + LINE;
        assertEquals(expected, OUT_CONTENT.toString());

    }
}
