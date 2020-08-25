package data.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    final LocalDateTime dateTime = LocalDateTime.parse("2020-10-18T14:30");
    final Todo todo = new Todo("read book");
    final Deadline deadline = new Deadline("return book", dateTime);
    final Event event = new Event("project meeting", dateTime);
    final Todo completedTodo = new Todo(true,"read book");
    final Deadline completeDeadline = new Deadline(true,"return book", dateTime);
    final Event completedEvent = new Event(true,"project meeting", dateTime);

    @Test
    void getStatusIcon() {
        assertEquals("X", todo.getStatusIcon());
        assertEquals("X", deadline.getStatusIcon());
        assertEquals("X", event.getStatusIcon());
        assertEquals("O", completedTodo.getStatusIcon());
        assertEquals("O", completeDeadline.getStatusIcon());
        assertEquals("O", completedEvent.getStatusIcon());
    }

}