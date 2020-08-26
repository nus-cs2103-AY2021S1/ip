import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
	private final String description = "Todo description";

	@Test
	public void markAsDoneTest() {
		ToDo todo = new ToDo(description, false);
		assertEquals(todo.toString(), "[" + todo.taskType.getSymbol() + "]" + "[\u2718]" + " " + description);
		todo.markAsDone();
		assertEquals(todo.toString(), "[" + todo.taskType.getSymbol() + "]" + "[\u2713]" + " " + description);
	}

	@Test
	public void isOccuringOnTest() throws ParseException {
		Event event = new Event(description, new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-04"), false);
		assertEquals(event.isOccuringOn(new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-04")), true);
		assertEquals(event.isOccuringOn(new SimpleDateFormat("yyyy-MM-dd").parse("2020-04-04")), false);
	}

}