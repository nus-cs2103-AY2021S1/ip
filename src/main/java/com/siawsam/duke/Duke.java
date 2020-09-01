package com.siawsam.duke;

import java.io.IOException;

/**
 * A Duke program.
 */
public class Duke {
    /**
     * The file path for saving tasks.
     */
    static final String FILE_PATH = "savedTasks.txt";
    private Parser parser;
    
    public void loadFromDisk() throws IOException, ClassNotFoundException {
        Storage storage = new Storage(FILE_PATH);
        
        if (storage.doesExist()) {
            TaskList savedList = storage.load();
            parser = new Parser(storage, savedList);
            Ui.showSuccessfulLoad();
        } else {
            Ui.showNoExistingSave();
            parser = new Parser(storage);
        }
    }
    
    public void readUserInput(String input) {
        parser.parse(input);
    }
}
