import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Scanner;

import cartona.DateParser;
import cartona.Storage;

import cartona.command.Command;
import cartona.command.Parser;
import cartona.exception.CartonaException;
import cartona.exception.InvalidInputException;

import cartona.task.*;

import cartona.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CartonaTest {
    @Test
    public void parserParseCommandTest() {
        assertThrows(InvalidInputException.class, () -> new Parser().parseCommand("a"));
        assertThrows(InvalidInputException.class, () -> new Parser().parseCommand("add"));
        assertThrows(InvalidInputException.class, () -> new Parser().parseCommand("add "));
        assertThrows(InvalidInputException.class, () -> new Parser().parseCommand("add todo"));
        assertThrows(InvalidInputException.class, () -> new Parser().parseCommand("add deadline /by"));
        assertThrows(InvalidInputException.class, () -> new Parser().parseCommand("add deadline/by"));
        assertThrows(InvalidInputException.class, () -> new Parser()
                                                            .parseCommand("add deadline /by 20-20-2020 1800"));
        assertThrows(InvalidInputException.class, () -> new Parser().parseCommand("add event /at"));
        assertThrows(InvalidInputException.class, () ->
                                                new Parser().parseCommand("add event /at 20/01/2020 1200"));
        assertThrows(InvalidInputException.class, () -> new Parser().parseCommand("add event /at 01/20/2020"));
        assertThrows(InvalidInputException.class, () -> new Parser().parseCommand("delete t"));
        assertThrows(InvalidInputException.class, () -> new Parser().parseCommand("delete"));
        assertThrows(InvalidInputException.class, () -> new Parser().parseCommand("done "));
    }

    @Test
    public void storageInputTest() throws CartonaException, IOException {
        String testFileString = "./src/test/InputSampleList.txt";
        Path path = Path.of(testFileString);
        Runnable inputTest = () -> {
            TaskList inputList = new TaskList();
            Parser parser = new Parser();
            Ui ui = new Ui();   // not used
            Storage storage = new Storage(testFileString);

            assertDoesNotThrow(() -> storage.checkAndCreateFile());

            try {
                Command todoCommand = parser.parseCommand("add todo test1");
                Command deadlineCommand = parser.parseCommand("add deadline test2 /by 2020/08/30 1800");
                Command eventCommand = parser.parseCommand("add event test3 /at 2020/08/30 2000 2200");

                todoCommand.execute(inputList, ui, storage);
                deadlineCommand.execute(inputList, ui, storage);
                eventCommand.execute(inputList, ui, storage);
            } catch (CartonaException e) {
                e.printStackTrace();
            }
        };

        String savedtxt = "";
        try {
            inputTest.run();
            savedtxt = Files.readString(path);
            Scanner txtReader = null;
            txtReader = new Scanner(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String expectedString = "T | 0 | test1\n" + "D | 0 | test2 | Aug 30 2020 1800\n" +
                                    "E | 0 | test3 | Aug 30 2020 2000 - 2200\n";

        assertEquals(savedtxt, expectedString);
    }

    @Test
    public void storageOutputTest() throws IOException {
        TaskList expectedList = new TaskList();
        expectedList.addTask(new Deadline("task1", true,
                                new TaskDate(LocalDateTime.of(2020, 8, 20, 8, 0))));
        expectedList.addTask((new Event("task2", false,
                                new TaskDate(LocalDateTime.of(2020, 8, 21, 12, 30)),
                                new TaskDate(LocalDateTime.of(2020, 8, 21, 18, 0)))));
        expectedList.addTask(new Todo("task 3", true));
        expectedList.addTask(new Deadline("task 4", false,
                                new TaskDate(LocalDateTime.of(2020, 8, 31, 19, 0))));

        Storage actualStorage = new Storage("./src/test/ReadSampleList.txt");
        TaskList actualList = actualStorage.getListFromStorage();

        assertEquals(4, actualList.getSize());

        for (int i = 1; i <= 4; i++) {
            assertEquals(expectedList.getTask(i).toString(), actualList.getTask(i).toString());
        }
    }

    @Test
    public void editTaskTest() {
        String testTxtString = "./src/test/addtasktest.txt";
        Path path = Path.of(testTxtString);

        Runnable inputTest = () -> {
            TaskList taskList = new TaskList();
            Parser parser = new Parser();
            Ui ui = new Ui();   // not used
            Storage storage = new Storage(testTxtString);

            assertDoesNotThrow(() -> storage.checkAndCreateFile());

            try {
                // Test editing todos
                Command addTodo = parser.parseCommand("add todo test1");
                Command editTodo = parser.parseCommand("edit 1 /name test2@");

                addTodo.execute(taskList, ui, storage);
                editTodo.execute(taskList, ui, storage);
                assertEquals(taskList.getTask(1).toString(),
                        new Todo("test2@", false).toString());


                // Test editing Deadlines
                Command addDeadline = parser.parseCommand("add deadline test2 /by 2020/08/30 1800");

                Command editDeadlineName = parser.parseCommand("edit 2 /name test3");
                addDeadline.execute(taskList, ui, storage);
                editDeadlineName.execute(taskList, ui, storage);
                assertEquals(taskList.getTask(2).toString(),
                        new Deadline("test3", false, DateParser.parseTaskDate("2020/08/30 1800"))
                                    .toString());

                Command editDeadlineDue = parser.parseCommand("edit 2 /due 2020/09/10 0800");
                editDeadlineDue.execute(taskList, ui, storage);
                assertEquals(taskList.getTask(2).toString(),
                        new Deadline("test3", false, DateParser.parseTaskDate("2020/09/10 0800"))
                                .toString());


                // Test editing Commands
                Command addEvent = parser.parseCommand("add event test3 /at 2020/08/30 2000 2200");
                Event originalEvent = (Event) taskList.getTask(3);
                TaskDate startDate = originalEvent.getStartDate();
                TaskDate endDate = originalEvent.getEndDate();

                addEvent.execute(taskList, ui, storage);

                Command editEventName = parser.parseCommand("edit 3 /name test4");
                editEventName.execute(taskList, ui, storage);
                assertEquals(taskList.getTask(3),
                        new Event("test4", false, startDate, endDate));

                Command editEventDate = parser.parseCommand("edit 3 /date 2020/09/30");
                editEventDate.execute(taskList, ui, storage);
                TaskDate newStart = DateParser.parseTaskDate("2020/09/30 2000");
                TaskDate newEnd = DateParser.parseTaskDate("2020/09/30 2200");
                assertEquals(taskList.getTask(3).toString(),
                        new Event("test4", false, newStart, newEnd).toString());

                Command editEventStart = parser.parseCommand("edit 3 /start 1000");
                Command editEventEnd = parser.parseCommand("edit 3 /end 2100");
                TaskDate newStart2 = DateParser.parseTaskDate("2020/09/30 1000");
                TaskDate newEnd2 = DateParser.parseTaskDate("2020/09/30 2100");
                assertEquals(taskList.getTask(3).toString(),
                        new Event("test4", false, newStart2, newEnd2));

            } catch (CartonaException e) {
                e.printStackTrace();
            }
        };
    }
}