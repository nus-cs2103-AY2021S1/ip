package duke;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskTest {
	@Test
	public void testGetDoneString(){
		assertEquals("[✓]", new duke.Task(duke.TaskType.DEADLINE, true, "aaaaa bbbb").getDoneString());
		assertEquals("[✗]", new duke.Task(duke.TaskType.TODO, false, "abc bbbb").getDoneString());
	}

	@Test
	public void testGetTypeString(){
		assertEquals("[T]", new duke.Task(duke.TaskType.TODO, true, "ERRRR").getTypeString());
		assertEquals("[D]", new duke.Task(duke.TaskType.DEADLINE, false, "aserfasdf").getTypeString());
		assertEquals("[E]", new duke.Task(duke.TaskType.EVENT, false, "asdfsadf").getTypeString());
	}

	@Test
	public void testToString(){
		assertEquals("biaaaa", new duke.Task(duke.TaskType.TODO, true, "biaaaa").toString());
		assertEquals("aswasasuperfasdf", new duke.Task(duke.TaskType.DEADLINE, false, "aswasasuperfasdf").toString());
		assertEquals("gcg", new duke.Task(duke.TaskType.EVENT, false, "gcg").toString());
	}
}
