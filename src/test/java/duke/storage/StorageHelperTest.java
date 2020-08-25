package duke.storage;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class StorageHelperTest {
    @Test
    public void save_currentDirectoryNoFile() {
        assertDoesNotThrow(() -> {
            String path = "test.txt";
            File file = new File(path);
            String string = "test";
            StorageHelper.save(() -> string, path);
            String actual = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            assertEquals(string, actual);
            file.delete();
        });
    }

    @Test
    public void save_directoryExistsNoFile() {
        assertDoesNotThrow(() -> {
            String path = "testDir/test.txt";
            File file = new File(path);
            String string = "test";
            file.getParentFile().mkdir();
            StorageHelper.save(() -> string, path);
            String actual = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            assertEquals(string, actual);
            file.delete();
            file.getParentFile().delete();
        });
    }

    @Test
    public void save_overwriteExistingFile() {
        assertDoesNotThrow(() -> {
            String path = "test.txt";
            File file = new File(path);
            Files.writeString(file.toPath(), "old text", StandardCharsets.UTF_8);
            String newText = "new text";
            StorageHelper.save(() -> newText, path);
            String actual = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            assertEquals(newText, actual);
            file.delete();
        });
    }

    @Test
    public void save_noDirectory_createDirectory() {
        assertDoesNotThrow(() -> {
            String path = "testDir/test.txt";
            File file = new File(path);
            String string = "test";
            StorageHelper.save(() -> string, path);
            String actual = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            assertEquals(string, actual);
            file.delete();
            file.getParentFile().delete();
        });
    }

    @Test
    public void open_fileExists() {
        assertDoesNotThrow(() -> {
            String path = "testOpen.txt";
            String actual = StorageHelper.open((String s) -> s, path);
            assertEquals("test", actual);
        });
    }

    @Test
    public void open_missingFile_throwException() {
        assertThrows(FileMissingException.class,
                () -> StorageHelper.open((String s) -> s, "nonExistentFile.txt"));
    }
}
