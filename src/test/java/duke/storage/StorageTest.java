package duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

class StorageTest {


    @Test
    void testWrite_shouldWriteToFile() throws DukeException, IOException {
        final String fileName = "test.txt";
        final String text = "test\n";
        Storage storage = Storage.create(fileName);
        storage.write(text);
        storage.close();
        Path file = Storage.DATA_DIR.resolve(fileName);
        String content = Files.readString(file);
        assertEquals(content, text);
    }
}
