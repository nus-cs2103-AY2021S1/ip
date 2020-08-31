package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {
    @Test
    void testTodoCorrectDescription() {
        Todo todo = new Todo("Deliver Parcel", true);
        String todoText = todo.toString();
        String todoDBText = todo.getDescriptionForDatabase();
        assertEquals("todo - 1 - Deliver Parcel", todoDBText);
        assertEquals("[T][✓] Deliver Parcel", todoText);
    }

    @Test
    void testEventCorrectDescription() {
        Event event = new Event("Concert with Friends", "OCTOBER 20 2021 12:45 AM", false);
        String eventDescription = event.toString();
        String eventDBDescription = event.getDescriptionForDatabase();
        assertEquals("event - 0 - Concert with Friends - OCTOBER 20 2021 12:45 AM", eventDBDescription);
        assertEquals("[E][X] Concert with Friends (at: OCTOBER 20 2021 12:45 AM)", eventDescription);
    }

    @Test
    void testDeadlineCorrectDescription() {
        Deadline deadline = new Deadline(
                "Individual Project Week 5 Deliverables",
                "ASAP!",
                false);
        String deadlineDescription = deadline.toString();
        String deadlineDBDescription = deadline.getDescriptionForDatabase();
        assertEquals("deadline - 0 - Individual Project Week 5 Deliverables - ASAP!", deadlineDBDescription);
        assertEquals("[D][X] Individual Project Week 5 Deliverables (by: ASAP!)", deadlineDescription);
    }
}