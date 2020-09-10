package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import duke.exception.InvalidSaveFileException;

public class DukeTest {
    private static final Path TEST_SAVE_PATH = Paths.get("temp-data", "duke.txt");
    private static final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private static final PrintStream ORIGINAL_OUT = System.out;
    private static final InputStream SYSIN_BACKUP = System.in;
    private static String testFilesDir = Paths.get("").toString();

    static {
        Path currentDir = Paths.get("").toAbsolutePath();
        int len = currentDir.getNameCount();
        int index = -1;
        for (int i = 0; i < len; i++) {
            if (currentDir.getName(i).toString().equals("ip")) {
                index = i;
                break;
            }
        }
        Path rootDir = currentDir;
        for (int i = 0; i < len - index - 1; i++) {
            rootDir = rootDir.getParent();
        }
        try {
            Path[] testDir =
                    Files
                            .walk(rootDir, 20)
                            .filter(p -> p.endsWith("duke-test"))
                            .toArray(Path[]::new);
            testFilesDir = testDir[0].toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // recursive function to delete directory that stores the save file
    private static void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }

    private static void clearPath() {
        Path topDir = TEST_SAVE_PATH.subpath(0, 1);
        if (!java.nio.file.Files.exists(topDir)) {
            return;
        }
        File toDelete = new File(String.valueOf(topDir));
        deleteDirectory(toDelete);
    }

    @BeforeAll
    static void setUpStreams() throws IOException {
        clearPath();
        System.setOut(new PrintStream(OUT_CONTENT));
        Path currentDir = Paths.get("").toAbsolutePath();
        int len = currentDir.getNameCount();
        int index = -1;
        for (int i = 0; i < len; i++) {
            if (currentDir.getName(i).toString().equals("ip")) {
                index = i;
                break;
            }
        }
        String[] pathToRootDir = new String[len - index - 1];
        Arrays.fill(pathToRootDir, "..");
        Path rootDir = Paths.get(String.join(File.separator, pathToRootDir));

        String inputFilePath = "";
        try {
            Path[] testDir =
                    Files
                            .walk(rootDir, 20)
                            .filter(p -> p.endsWith("duke_test_input.txt"))
                            .toArray(Path[]::new);
            inputFilePath = testDir[0].toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
        String[] input = reader.lines().toArray(String[]::new);
        reader.close();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(String.join("\n", input).getBytes());
        System.setIn(inputStream);
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(ORIGINAL_OUT);
        clearPath();
        System.setIn(SYSIN_BACKUP);
    }

    @Test
    public void testInputs() throws IOException, InvalidSaveFileException {
        Duke duke = new Duke(TEST_SAVE_PATH);
        duke.runCli();

        Path currentDir = Paths.get("").toAbsolutePath();
        int len = currentDir.getNameCount();
        int index = -1;
        for (int i = 0; i < len; i++) {
            if (currentDir.getName(i).toString().equals("ip")) {
                index = i;
                break;
            }
        }
        String[] pathToRootDir = new String[len - index - 1];
        Arrays.fill(pathToRootDir, "..");
        Path rootDir = Paths.get(String.join(File.separator, pathToRootDir));
        String pathToExpected =
                Files
                        .walk(rootDir, 20)
                        .filter(p -> p.endsWith("DUKE_TEST_EXPECTED.txt"))
                        .toArray(Path[]::new)[0]
                        .toString();

        File file = new File(pathToExpected);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = fis.readAllBytes();
        fis.close();
        String[] expectedOutputLines = new String(data, StandardCharsets.UTF_8).split("[\r\n]{1,2}");

        String actualOutput = OUT_CONTENT.toString();
        String[] actualOutputLines = actualOutput.split("[\r\n]{1,2}");

        String pathToActual =
                Files
                        .walk(rootDir, 20)
                        .filter(p -> p.endsWith("DUKE_TEST_ACTUAL.txt"))
                        .toArray(Path[]::new)[0]
                        .toString();
        FileWriter myWriter = new FileWriter(pathToActual);
        myWriter.write(actualOutput);
        myWriter.close();
        assertEquals(expectedOutputLines.length, actualOutputLines.length);
        Stream
                .iterate(0, i -> i < expectedOutputLines.length, i -> i + 1)
                .forEach(i -> {
                    assertEquals(expectedOutputLines[i], actualOutputLines[i]);
                });
    }
}
