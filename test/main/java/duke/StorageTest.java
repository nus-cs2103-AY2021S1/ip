package main.java.duke;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void saveFile_fileNotFound_fail() {
        try {
            Storage storage = new Storage("test/main/java/duke/badFormatFile.txt");
            storage.saveFile("Hello World");
        } catch (DukeException err) {
            assertEquals(err.getMessage(), "File Path is a directory -OR- Can't create file at location");
        }
    }


    @Test
    void loadFile_success() {
        try {
            Storage storage = new Storage("data/duke.txt");
            storage.loadFile();
        } catch (DukeException err) {
            System.out.println(err.getMessage());
            fail();
        }
    }

    @Test
    void loadFile_corruptedFile_fail() {
        try {
            Storage storage = new Storage("test/main/java/duke/badFormatFile.txt");
            storage.loadFile();
        } catch (DukeException err) {
            assertEquals(err.getMessage(), "Error: Saved File is badly corrupted");
        }
    }
}