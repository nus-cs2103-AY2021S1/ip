import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;
import duke.utility.Parser;

public class ParserTest {
    @Test
    public void parseTaskForSavingTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String taskName = "Sample task";
        LocalDateTime dateTest = LocalDateTime.parse("2020-01-01 0000", formatter);

        Task toDoTask = new ToDoTask(taskName);
        Task deadlineTask = new DeadlineTask(taskName, dateTest);
        Task eventTask = new EventTask(taskName, dateTest);

        String parsedToDoTaskNotDone = Parser.parseForSave(toDoTask);
        String parsedDeadlineTaskNotDone = Parser.parseForSave(deadlineTask);
        String parsedEventTaskNotDone = Parser.parseForSave(eventTask);

        String expectedToDoNotDone = "T | 0 | Sample task\n";
        String expectedDeadlineNotDone = "D | 0 | Sample task | 2020-01-01 0000\n";
        String expectedEventNotDone = "E | 0 | Sample task | 2020-01-01 0000\n";

        Assertions.assertEquals(expectedToDoNotDone, parsedToDoTaskNotDone);
        Assertions.assertEquals(expectedDeadlineNotDone, parsedDeadlineTaskNotDone);
        Assertions.assertEquals(expectedEventNotDone, parsedEventTaskNotDone);

        toDoTask.setStatusToDone();
        deadlineTask.setStatusToDone();
        eventTask.setStatusToDone();

        String parsedToDoTaskDone = Parser.parseForSave(toDoTask);
        String parsedDeadlineTaskDone = Parser.parseForSave(deadlineTask);
        String parsedEventTaskDone = Parser.parseForSave(eventTask);

        String expectedToDoDone = "T | 1 | Sample task\n";
        String expectedDeadlineDone = "D | 1 | Sample task | 2020-01-01 0000\n";
        String expectedEventDone = "E | 1 | Sample task | 2020-01-01 0000\n";

        Assertions.assertEquals(expectedToDoDone, parsedToDoTaskDone);
        Assertions.assertEquals(expectedDeadlineDone, parsedDeadlineTaskDone);
        Assertions.assertEquals(expectedEventDone, parsedEventTaskDone);
    }

    @Test
    public void parseToDoTaskForReadingFileTest() {
        String readToDoNotDone = "T | 0 | Sample task";
        String readToDoDone = "T | 1 | Sample task";
        String readDeadlineNotDone = "D | 0 | Sample task | 2020-01-01 0000";
        String readDeadlineDone = "D | 1 | Sample task | 2020-01-01 0000";
        String readEventNotDone = "E | 0 | Sample task | 2020-01-01 0000";
        String readEventDone = "E | 1 | Sample task | 2020-01-01 0000";

        Task taskToDoNotDone = Parser.parseForReadingFile(readToDoNotDone);
        Task taskToDoDone = Parser.parseForReadingFile(readToDoDone);
        Task taskDeadlineNotDone = Parser.parseForReadingFile(readDeadlineNotDone);
        Task taskDeadlineDone = Parser.parseForReadingFile(readDeadlineDone);
        Task taskEventNotDone = Parser.parseForReadingFile(readEventNotDone);
        Task taskEventDone = Parser.parseForReadingFile(readEventDone);

        Assertions.assertEquals("[T][X] Sample task", taskToDoNotDone.toString());
        Assertions.assertEquals("[T][O] Sample task", taskToDoDone.toString());
        Assertions.assertEquals("[D][X] Sample task (by: Jan 01 2020 00:00)", taskDeadlineNotDone.toString());
        Assertions.assertEquals("[D][O] Sample task (by: Jan 01 2020 00:00)", taskDeadlineDone.toString());
        Assertions.assertEquals("[E][X] Sample task (at: Jan 01 2020 00:00)", taskEventNotDone.toString());
        Assertions.assertEquals("[E][O] Sample task (at: Jan 01 2020 00:00)", taskEventDone.toString());
    }

    @Test
    public void parseUserInputTest() {
        String userInputDelete = "delete 1";
        Assertions.assertDoesNotThrow(() -> {
            Parser.parseUserInput(userInputDelete);
        });

        String userInputTaskAfter = "taskafter 2020-01-01";
        Assertions.assertDoesNotThrow(() -> {
            Parser.parseUserInput(userInputTaskAfter);
        });

        String userInputTaskBefore = "taskbefore 2020-01-01";
        Assertions.assertDoesNotThrow(() -> {
            Parser.parseUserInput(userInputTaskBefore);
        });

        String userInputToDo = "todo Sample task";
        Assertions.assertDoesNotThrow(() -> {
            Parser.parseUserInput(userInputToDo);
        });

        String userInputDeadline = "deadline Sample task /by 2020-05-05 1000";
        Assertions.assertDoesNotThrow(() -> {
            Parser.parseUserInput(userInputDeadline);
        });

        String userInputEvent = "event Sample task /at 2020-05-05 1000";
        Assertions.assertDoesNotThrow(() -> {
            Parser.parseUserInput(userInputEvent);
        });
    }
}
