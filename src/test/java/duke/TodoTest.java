package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
	@Test
	public void testToString() {
		Todo todo = new Todo("arrange meeting");
		assertEquals("[T][\u2718] arrange meeting", todo.toString());
	}

	@Test
	public void testCompleteTask() {
		Todo todo = new Todo("read book");
		todo = todo.completeTask();
		assertEquals("[T][\u2713] read book", todo.toString());
	}

	@Test
	public void testGetData() {
		Todo todo = new Todo("home workout");
		assertEquals("TODO#home workout#false", todo.getData());
	}
}
