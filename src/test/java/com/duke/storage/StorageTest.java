package com.duke.storage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StorageTest {

    @Test
    public void printFileContentsTest() throws Exception {
        Storage storage = new Storage("data/test.txt");
        String output = "";
        storage.printFileContents();
    }
}
