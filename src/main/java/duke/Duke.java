package duke;

import java.io.IOException;
import duke.task.TaskList;
import duke.task.TaskType;

/**
 * Encapsulates the main entry point of the duke program.
 */
public class Duke {
    private TaskList list;
    private Storage storage;

    /**
     * Instantiates a Duke object.
     * @param filePath path to locate/save the save file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            list = storage.loadFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Instantiates a Duke object, with default filePath ".//data//duke.data".
     */
    public Duke() {
        this(".//data//duke.data");
    }

    public String getResponse(String input) {
        Command command;
        try {
            command = Parser.parse(input);
        } catch (IllegalArgumentException e) {
            return "There was an error parsing your command.\n" + e.getMessage();
        }

        if (command.getTaskType() == TaskType.BYE) {
            storage.saveFile(list);
            System.exit(0);
        }

        return command.execute(list);
    }
}
