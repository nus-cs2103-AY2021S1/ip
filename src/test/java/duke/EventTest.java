package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
	@Test
	public void testToString() {
		Event event = new Event("attend meeting", "2020-08-27");
		assertEquals("[E][\u2718] attend meeting (at: THURSDAY, Aug 27 2020)", event.toString());
	}

	@Test
	public void testCompleteTask() {
		Event event = new Event("buy books", "6pm");
		event = event.completeTask();
		assertEquals("[E][\u2713] buy books (at: 6pm)", event.toString());
	}

	@Test
	public void testGetData() {
		Event event = new Event("workout", "2020-08-27");
		assertEquals("EVENT#workout#false#2020-08-27", event.getData());
	}
}
