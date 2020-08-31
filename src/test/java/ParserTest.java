package duke;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {
	@Test
	public void testParse1(){
		String string;
		try {
			string = duke.Parser.parse("bye").execute(new duke.TaskList(), new duke.Ui(), new duke.Storage("duke"));
		} catch (duke.DukeException e) {
			string = e.getMessage();
		}
		assertEquals("Bye. Hope to see you again soon!", string);
	}
	@Test
	public void testParse2(){
		String string;
		try {
			string = duke.Parser.parse("asdas").execute(new duke.TaskList(), new duke.Ui(), new duke.Storage("duke"));
		} catch (duke.DukeException e) {
			string = e.getMessage();
		}
		assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", string);
	}
	@Test
	public void testParse3(){
		String outputstring, expectedString;
		try {
			outputstring = duke.Parser.parse("todo borrow book").execute(new duke.TaskList(), new duke.Ui(), new duke.Storage("duke"));
		} catch (duke.DukeException e) {
			outputstring = e.getMessage();
		}
		expectedString = "Got it. I've added this task:\n [T][✗] borrow book\nNow you have 1 tasks in the list.";
		assertEquals(expectedString, outputstring);
	}
}