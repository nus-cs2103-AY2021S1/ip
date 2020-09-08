package duke.storage;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {


    @Test
    void testWrite_shouldWriteToFile() throws DukeException, IOException {
        final String FILE_NAME = "test.txt";
        final String TEXT = "test\n";
        Storage storage = Storage.create(FILE_NAME);
        storage.write(TEXT);
        storage.close();
        Path file = Storage.dataDir.resolve(FILE_NAME);
        String content = Files.readString(file);
        assertEquals(content, TEXT);
    }
}