import duke.exception.DeadlineException;
import duke.exception.DeleteException;
import duke.exception.DoneException;
import duke.exception.EventException;
import duke.exception.InvalidDateFormatException;
import duke.exception.InvalidDateInputException;
import duke.exception.InvalidTaskNumberException;
import duke.exception.NotACommandException;
import duke.exception.ToDoException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;
import duke.utility.Parser;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParserTest {
    @Test
    public void parseToDoTaskForSavingTest() {
        String taskName = "Sample task";
        Task task = new ToDoTask(taskName);
        String parsedTaskNotDone = Parser.parseForSave(task);
        String expectedNotDone = "T | 0 | Sample task\n";
        Assertions.assertEquals(expectedNotDone, parsedTaskNotDone);

        task.setStatusToDone();

        String parsedTaskDone = Parser.parseForSave(task);
        String expectedDone = "T | 1 | Sample task\n";
        Assertions.assertEquals(expectedDone, parsedTaskDone);
    }

    @Test
    public void parseDeadlineTaskForSavingTest() {
        String taskName = "Sample task";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime date = LocalDateTime.parse("2020-01-01 0000", formatter);
        Task task = new DeadlineTask(taskName, date);
        String parsedTaskNotDone = Parser.parseForSave(task);
        String expectedNotDone = "D | 0 | Sample task | 2020-01-01 0000\n";
        Assertions.assertEquals(expectedNotDone, parsedTaskNotDone);

        task.setStatusToDone();

        String parsedTaskDone = Parser.parseForSave(task);
        String expectedDone = "D | 1 | Sample task | 2020-01-01 0000\n";
        Assertions.assertEquals(expectedDone, parsedTaskDone);
    }

    @Test
    public void parseEventTaskForSavingTest() {
        String taskName = "Sample task";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime date = LocalDateTime.parse("2020-01-01 0000", formatter);
        Task task = new EventTask(taskName, date);
        String parsedTaskNotDone = Parser.parseForSave(task);
        String expectedNotDone = "E | 0 | Sample task | 2020-01-01 0000\n";
        Assertions.assertEquals(expectedNotDone, parsedTaskNotDone);

        task.setStatusToDone();

        String parsedTaskDone = Parser.parseForSave(task);
        String expectedDone = "E | 1 | Sample task | 2020-01-01 0000\n";
        Assertions.assertEquals(expectedDone, parsedTaskDone);
    }

    @Test
    public void parseToDoTaskForReadingFileTest() {
        String readDataNotDone = "T | 0 | Sample task";
        Task taskNotDone = Parser.parseForReadingFile(readDataNotDone);

        Assertions.assertEquals("[T][✗] Sample task", taskNotDone.toString());

        String readDataDone = "T | 1 | Sample task";
        Task taskDone = Parser.parseForReadingFile(readDataDone);

        Assertions.assertEquals("[T][✓] Sample task", taskDone.toString());
    }

    @Test
    public void parseDeadlineTaskForReadingFileTest() {
        String readDataNotDone = "D | 0 | Sample task | 2020-01-01 0000";
        Task taskNotDone = Parser.parseForReadingFile(readDataNotDone);

        Assertions.assertEquals("[D][✗] Sample task (by: Jan 01 2020 00:00)", taskNotDone.toString());

        String readDataDone = "D | 1 | Sample task | 2020-01-01 0000";
        Task taskDone = Parser.parseForReadingFile(readDataDone);

        Assertions.assertEquals("[D][✓] Sample task (by: Jan 01 2020 00:00)", taskDone.toString());
    }

    @Test
    public void parseEventTaskForReadingFileTest() {
        String readDataNotDone = "E | 0 | Sample task | 2020-01-01 0000";
        Task taskNotDone = Parser.parseForReadingFile(readDataNotDone);

        Assertions.assertEquals("[E][✗] Sample task (at: Jan 01 2020 00:00)", taskNotDone.toString());

        String readDataDone = "E | 1 | Sample task | 2020-01-01 0000";
        Task taskDone = Parser.parseForReadingFile(readDataDone);

        Assertions.assertEquals("[E][✓] Sample task (at: Jan 01 2020 00:00)", taskDone.toString());
    }

    @Test
    public void parseUserInputByeTest() {
        String userInput = "bye";
        Assertions.assertDoesNotThrow(() -> {
            Parser.parseUserInput(userInput);
        });
    }

    @Test
    public void parseUserInputListTest() {
        String userInput = "list";
        Assertions.assertDoesNotThrow(() -> {
            Parser.parseUserInput(userInput);
        });
    }

    @Test
    public void parseUserInputDoneTest() {
        String userInputNoArg = "done";
        Assertions.assertThrows(DoneException.class, () -> {
            Parser.parseUserInput(userInputNoArg);
        });

        String userInputWrongArg = "done a";
        Assertions.assertThrows(InvalidTaskNumberException.class, () -> {
            Parser.parseUserInput(userInputWrongArg);
        });

        String userInputCorrectArg = "done 1";
        Assertions.assertDoesNotThrow(() -> {
            Parser.parseUserInput(userInputCorrectArg);
        });
    }

    @Test
    public void parseUserInputDeleteTest() {
        String userInputNoArg = "delete";
        Assertions.assertThrows(DeleteException.class, () -> {
            Parser.parseUserInput(userInputNoArg);
        });

        String userInputWrongArg = "delete a";
        Assertions.assertThrows(InvalidTaskNumberException.class, () -> {
            Parser.parseUserInput(userInputWrongArg);
        });

        String userInputCorrectArg = "delete 1";
        Assertions.assertDoesNotThrow(() -> {
            Parser.parseUserInput(userInputCorrectArg);
        });
    }

    @Test
    public void parseUserInputTaskAfterTest() {
        String userInputNoArg = "taskafter";
        Assertions.assertThrows(InvalidDateFormatException.class, () -> {
            Parser.parseUserInput(userInputNoArg);
        });

        String userInputWrongArg = "taskafter 10 june 2020";
        Assertions.assertThrows(InvalidDateFormatException.class, () -> {
            Parser.parseUserInput(userInputWrongArg);
        });

        String userInputCorrectArg = "taskafter 2020-01-01";
        Assertions.assertDoesNotThrow(() -> {
            Parser.parseUserInput(userInputCorrectArg);
        });
    }

    @Test
    public void parseUserInputTaskBeforeTest() {
        String userInputNoArg = "taskbefore";
        Assertions.assertThrows(InvalidDateFormatException.class, () -> {
            Parser.parseUserInput(userInputNoArg);
        });

        String userInputWrongArg = "taskbefore 10 june 2020";
        Assertions.assertThrows(InvalidDateFormatException.class, () -> {
            Parser.parseUserInput(userInputWrongArg);
        });

        String userInputCorrectArg = "taskbefore 2020-01-01";
        Assertions.assertDoesNotThrow(() -> {
            Parser.parseUserInput(userInputCorrectArg);
        });
    }

    @Test
    public void parseUserInputToDoTest() {
        String userInputCorrect = "todo Sample task";
        Assertions.assertDoesNotThrow(() -> {
            Parser.parseUserInput(userInputCorrect);
        });

        String userInputIncorrect = "todo";
        Assertions.assertThrows(ToDoException.class, () -> {
            Parser.parseUserInput(userInputIncorrect);
        });
    }

    @Test
    public void parseUserInputDeadlineTest() {
        String userInputCorrect = "deadline Sample task /by 2020-05-05 1000";
        Assertions.assertDoesNotThrow(() -> {
            Parser.parseUserInput(userInputCorrect);
        });

        String userInputNoArg = "deadline";
        Assertions.assertThrows(DeadlineException.class, () -> {
            Parser.parseUserInput(userInputNoArg);
        });

        String userInputNoDate = "deadline Sample task";
        Assertions.assertThrows(DeadlineException.class, () -> {
            Parser.parseUserInput(userInputNoDate);
        });

        String userInputNoBy = "deadline Sample task /date description";
        Assertions.assertThrows(DeadlineException.class, () -> {
            Parser.parseUserInput(userInputNoBy);
        });

        String userInputWrongDate = "deadline Sample task /by 01 January 2020";
        Assertions.assertThrows(InvalidDateInputException.class, () -> {
            Parser.parseUserInput(userInputWrongDate);
        });
    }

    @Test
    public void parseUserInputEventTest() {
        String userInputCorrect = "event Sample task /at 2020-05-05 1000";
        Assertions.assertDoesNotThrow(() -> {
            Parser.parseUserInput(userInputCorrect);
        });

        String userInputNoArg = "event";
        Assertions.assertThrows(EventException.class, () -> {
            Parser.parseUserInput(userInputNoArg);
        });

        String userInputNoDate = "event Sample task";
        Assertions.assertThrows(EventException.class, () -> {
            Parser.parseUserInput(userInputNoDate);
        });

        String userInputNoAt = "event Sample task /date description";
        Assertions.assertThrows(EventException.class, () -> {
            Parser.parseUserInput(userInputNoAt);
        });

        String userInputWrongDate = "event Sample task /at 01 January 2020";
        Assertions.assertThrows(InvalidDateInputException.class, () -> {
            Parser.parseUserInput(userInputWrongDate);
        });
    }

    @Test
    public void parseNotACommandTest() {
        String NotACommand1 = "abc";
        Assertions.assertThrows(NotACommandException.class, () -> {
            Parser.parseUserInput(NotACommand1);
        });

        String NotACommand2 = "cba";
        Assertions.assertThrows(NotACommandException.class, () -> {
            Parser.parseUserInput(NotACommand2);
        });

        String NotACommand3 = "dead line";
        Assertions.assertThrows(NotACommandException.class, () -> {
            Parser.parseUserInput(NotACommand3);
        });
    }
}
