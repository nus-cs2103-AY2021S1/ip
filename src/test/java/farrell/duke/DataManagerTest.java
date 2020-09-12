package farrell.duke;

import main.java.farrell.duke.Duke;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class DataManagerTest {
    @Test
    public void duke_loadFromFile_tasksLoaded() {
        try {
            String expectedOutput = Files.readString(Paths.get("ExpectedOutputs", "Load.txt"));

            Duke duke = new Duke("TestInputs/Load.txt");
            String output = duke.run("list");

            assertEquals(expectedOutput, output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void duke_saveToFile_tasksSaved() {
        try {
            String expectedOutput = Files.readString(Paths.get("ExpectedOutputs", "Save.txt"));
            String input = Files.readString(Paths.get("TestInputs", "Save.txt"));

            Duke duke = new Duke();
            for(String line : input.split("\n")) {
                duke.run(line);
            }

            String output = Files.readString(Paths.get("data", "data.txt"));

            assertEquals(expectedOutput, output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
