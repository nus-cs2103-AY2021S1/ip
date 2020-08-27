package Duke.task;

import Duke.exception.DukeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

	private TaskList tasks;
	private Todo todoTask = new Todo("valid todo");
	private Deadline deadlineTask = new Deadline("valid deadline","2019-12-01 00:11");
	private Event eventTask = new Event("valid event", "2020-08-22 23:32");

	@BeforeEach
	public void init(){
		tasks = new TaskList();
		tasks.add(todoTask);
		tasks.add(deadlineTask);
		tasks.add(eventTask);
	}



	@Test
	public void getSizeTest() {
		assertEquals(3, tasks.getSize());
	}

	@Test
	public void testStringConversion() {
		assertEquals("1.[T][✘] valid todo\n" +
				"2.[D][✘] valid deadline (by: Dec 1 2019 00:11)\n" +
				"3.[E][✘] valid event (at: Aug 22 2020 23:32)", tasks.toString());

		assertEquals("" ,new TaskList().toString());
	}

	@Test
	public void testConstructor_validString_taskListCreated() throws DukeException {
		String validString = "T,0,valid todo|D,1,valid deadline,2019-12-01 00:11|";
		TaskList tl = new TaskList(validString);
		assertEquals("1.[T][✘] valid todo\n" +
				"2.[D][✓] valid deadline (by: Dec 1 2019 00:11)",tl.toString());

	}

	@Test
	public void testConstructor_invalidString_exceptionThrown() throws DukeException {
		String invalidString = "T,0";
		try {
			TaskList tl = new TaskList(invalidString);
			assertEquals("1.[T][✘] valid todo", tl.toString());
			fail();
		} catch (DukeException e) {
			assertEquals("Index 2 out of bounds for length 2", e.getMessage());
		}

		String invalidString2 = "T,0,dsad|D,k,vs,3";
		try {
			TaskList tl2 = new TaskList(invalidString2);
			assertEquals("1.[T][✘] valid todo", tl2.toString());
			fail();
		} catch (DukeException e) {
			assertEquals("file could not be parsed", e.getMessage());
		}

	}

	@Test
	public void getTaskTest() {

		assertEquals(todoTask,tasks.get(0));
		assertEquals(deadlineTask,tasks.get(1));
		assertEquals(eventTask,tasks.get(2));
	}

	@Test
	public void addTest() {
		tasks.add(todoTask);

		assertEquals(4, tasks.getSize());
		assertEquals("1.[T][✘] valid todo\n" +
				"2.[D][✘] valid deadline (by: Dec 1 2019 00:11)\n" +
				"3.[E][✘] valid event (at: Aug 22 2020 23:32)\n" +
				"4.[T][✘] valid todo", tasks.toString());
	}

	@Test
	public void deleteTest() {
		assertEquals(3, tasks.getSize());

		//delete last task
		assertEquals(eventTask,tasks.delete(3));
		assertEquals(2, tasks.getSize());

		//delete first task
		tasks.delete(1);
		assertEquals(1, tasks.getSize());


	}

	@Test
	public void markDoneTest() {
		tasks.markDone(1);
		tasks.markDone(3);

		assertEquals("1.[T][✓] valid todo\n" +
				"2.[D][✘] valid deadline (by: Dec 1 2019 00:11)\n" +
				"3.[E][✓] valid event (at: Aug 22 2020 23:32)", tasks.toString());

	}




	@Test
	public void getListTest() {
		//form external list
		List<Task> lst = new ArrayList<>();
		lst.add(todoTask);
		lst.add(deadlineTask);
		lst.add(eventTask);
		assertEquals(lst,tasks.getList());
	}


}
