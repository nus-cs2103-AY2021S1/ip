package task;

import exception.DateTimeInvalidFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {

    @Test
    public void testToString() {

        try {
            DeadlineTask task = new DeadlineTask("Hello", "2020-08-25 2359");
            DeadlineTask task2 = new DeadlineTask("Jello", "2020-12-25 1359");
            DeadlineTask task3 = new DeadlineTask("Pello", "2020-09-25 2059");

            assertEquals("[D][✘] Hello (by: 25 Aug 2020 23:59)", task.toString());
            assertEquals("[D][✘] Jello (by: 25 Dec 2020 13:59)", task2.toString());
            assertEquals("[D][✘] Pello (by: 25 Sep 2020 20:59)", task3.toString());
        } catch (DateTimeInvalidFormatException e) {
            String temp = "    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! "
                    + "Action invalid. Date and Time Format incorrect."
                    + "\n"
                    + "    ____________________________________________________________\n";
            assertEquals(temp, e.getMessage());
        }
    }

    @Test
    public void testGetDateInput() {
        try {
            DeadlineTask task = new DeadlineTask("Hello", "2020-08-25 2359");
            DeadlineTask task2 = new DeadlineTask("Jello", "2020-12-25 1359");
            DeadlineTask task3 = new DeadlineTask("Pello", "2020-09-25 2059");

            assertEquals("2020-08-25", task.getDateInput());
            assertEquals("2020-12-25", task2.getDateInput());
            assertEquals("2020-09-25", task3.getDateInput());
        } catch (DateTimeInvalidFormatException e) {
            String temp = "    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! "
                    + "Action invalid. Date and Time Format incorrect."
                    + "\n"
                    + "    ____________________________________________________________\n";
            assertEquals(temp, e.getMessage());
        }
    }

    @Test
    public void testGetTimeInput() {
        try {
            DeadlineTask task = new DeadlineTask("Hello", "2020-08-25 2359");
            DeadlineTask task2 = new DeadlineTask("Jello", "2020-12-25 1359");
            DeadlineTask task3 = new DeadlineTask("Pello", "2020-09-25 2059");

            assertEquals("2359", task.getTimeInput());
            assertEquals("1359", task2.getTimeInput());
            assertEquals("2059", task3.getTimeInput());
        } catch (DateTimeInvalidFormatException e) {
            String temp = "    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! "
                    + "Action invalid. Date and Time Format incorrect."
                    + "\n"
                    + "    ____________________________________________________________\n";
            assertEquals(temp, e.getMessage());
        }
    }
}
