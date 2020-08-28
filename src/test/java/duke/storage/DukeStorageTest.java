package duke.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DukeStorageTest {
    private static final Path TEST_FILE_PATH = Paths.get("test", "data", "duke.txt");

    // recursive function to delete directory that stores the save file
    private void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }

    private void writeLines(String[] lines) throws IOException {
        FileWriter myWriter = new FileWriter(TEST_FILE_PATH.toString());
        for (String line: lines) {
            myWriter.write(line + "\n");
        }
        myWriter.close();
    }

    private void createSaveFile() throws IOException {
        int len = TEST_FILE_PATH.getNameCount();
        Path directoriesToCreate = TEST_FILE_PATH.subpath(0, len - 1);
        Files.createDirectories(directoriesToCreate);
        if (!java.nio.file.Files.exists(TEST_FILE_PATH)) {
            new File(TEST_FILE_PATH.toString()).createNewFile();
        }
    }

    private void clearPath() {
        Path topDir = TEST_FILE_PATH.subpath(0, 1);
        if (!java.nio.file.Files.exists(topDir)) {
            return;
        }
        File toDelete = new File(String.valueOf(topDir));
        deleteDirectory(toDelete);
    }

    @BeforeEach
    @AfterEach
    public void removeSaveFile() {
        clearPath();
    }

    @Test
    public void getSavedLines_saveFileNotExist_success() throws IOException {
        Storage store = new DukeStorage(TEST_FILE_PATH);

        List<String> savedLines = store.getSavedLines();
        int len = savedLines.size();
        assertEquals(0, len);
    }

    @Test
    public void getSavedLines_saveFileExistNotEmpty_success() throws IOException {
        createSaveFile();
        String[] initialLines = new String[3];
        initialLines[0] = "This is test line number 1.";
        initialLines[1] = "This is test line number 2!";
        initialLines[2] = "This is test line number 3?";
        writeLines(initialLines);
        Storage store = new DukeStorage(TEST_FILE_PATH);

        List<String> savedLines = store.getSavedLines();
        int len = savedLines.size();
        assertEquals(initialLines.length, len);

        for (int i = 0; i < len; i++) {
            assertEquals(initialLines[i], savedLines.get(i));
        }
    }

    @Test
    public void getSavedLines_saveFileExistEmpty_success() throws IOException {
        createSaveFile();

        Storage store = new DukeStorage(TEST_FILE_PATH);

        List<String> savedLines = store.getSavedLines();
        int len = savedLines.size();
        assertEquals(0, len);
    }

    @Test
    public void addLine_newLinesSaved() throws IOException {
        createSaveFile();

        Storage store = new DukeStorage(TEST_FILE_PATH);

        String[] addedLines = new String[3];
        addedLines[0] = "This is test line number 1.";
        addedLines[1] = "This is test line number 2!";
        addedLines[2] = "This is test line number 3?";
        Arrays
                .stream(addedLines)
                .forEach(store::addLine);

        BufferedReader in = new BufferedReader(new FileReader(TEST_FILE_PATH.toString()));
        String[] actual = in.lines().toArray(String[]::new);
        in.close();

        int len = actual.length;
        assertEquals(addedLines.length, len);

        for (int i = 0; i < len; i++) {
            assertEquals(addedLines[i], actual[i]);
        }
    }

    @Test
    public void updateLine_updatesSaveFile() throws IOException {
        createSaveFile();
        String[] lines = new String[3];
        lines[0] = "This is test line number 1.";
        lines[1] = "This is test line number 2!";
        lines[2] = "This is test line number 3?";
        writeLines(lines);
        Storage store = new DukeStorage(TEST_FILE_PATH);

        lines[1] = "Line number 2 has been updated!";
        store.updateLine(1, lines[1]);

        BufferedReader in = new BufferedReader(new FileReader(TEST_FILE_PATH.toString()));
        String[] actual = in.lines().toArray(String[]::new);
        in.close();

        int len = actual.length;
        assertEquals(lines.length, len);

        for (int i = 0; i < len; i++) {
            assertEquals(lines[i], actual[i]);
        }
    }

    @Test
    public void removeLine_removesLinesFromSaveFile() throws IOException {
        createSaveFile();
        String[] lines = new String[3];
        lines[0] = "This is test line number 1.";
        lines[1] = "This is test line number 2!";
        lines[2] = "This is test line number 3?";
        writeLines(lines);
        Storage store = new DukeStorage(TEST_FILE_PATH);

        store.removeLine(0);
        store.removeLine(0);

        BufferedReader in = new BufferedReader(new FileReader(TEST_FILE_PATH.toString()));
        String[] actual = in.lines().toArray(String[]::new);
        in.close();

        String[] expected = Arrays.copyOfRange(lines, 2, 3);

        int len = actual.length;
        assertEquals(expected.length, len);

        for (int i = 0; i < len; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }
}
