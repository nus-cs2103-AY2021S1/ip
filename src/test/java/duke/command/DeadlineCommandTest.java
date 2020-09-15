package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class DeadlineCommandTest {
    @Test
    public void execute_normalInput_executedCorrectly() {
        try {
            String path = "../data/duke.txt";
            Storage storage = new Storage(path);
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<Task> tasks = new ArrayList<>();
            TaskList taskList = new TaskList(tasks);
            LocalDateTime localDateTime = LocalDateTime.parse("2007-12-03T10:15:30");
            DeadlineCommand deadlineCommand = new DeadlineCommand("test", localDateTime);
            deadlineCommand.execute(storage, taskList);
            String input = bufferedReader.readLine();
            assertEquals(input, "D | 0 | test | 2007-12-03T10:15:30");
        } catch (DukeException e) {
            System.out.println("Error creating test case.");
        } catch (IOException e) {
            System.out.println("Error creating test case.");
        }
    }
}
