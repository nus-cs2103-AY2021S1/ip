package farrell.duke;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import main.java.farrell.duke.Duke;
import main.java.farrell.duke.DukeException;

public class DukeTest {
    @Test
    public void duke_createToDo_toDoCreated() {
        try {
            String expectedOutput = Files.readString(Paths.get("ExpectedOutputs", "CreateToDo.txt"));
            String input = Files.readString(Paths.get("TestInputs", "CreateToDo.txt"));

            String output = runProgram(input);
            assertEquals(expectedOutput, output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void duke_createEvent_eventCreated() {
        try {
            String expectedOutput = Files.readString(Paths.get("ExpectedOutputs", "CreateEvent.txt"));
            String input = Files.readString(Paths.get("TestInputs", "CreateEvent.txt"));

            String output = runProgram(input);
            assertEquals(expectedOutput, output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void duke_createDeadline_deadlineCreated() {
        try {
            String expectedOutput = Files.readString(Paths.get("ExpectedOutputs", "CreateDeadline.txt"));
            String input = Files.readString(Paths.get("TestInputs", "CreateDeadline.txt"));

            String output = runProgram(input);
            assertEquals(expectedOutput, output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void duke_done_taskDone() {
        try {
            String expectedOutput = Files.readString(Paths.get("ExpectedOutputs", "Done.txt"));
            String input = Files.readString(Paths.get("TestInputs", "Done.txt"));

            String output = runProgram(input);
            assertEquals(expectedOutput, output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void duke_delete_taskDeleted() {
        try {
            String expectedOutput = Files.readString(Paths.get("ExpectedOutputs", "Delete.txt"));
            String input = Files.readString(Paths.get("TestInputs", "Delete.txt"));

            String output = runProgram(input);
            assertEquals(expectedOutput, output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void duke_find_tasksFound() {
        try {
            String expectedOutput = Files.readString(Paths.get("ExpectedOutputs", "Find.txt"));
            String input = Files.readString(Paths.get("TestInputs", "Find.txt"));

            String output = runProgram(input);
            assertEquals(expectedOutput, output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void duke_sortByDescription_tasksSortedByDescription() {
        try {
            String expectedOutput = Files.readString(Paths.get("ExpectedOutputs", "SortByDescription.txt"));
            String input = Files.readString(Paths.get("TestInputs", "SortByDescription.txt"));

            String output = runProgram(input);
            assertEquals(expectedOutput, output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void duke_sortByDate_tasksSortedByDate() {
        try {
            String expectedOutput = Files.readString(Paths.get("ExpectedOutputs", "SortByDate.txt"));
            String input = Files.readString(Paths.get("TestInputs", "SortByDate.txt"));

            String output = runProgram(input);
            assertEquals(expectedOutput, output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private String runProgram(String input) {
        Duke duke = new Duke();

        StringBuilder sb = new StringBuilder();
        for (String line : input.split("\n")) {
            try {
                sb.append(duke.run(line));
            } catch (DukeException e) {
                sb.append(e.getMessage());
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
