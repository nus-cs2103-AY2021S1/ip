package duke.command;

import duke.task.TaskType;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandParserTest {
    @Test
    public void parseCmdWord() {
        CommandType exit = CommandParser.parseCmdWord("bye");
        assertEquals(CommandType.Exit, exit);

        CommandType list = CommandParser.parseCmdWord("list");
        assertEquals(CommandType.List, list);

        CommandType done = CommandParser.parseCmdWord("done 3");
        assertEquals(CommandType.Done, done);

        CommandType delete = CommandParser.parseCmdWord("delete 1");
        assertEquals(CommandType.Delete, delete);

        CommandType due = CommandParser.parseCmdWord("due 2020-08-09");
        assertEquals(CommandType.Due, due);

        CommandType deadline = CommandParser.parseCmdWord("deadline lorem ipsum");
        assertEquals(CommandType.Task, deadline);

        CommandType event = CommandParser.parseCmdWord("event lorem ipsum");
        assertEquals(CommandType.Task, event);

        CommandType todo = CommandParser.parseCmdWord("todo lorem ipsum");
        assertEquals(CommandType.Task, todo);

        CommandType invalid = CommandParser.parseCmdWord("something");
        assertEquals(CommandType.Invalid, invalid);
    }

    @Test
    public void parseTaskType() {
        TaskType deadline = CommandParser.parseTaskType("deadline lorem ipsum");
        assertEquals(TaskType.Deadline, deadline);

        TaskType event = CommandParser.parseTaskType("event lorem ipsum");
        assertEquals(TaskType.Event, event);

        TaskType todo = CommandParser.parseTaskType("todo lorem ipsum");
        assertEquals(TaskType.Todo, todo);

        TaskType invalid = CommandParser.parseTaskType("something");
        assertEquals(TaskType.Invalid, invalid);
    }
}
