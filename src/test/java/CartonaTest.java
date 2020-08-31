import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.function.Executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
    public void storageInputTest() throws CartonaException {
        String testFileString = "./InputSampleList.txt";
        Path path = Path.of(testFileString);
        Runnable inputTest = () -> {
            TaskList inputList = new TaskList();
            Parser parser = new Parser();
            Ui ui = new Ui();   // not used
            Storage storage = new Storage(testFileString);
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

        }
        String expectedString = "T | 0 | test1\n" + "D | 0 | test2  | Aug 30 2020 1800\n" +
                                    "E | 0 | test3  | Aug 30 2020 2000 - 2200\n";

        assertEquals(savedtxt, expectedString);
    }

    @Test
    public void storageOutputTest() {
        TaskList expectedList = new TaskList();
        expectedList.addTask(new Deadline("task1", true,
                                new TaskDate(LocalDateTime.of(2020, 8, 20, 8, 0))));
        expectedList.addTask((new Event("task2", false,
                                new TaskDate(LocalDateTime.of(2020, 8, 21, 12, 30)),
                                new TaskDate(LocalDateTime.of(2020, 8, 21, 18, 0)))));
        expectedList.addTask(new Todo("task 3", true));
        expectedList.addTask(new Deadline("task 4", false,
                                new TaskDate(LocalDateTime.of(2020, 8, 31, 19, 0))));
        TaskList actualList = new Storage("./ReadSampleList.txt").getListFromStorage();

        for (int i = 1; i <= 4; i++) {
            assertEquals(expectedList.getTask(i).toString(), actualList.getTask(i).toString());
        }
    }
}