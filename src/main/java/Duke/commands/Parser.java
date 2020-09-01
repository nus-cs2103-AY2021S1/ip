package Duke.commands;

import Duke.DukeExceptions;
import Duke.TaskList.TaskList;
import Duke.TaskList.tasks.Deadlines;
import Duke.TaskList.tasks.Events;
import Duke.TaskList.tasks.ToDos;
import Duke.UI.UI;

import java.io.FileReader;
import java.io.File;
import java.io.PrintStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.time.format.DateTimeParseException;

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
            PrintStream oldOutput = System.out;
            PrintStream dummyOut = new PrintStream(new OutputStream() {
                @Override
                public void write(int i) throws IOException {

                }
            });
            System.setOut(dummyOut);
            BufferedReader bufferedTaskReader = new BufferedReader(taskReader);
            String lineWithStatus;
            String status;
            String line;
            while ((lineWithStatus = bufferedTaskReader.readLine()) != null) {
                line = lineWithStatus.substring(0, lineWithStatus.length() - 4);
                status = lineWithStatus.substring(lineWithStatus.length() - 4);
                parseAndAddToList(line);
                if (status.equals("done")) {
                    TaskList.markDone(TaskList.getThingsOnListSize() - 1);
                }
                System.out.println();
            }
            System.setOut(oldOutput);
            System.out.println("BARK WOOF: (You have these tasks currently: )");
            TaskList.viewList();
        } catch ( IOException e) {
            throw new DukeExceptions(e.getMessage());
        }
    }

    /**
     * Parses through input text to check for Duke.TaskList.Duke.commands
     * then adds a type of Task to the ArrayList of tasks.
     * @param input User-input string.
     * @throws DukeExceptions
     */
    public static String parseAndAddToList(String input) throws DukeExceptions {
        String output = "";
        int startingSize = TaskList.getThingsOnListSize();
        boolean searched = false;
        if (!input.isEmpty()) {
            UI.printLine();
            if (input.equals(UI.getMessage("BYE"))) {
                UI.stop();
                return UI.getMessage("GOODBYE_MSG");
            } else if (input.equals(UI.getMessage("LIST"))) {
                return TaskList.getListView();
            } else if (!input.isEmpty()) {
                int spaceIndex = input.indexOf(" ");
                if (spaceIndex != -1 && spaceIndex != input.length() - 1
                        && (input.contains(UI.getMessage("DELETE"))
                        || input.contains(UI.getMessage("DONE")))) {
                    try {
                        int x = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
                        if (x + 1 > TaskList.getThingsOnListSize()) {
                            throw new DukeExceptions("Woof? (This task doesn't exist?)");
                        }
                        if (input.substring(0, spaceIndex).equals(UI.getMessage("DONE"))) {
                            TaskList.markDone(x);
                            return TaskList.getListView() + "BARK BARK!!! (Task marked as done!!!)";
                        } else if (input.substring(0, spaceIndex).equals(UI.getMessage("DELETE"))) {
                            TaskList.deleteFromList(x);
                            return ("Bark bark: bork bark. (Removing task: " +
                                    TaskList.getThingsOnList().get(x) + ".");
                        }
                    } catch (NumberFormatException e) {
                        throw new DukeExceptions("Bark. (That number isn't on the list.)");
                    }
                } else {
                    int cmdIndex = input.indexOf("/");
                    if ((cmdIndex == 0 || (input.contains("todo") || input.contains("find")) && input.length() == 4)) {
                        throw new DukeExceptions("Bark bark? (There's no content for this command?)");
                    } else if (cmdIndex != -1 && cmdIndex != input.length() - 1) {
                        String cmd = input.substring(cmdIndex, cmdIndex + 3);
                        try {
                            if (cmd.equals("/by")) {
                                TaskList.addToList(new Deadlines(input));
                            } else if (cmd.equals("/at")) {
                                TaskList.addToList(new Events(input));
                            }
                        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                            throw new DukeExceptions(
                                    "Bark (Please make sure format of Date/Time is yyyy-MM-dd HHmm)");
                        }
                    } else if (input.contains("todo") && !(input.substring(input.length() - 4).contains("todo"))) {
                        if (input.substring(5).isEmpty()) {
                            throw new DukeExceptions("Bork? (This todo is empty?)");
                        }
                        TaskList.addToList(new ToDos(input));
                    } else if (input.contains("find") && !input.substring(4).contains("find")) {
                        searched = true;
                        if (input.substring(5).isEmpty()) {
                            throw new DukeExceptions("Bork?? (This find is empty?)");
                        }
                        return TaskList.getFind(input.substring(5));
                    }
                    if (TaskList.getThingsOnListSize() == startingSize && !searched) {
                        throw new DukeExceptions("Bark bark bark! (Please use me with proper commands!)");
                    } else {
                        return ("Bark. Bork: bark bark woof. (Roger. I've added this task:\n" +
                                TaskList.getLastTask() + "\n    " +
                                "Now you have " + TaskList.getThingsOnListSize() + " tasks in the list.)");
                    }
                }
            }
        }
        return output;
    }
}
