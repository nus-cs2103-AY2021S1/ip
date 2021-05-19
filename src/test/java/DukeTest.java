import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    Task toDo = new ToDo("Eat bread");
    Task event = new Event("Eat bread", "tomorrow");
    Task deadline = new Deadline("Eat bread", "tomorrow");
    Parser parser = new Parser();
    TaskList taskList = new TaskList(new ArrayList<Task>());

    @Test
    public void ToDoTest(){
        assertEquals("Eat bread", toDo.getTask());
    }

    @Test
    public void EventTest() {
        assertEquals("Eat bread", event.getTask());
    }

    @Test
    public void DeadlineTest() {
        assertEquals("Eat bread", deadline.getTask());
    }

    @Test
    public void ParserDeadlineTest() throws DukeException {
        assertEquals(Parser.Command.DEADLINE, parser.parse("deadline eat food /by tomorrow"));
    }

    @Test
    public void ParserEventTest() throws DukeException {
        assertEquals(Parser.Command.EVENT, parser.parse("event eat food /at tomorrow night"));
    }

    @Test
    public void ParserTodoTest() throws DukeException {
        assertEquals(Parser.Command.TODO, parser.parse("todo eat food"));
    }

    @Test
    public void ParserEmptyTaskTest() {
    }

    @Test
    public void ParserEmptyDateTest() {
    }

    @Test
    public void TaskListTest() {
        taskList.add(event);
        taskList.add(toDo);
        taskList.add(deadline);
        assertEquals(3, taskList.size());

        taskList.set(0, event);
        assertEquals(3, taskList.size());

        taskList.remove(1);
        assertEquals(2, taskList.size());

        assertEquals(event, taskList.get(0));

        assertEquals(2, taskList.find("bread").size());


    }


}