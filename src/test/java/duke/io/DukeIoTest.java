package duke.io;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.common.DukeException;
import duke.common.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class DukeIoTest {
    final String dataDir = System.getProperty("user.dir") + File.separator + "dataTest";

    @Test
    public void loadTest() throws DukeException {
        String dataFileWrite = "dukeLoad.txt";
        String filePath = dataDir + File.separator + dataFileWrite;
        Storage storage = new Storage(filePath);
        TaskList taskList = new TaskList(storage.load());
        // Create Test Result to compare
        Todo todoResult = new Todo("CS2103T homework");
        LocalDateTime testStartTime = LocalDateTime.of(2020, 8, 27, 2, 22);
        LocalDateTime testEndTime = LocalDateTime.of(2020, 8, 27, 18, 0);
        Event eventResult = new Event("meeting", testStartTime, testEndTime);
        eventResult.markAsDone();
        LocalDateTime testTime = LocalDateTime.of(2020, 8, 29, 16, 0);
        Deadline deadline = new Deadline("return book", testTime);
        assert taskList.retrieveTask(0).equals(eventResult);
        assert taskList.retrieveTask(1).equals(todoResult);
        assert taskList.retrieveTask(2).equals(deadline);
    }

    /*
    Test to be updated.
    @Test
    public void writeTest() throws DukeException {
        String dataFileWrite = "dukeWrite.txt";
        String filePath = dataDir + File.separator + dataFileWrite;
        Ui ui = new Ui();
        Storage storage = new Storage(filePath);
        TaskList taskList = new TaskList(storage.load());
        // Create test result to compare
        Todo todoResult = new Todo("CS2103T homework");
        LocalDateTime testStartTime = LocalDateTime.of(2020, 8, 27, 2, 22);
        LocalDateTime testEndTime = LocalDateTime.of(2020, 8, 27, 18, 0);
        Event eventResult = new Event("meeting", testStartTime, testEndTime);
        eventResult.markAsDone();
        LocalDateTime testTime = LocalDateTime.of(2020, 8, 29, 16, 0);
        Deadline deadline = new Deadline("return book", testTime);

        Parser.parse("event meeting /at 27-8-2020 222-1800").execute(taskList, ui, storage);
        Parser.parse("done 1").execute(taskList, ui, storage);
        Parser.parse("todo CS2103T homework").execute(taskList, ui, storage);
        Parser.parse("deadline return book /by 29-8-2020 1600").execute(taskList, ui, storage);

        storage.write(taskList);
        TaskList outcomes = new TaskList(storage.load());
        assert outcomes.retrieveTask(0).equals(eventResult);
        assert outcomes.retrieveTask(1).equals(todoResult);
        assert outcomes.retrieveTask(2).equals(deadline);

        // Reset the file
        TaskList reset = new TaskList();
        storage.write(reset);
    }
     */

    @Test
    public void findTest() throws DukeException {
        String dataFileWrite = "dukeFind.txt";
        String filePath = dataDir + File.separator + dataFileWrite;
        Ui ui = new Ui();
        Storage storage = new Storage(filePath);
        TaskList taskList = new TaskList(storage.load());
        // Create test result to compare
        LocalDateTime testTime = LocalDateTime.of(2020, 8, 29, 16, 0);
        Deadline deadline = new Deadline("return book", testTime);
        Todo todoResult = new Todo("CS2101 borrow book");

        ArrayList<Task> outcomes = taskList.find("book");
        for (Task outcome : outcomes) {
            assert outcome.equals(deadline) || outcome.equals(todoResult);
        }
    }
}
