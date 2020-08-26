package duke.util;

import duke.command.CommandType;
import duke.task.TaskType;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParseCmdWord() {
        CommandType exit = Parser.parseCmdWord("bye");
        assertEquals(CommandType.Exit, exit);

        CommandType list = Parser.parseCmdWord("list");
        assertEquals(CommandType.List, list);

        CommandType done = Parser.parseCmdWord("done 3");
        assertEquals(CommandType.Done, done);

        CommandType delete = Parser.parseCmdWord("delete 1");
        assertEquals(CommandType.Delete, delete);

        CommandType due = Parser.parseCmdWord("due 2020-08-09");
        assertEquals(CommandType.Due, due);

        CommandType deadline = Parser.parseCmdWord("deadline lorem ipsum");
        assertEquals(CommandType.Task, deadline);

        CommandType event = Parser.parseCmdWord("event lorem ipsum");
        assertEquals(CommandType.Task, event);

        CommandType todo = Parser.parseCmdWord("todo lorem ipsum");
        assertEquals(CommandType.Task, todo);

        CommandType invalid = Parser.parseCmdWord("something");
        assertEquals(CommandType.Invalid, invalid);
    }

    @Test
    public void testParseTaskType() {
        TaskType deadline = Parser.parseTaskType("deadline lorem ipsum");
        assertEquals(TaskType.Deadline, deadline);

        TaskType event = Parser.parseTaskType("event lorem ipsum");
        assertEquals(TaskType.Event, event);

        TaskType todo = Parser.parseTaskType("todo lorem ipsum");
        assertEquals(TaskType.Todo, todo);

        TaskType invalid = Parser.parseTaskType("something");
        assertEquals(TaskType.Invalid, invalid);
    }
}
