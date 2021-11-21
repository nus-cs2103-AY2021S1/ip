import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class AddAliasCommandTest {
    @TempDir
    Path dataDir = Paths.get("data");

    @Test
    void execute() {
        Ui ui = new Ui();
        Path dataPath = Paths.get("data", "duke.txt");
        Path aliasMapFilePath = Paths.get("data", "keymap.txt");
        Storage storage = new Storage(dataPath, aliasMapFilePath);

        HashMap<String, String> aliasMap = new HashMap<>(Map.of(
                "t", "todo",
                "d", "deadline",
                "e", "event"));
        aliasMap.put("somekey", "list");
        AddAliasCommand command = new AddAliasCommand(aliasMap, "somekey");

        try {
            String message = command.execute(new TaskList(new ArrayList<>()), ui, storage);
            assertEquals(message, ui.showMapping("list", "somekey"));
            assertEquals(storage.loadAliasMapping(), aliasMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}