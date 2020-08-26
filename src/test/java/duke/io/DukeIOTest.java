package duke.io;

import duke.DukeException;
import duke.Ui;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class DukeIOTest {
  final String dataDir = System.getProperty("user.dir") + File.separator + "dataTest";

  @Test
  public void loadTest() throws DukeException, IOException {
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

  @Test
  public void writeTest() throws DukeException, IOException {
    String dataFileWrite = "dukeWrite.txt";
    String filePath = dataDir + File.separator + dataFileWrite;
    Ui ui = new Ui();
    Storage storage = new Storage(filePath);
    TaskList taskList = new TaskList(storage.load());
    // Create Test Result to compare
    Todo todoResult = new Todo("CS2103T homework");
    LocalDateTime testStartTime = LocalDateTime.of(2020, 8, 27, 2, 22);
    LocalDateTime testEndTime = LocalDateTime.of(2020, 8, 27, 18, 00);
    Event eventResult = new Event("meeting", testStartTime, testEndTime);
    eventResult.markAsDone();
    LocalDateTime testTime = LocalDateTime.of(2020, 8, 29, 16, 00);
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
}
