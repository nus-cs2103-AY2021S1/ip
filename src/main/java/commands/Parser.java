package main.java.commands;

import main.java.DukeExceptions;
import main.java.TaskList.TaskList;
import main.java.TaskList.tasks.Events;
import main.java.TaskList.tasks.ToDos;
import main.java.UI.UI;
import main.java.TaskList.tasks.Deadlines;

import java.io.FileReader;
import java.io.File;
import java.io.PrintStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.time.format.DateTimeParseException;

public class Parser {
    private static FileReader taskReader;

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
            String line;
            while ((line = bufferedTaskReader.readLine()) != null) {
                parseAndAddToList(line);
                System.out.println(line);
            }
            System.setOut(oldOutput);
            System.out.println("    BARK WOOF: (You have these tasks currently: )");
            TaskList.viewList();
        } catch ( IOException e) {
            throw new DukeExceptions(e.getMessage());
        }
    }

    public static void parseAndAddToList(String input) throws DukeExceptions {
        int startingSize = TaskList.getThingsOnListSize();
        if (!input.isEmpty()) {
            UI.printLine();
            if (input.equals(UI.getMessage("BYE"))) {
                UI.stop();
                UI.printGoodbye();
            } else if (input.equals(UI.getMessage("LIST"))) {
                TaskList.viewList();
            } else if (!input.isEmpty()) {
                int spaceIndex = input.indexOf(" ");
                if (spaceIndex != -1 && spaceIndex != input.length() - 1 && (input.contains(UI.getMessage("DELETE"))
                        || input.contains(UI.getMessage("DONE")))) {
                    try {
                        int x = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
                        if (x + 1 > TaskList.getThingsOnListSize()) {
                            throw new DukeExceptions("    Woof? (This task doesn't exist?");
                        }
                        if (input.substring(0, spaceIndex).equals(UI.getMessage("DONE"))) {
                            System.out.println("    BARK BARK!!! (Task marked as done!!!)");
                            TaskList.markDone(x);
                            TaskList.viewList();
                        } else if (input.substring(0, spaceIndex).equals(UI.getMessage("DELETE"))) {
                            System.out.println("    Bark bark: bork bark. (Removing task: " +
                                    TaskList.getThingsOnList().get(x) + ".");
                            TaskList.deleteFromList(x);
                        }
                    } catch (NumberFormatException e) {
                        throw new DukeExceptions("    Bark. (That number isn't on the list.)");
                    }
                } else {
                    int cmdIndex = input.indexOf("/");
                    if (cmdIndex == 0 || (input.contains("todo") && input.length() == 4)) {
                        throw new DukeExceptions("    Bark bark? (There's no content for this command?");
                    } else if (cmdIndex != -1 && cmdIndex != input.length() - 1) {
                        String cmd = input.substring(cmdIndex, cmdIndex + 3);
                        try {
                            if (cmd.equals("/by")) {
                                TaskList.addToList(new Deadlines(input));
                            } else if (cmd.equals("/at")) {
                                TaskList.addToList(new Events(input));
                            }
                        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                            throw new DukeExceptions("    Bark (Please make sure format of Date/Time is yyyy-MM-dd HHmm)");
                        }
                    } else if (input.contains("todo") && !(input.substring(input.length() - 4).contains("todo"))) {
                        if (input.substring(5).isEmpty()) {
                            throw new DukeExceptions("    Bork? (This todo is empty?)");
                        }
                        TaskList.addToList(new ToDos(input));
                    }
                    if (TaskList.getThingsOnListSize() == startingSize) {
                        throw new DukeExceptions("    Bark bark bark! (Please use me with proper commands!)");
                    } else {
                        System.out.println("    Bark. Bork: bark bark woof. (Roger. I've added this task:\n    " +
                                TaskList.getLastTask() + "\n    " +
                                "Now you have " + TaskList.getThingsOnListSize() + " tasks in the list.)");
                    }
                }
            }
            UI.printLine();
        }
    }
}
