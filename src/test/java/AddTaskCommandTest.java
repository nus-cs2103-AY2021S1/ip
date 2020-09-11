import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class AddTaskCommandTest {
    final Ui ui = new Ui();
    final Path dataPath = Paths.get("data", "duke.txt");
    final Path aliasMapFilePath = Paths.get("data", "keymap.txt");
    final Storage storage = new StorageStub(dataPath, aliasMapFilePath);
    final TaskList taskList = new TaskList(new ArrayList<>());

    @Test
    void execute() {
        AddTaskCommand command = new AddTaskCommand(TaskType.D, "lorem", LocalDate.MIN, LocalTime.NOON);
        try {
            String output = command.execute(taskList, ui, storage);
            Task testTask = new Deadline("lorem", LocalDate.MIN, LocalTime.NOON);
            assertEquals(taskList.getList().get(0), testTask);
            assertEquals(storage.loadTaskList(), new ArrayList<>(List.of(testTask)));
            assertEquals(ui.showAdd((short) 1, testTask), output);
        } catch (BlankTaskException | IOException | InvalidCommandException e) {
            e.printStackTrace();
        }
    }
}