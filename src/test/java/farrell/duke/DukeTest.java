package farrell.duke;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.farrell.duke.Main;

public class DukeTest {
    private TestManager testManager = new TestManager();
    @Before
    public void setUp() {
        testManager.init();
    }

    @After
    public void cleanUp() {
        testManager.cleanUp();
    }

    @Test
    public void duke_createToDo_toDoCreated() {
        try {
            String output = Files.readString(Paths.get("ExpectedOutputs", "CreateToDo.txt"));
            String input = Files.readString(Paths.get("TestInputs", "CreateToDo.txt"));
            testManager.giveInput(input);
            Main.main(new String[0]);
            assertEquals(output, testManager.getOutput());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
