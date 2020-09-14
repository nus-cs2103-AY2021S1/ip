package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class to test the Task class.
 */
class TaskTest {

    /**
     * Tests if a sample to-do Task produces the right
     * description for both the Ui and the Database.
     */
    @Test
    void testTodoCorrectDescription() {
        Todo todo = new Todo("Deliver Parcel", true);
        String todoText = todo.toString();
        String todoDatabaseText = todo.getDescriptionForDatabase();
        assertEquals("todo - 1 - Deliver Parcel", todoDatabaseText);
        assertEquals("[T][✓] Deliver Parcel", todoText);
    }

    /**
     * Tests if a sample Event produces the right
     * description for both the Ui and the Database.
     */
    @Test
    void testEventCorrectDescription() {
        Event event = new Event("Concert with Friends", "OCTOBER 20 2021 12:45 AM", false);
        String eventDescription = event.toString();
        String eventDatabaseDescription = event.getDescriptionForDatabase();
        assertEquals("event - 0 - Concert with Friends - OCTOBER 20 2021 12:45 AM", eventDatabaseDescription);
        assertEquals("[E][X] Concert with Friends (at: OCTOBER 20 2021 12:45 AM)", eventDescription);
    }

    /**
     * Tests if a sample Deadline Task produces the right
     * description for both the Ui and the Database.
     */
    @Test
    void testDeadlineCorrectDescription() {
        Deadline deadline = new Deadline(
                "Individual Project Week 5 Deliverables",
                "ASAP!",
                false);
        String deadlineDescription = deadline.toString();
        String deadlineDatabaseDescription = deadline.getDescriptionForDatabase();
        assertEquals("deadline - 0 - Individual Project Week 5 Deliverables - ASAP!", deadlineDatabaseDescription);
        assertEquals("[D][X] Individual Project Week 5 Deliverables (by: ASAP!)", deadlineDescription);
    }
}
