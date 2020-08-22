package parser;

import exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;
import service.DeadlineTask;
import service.Task;

import static org.junit.jupiter.api.Assertions.fail;

public class TaskParserTest {
    @Test
    public void ParseFailedTest() {
        TaskParser parser = new TaskParser();
        parser.registerTask(DeadlineTask::new, DeadlineTask.taskWord);

        String rawTask = "event meet Chau \\at 2020-09-09";
        try {
            Task newTask = parser.parse(rawTask.split(" "));
            fail("Should parse failed");
        } catch (InvalidCommandException e) {
            ///okay
        }
    }

    @Test
    public void NotRegistered() {
        TaskParser parser = new TaskParser();

        String rawTask = "todo meet Chau";
        try {
            Task newTask = parser.parse(rawTask.split(" "));
            fail("not registered");
        } catch (InvalidCommandException e) {
            //okay
        }
    }
}
