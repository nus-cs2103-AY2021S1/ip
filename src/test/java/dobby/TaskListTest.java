package test.java.dobby;

import main.java.dobby.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.ParseException;
import java.time.LocalDate;

public class TaskListTest {

    @Test
    public void taskListTest () {
        TaskList tasks = new TaskList();

        try {
            tasks.createFromStorage("[D][✘] ip task for week 3 (by: Aug 27 2020 11:59 pm)");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String allTasks = tasks.getListedTasks();
        assertEquals("\n    1. [D][✘] ip task for week 3 (by: Aug 27 2020 11:59 pm)\n    ", allTasks);

        LocalDate date = LocalDate.parse("2020-08-27");
        String scheduledTasks = tasks.getScheduledTasks(date);
        assertEquals("\n    1. [D][✘] ip task for week 3 (by: Aug 27 2020 11:59 pm)\n    ", scheduledTasks);
    }

}
