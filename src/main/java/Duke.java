import duke.*;

import java.io.IOException;

public class Duke {
    protected Storage storage;
    private TaskList tasks;
    protected Ui ui;

    public Duke(String path, String fileName) {
        this.storage = new Storage(path,fileName);
        this.tasks = storage.load();
        this.ui = new Ui(this.tasks);
    }


    protected String getResponse(String input) throws EmptyInputException, NoResponseException, IOException {
        Parser parser = new Parser(this.ui,this.tasks);
        String response = parser.parse(input);
        if (input.equals("bye")) {
            storage.storeFile(this.tasks);
        }
        return response;
    }



}