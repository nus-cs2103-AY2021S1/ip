package duke.util;

import duke.command.CommandType;
import duke.task.TaskType;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParseCmdWord() {
        CommandType deadline = Parser.parseCmdWord("deadline lorem ipsum");
        assertEquals(CommandType.Task, deadline);

        CommandType delete = Parser.parseCmdWord("delete 1");
        assertEquals(CommandType.Delete, delete);

        CommandType done = Parser.parseCmdWord("done 3");
        assertEquals(CommandType.Done, done);

        CommandType due = Parser.parseCmdWord("due 2020-08-09");
        assertEquals(CommandType.Due, due);

        CommandType exit = Parser.parseCmdWord("bye");
        assertEquals(CommandType.Exit, exit);

        CommandType event = Parser.parseCmdWord("event lorem ipsum");
        assertEquals(CommandType.Task, event);

        CommandType find = Parser.parseCmdWord("find");
        assertEquals(CommandType.Find, find);

        CommandType invalid = Parser.parseCmdWord("something");
        assertEquals(CommandType.Invalid, invalid);

        CommandType list = Parser.parseCmdWord("list");
        assertEquals(CommandType.List, list);

        CommandType todo = Parser.parseCmdWord("todo lorem ipsum");
        assertEquals(CommandType.Task, todo);
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
