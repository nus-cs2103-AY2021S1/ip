package duke.command;

import java.io.File;

public class DukeCommandTest {
    final String dataDir = System.getProperty("user.dir") + File.separator + "dataTest";

    /*
      Test need to be updated
    @Test
    public void testListCommand_testIsExit() throws DukeException {
        final String dataFile = "dukeList.txt";
        String fullCommand = "list";
        Command command = Parser.parse(fullCommand);
        Ui ui = new Ui();
        Storage storage = new Storage(dataDir + File.separator + dataFile);
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("return book"));
        command.execute(taskList, ui, storage);

   }
     */
}
