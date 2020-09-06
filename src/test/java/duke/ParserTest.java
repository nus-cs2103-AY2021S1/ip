package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void isByeTest() {
        String bye = "bye";
        String notBye = "not bye";
        assert(Parser.isBye(bye));
        assert(!Parser.isBye(notBye));
    }

    @Test
    public void isListTest() {
        String list = "list";
        String notList = "not list";
        assert(Parser.isList(list));
        assert(!Parser.isList(notList));
    }

    @Test
    public void isDoneTest() {
        String done = "done";
        String notDone = "not done";
        assert(Parser.isDone(done));
        assert(!Parser.isDone(notDone));
    }

    @Test
    public void isDeleteTest() {
        String delete = "delete";
        String notDelete = "not delete";
        assert(Parser.isDelete(delete));
        assert(!Parser.isDelete(notDelete));
    }

    @Test
    public void isFindTest() {
        String find = "find";
        String notFind = "not find";
        assert(Parser.isFind(find));
        assert(!Parser.isFind(notFind));
    }

    @Test
    public void parseTaskTest() throws Exception {
        String todoRead = "todo read";
        String eventRead = "event read /at 2am";
        String deadlineRead = "deadline read /by 2020-09-24 02:00";

        Task todoParsed = Parser.parseTask(todoRead);
        Task eventParsed = Parser.parseTask(eventRead);
        Task deadlineParsed = Parser.parseTask(deadlineRead);

        assertEquals(todoParsed.getType(), TaskType.ToDo);
        assertEquals(eventParsed.getType(), TaskType.Event);
        assertEquals(deadlineParsed.getType(), TaskType.Deadline);
    }
}
