package farrell.duke;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.farrell.duke.UiManager;

public class UiManagerTest {
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
    public void printInWindow_normalText_printCorrectly() {
        try {
            String output = Files.readString(Paths.get("ExpectedOutputs", "PrintInWindow.txt"));
            UiManager manager = new UiManager();
            manager.sendDukeMessage("test");
            assertEquals(output, testManager.getOutput());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void displayStartMessage_displayCorrectly() {
        try {
            String output = Files.readString(Paths.get("ExpectedOutput", "DisplayStartMessage"));
            UiManager manager = new UiManager();
            manager.displayStartMessage();
            assertEquals(output, testManager.getOutput());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
