package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

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
        } catch (DateTimeParseException e) {
            return "There was an error parsing your date.\nInput your date in the format 2020-12-25.";
        }

        if (command.getTaskType() == TaskType.BYE) {
            storage.saveFile(list);
            System.exit(0);
        }

        return command.execute(list);
    }
}
