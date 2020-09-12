package farrell.duke;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import main.java.farrell.duke.Duke;
import main.java.farrell.duke.DukeException;

public class DataManagerTest {
    @Test
    public void duke_loadFromFile_tasksLoaded() {
        try {
            String expectedOutput = Files.readString(Paths.get("ExpectedOutputs", "Load.txt"));

            Duke duke = new Duke("TestInputs/Load.txt");
            String output;
            try {
                output = duke.run("list");
            } catch (DukeException e) {
                output = e.getMessage();
            }

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
            for (String line : input.split("\n")) {
                try {
                    duke.run(line);
                } catch (DukeException e) {
                    System.out.println("Invalid Command Provided");
                    fail();
                }
            }

            String output = Files.readString(Paths.get("data", "data.txt"));

            assertEquals(expectedOutput, output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
