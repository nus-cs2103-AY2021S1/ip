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
    
    /**
     * Loads saved data from disk into the current Duke instance if it exists.
     *
     * @return The {@link Response response} of the loading operation.
     * @throws IOException if an error occurs while reading the file.
     * @throws ClassNotFoundException if the file exists but doesn't contain valid Duke data.
     */
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
    
    /**
     * Reads a user input string, parses it, and executes it.
     *
     * @param input The input string.
     * @return The {@link Response response} of the executed operation.
     */
    public Response readAndExecute(String input) {
        return parser.parseAndExecute(input);
    }
}
