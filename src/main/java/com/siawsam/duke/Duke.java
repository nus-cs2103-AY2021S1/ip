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
    
    public Response loadFromDisk() throws IOException, ClassNotFoundException {
        Storage storage = new Storage(FILE_PATH);
        
        if (storage.doesExist()) {
            TaskList savedList = storage.load();
            parser = new Parser(storage, savedList);
            return new Response(Ui.showSuccessfulLoad());
        } else {
            parser = new Parser(storage);
            return new Response(Ui.showNoExistingSave());
        }
    }
    
    public Response readAndExecute(String input) {
        return parser.parseAndExecute(input);
    }
}
