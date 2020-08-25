package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

//    @Test
//    void parse() throws DukeException {
//        taskListHandler handler = new taskListHandler(new ArrayList<>());
//        Parser.parse("todo pop-quiz", handler);
//        TodoCommand tdcmd = new TodoCommand(new Todo("todo pop-quiz"));
//        assertEquals(Parser.parse("todo pop-quiz", handler), tdcmd);
//    }

    @Test
    void parseModifyTaskCommand() throws DukeException {
        taskListHandler handler = new taskListHandler(new ArrayList<>());
        handler.addToList(new Deadline("assignment", "8pm"));
        handler.getTasks().get(0).markAsDone();
        Deadline newDeadline = new Deadline("assignment", "8pm");
        newDeadline.markAsDone();
        assertEquals(Parser.parseModifyTaskCommand("done 1", handler), newDeadline);
        System.out.println("Passed: parseModifyTaskCommandTest!");
    }

    @Test
    void parseNewTaskCommand() throws DukeException {
        Todo newToDo = new Todo("assignment");
        String command = "todo assignment";
        assertEquals(Parser.parseNewTaskCommand(command,Task.taskType.TODO), newToDo);
        System.out.println("Passed: parseNewTaskCommandTest!");
    }

    @Test
    void parseTaskWithTime() throws DukeException {
        Event newEvent = new Event("fan-signing", "2pm");
        String command = "event fan-signing /at 2pm";
        assertEquals(Parser.parseTaskWithTime(command, Task.taskType.EVENT, "/at"), newEvent);
        System.out.println("Passed: parseTaskWithTimeTest!");
    }

    @Test
    void checkIsFieldEmpty() {
        String nameOfField = "taskDesc";
        String emptyTaskDesc = "    ";
        DukeException thrown = assertThrows(
                DukeException.class,
                () -> Parser.checkIsFieldEmpty(nameOfField, emptyTaskDesc),
                "Expected checkIsFieldEmpty() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("\u2639 Oops, " + nameOfField + " cannot be empty"));
        System.out.println("Passed: checkIsFieldEmptyTest!");
    }
}