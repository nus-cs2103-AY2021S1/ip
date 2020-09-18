package Duke.commands;

import Duke.DukeExceptions;
import Duke.TaskList.TaskList;

import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;

/**
 * Helps to parse through both user inputs and the save file.
 */
public class Parser {
    private static FileReader taskReader;

    /**
     * Reads save file and fills up the list
     * with the old save file's tasks, including
     * status.
     * @param tmpFile file containing the saved tasks
     * @throws DukeExceptions
     */
    public static void readSave(File tmpFile) throws DukeExceptions {
        try {
            taskReader = new FileReader(tmpFile);
            BufferedReader bufferedTaskReader = new BufferedReader(taskReader);
            String lineWithStatus;
            String status;
            String line;
            while ((lineWithStatus = bufferedTaskReader.readLine()) != null) {
                line = lineWithStatus.substring(0, lineWithStatus.length() - 4);
                status = lineWithStatus.substring(lineWithStatus.length() - 4);
                parseAndAddToList(line);
                if (status.equals("done")) {
                    TaskList.markDone("/done " + TaskList.getThingsOnListSize());
                }
            }
        } catch ( IOException e) {
            throw new DukeExceptions(e.getMessage());
        }
    }

    /**
     * Parses through input text to check for Duke.TaskList.Duke.commands
     * then adds a type of Task to the ArrayList of tasks.
     * @param input User-input string.
     * @throws DukeExceptions
     * @return Returns an appropriate response to the input
     */
    public static String parseAndAddToList(String input) throws DukeExceptions {
        String command;
        int cmdIndex = input.indexOf("/");
        if (cmdIndex == -1) {
            throw new DukeExceptions("Bark bark bark! (Please use me with proper commands!)");
        } else {
            command = input.substring(cmdIndex + 1);
            command = !command.contains(" ") ? command : command.substring(0, command.indexOf(" "));
            switch (command) {
                case "list":
                    return TaskList.getListView();

                case "delete":
                    return TaskList.deleteFromList(input);

                case "done":
                    return TaskList.markDone(input);

                case "update":
                    return TaskList.update(input);

                case "find":
                    return TaskList.find(input.substring(cmdIndex + "/find ".length()));

                case "todo":
                    return TaskList.addToDo(input);

                case "at":
                    return TaskList.addEvent(input);

                case "help":
                    return TaskList.getHelp();

                case "by":
                    return TaskList.addDeadline(input);

                default:
                    throw new DukeExceptions("Bark bark bark! (Please use me with proper commands!)");
            }
        }
    }
}
