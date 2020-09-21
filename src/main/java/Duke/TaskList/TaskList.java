package Duke.TaskList;

import Duke.DukeExceptions;
import Duke.TaskList.tasks.Event;
import Duke.TaskList.tasks.Task;
import Duke.TaskList.tasks.ToDos;
import Duke.TaskList.tasks.Deadline;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> thingsOnList = new ArrayList<>();

    /**
     * Returns size of current list of tasks.
     * @return size of current list of tasks.
     */
    public static int getThingsOnListSize() {
        return thingsOnList.size();
    }

    /**
     * Adds a task to list.
     * @param task
     */
    private static String addToList(Task task) {
        thingsOnList.add(task);
        return "Bark. Bork: bark bark woof. (Roger. I've added this task:\n" +
                getLastTask() + "\n" +
                "Now you have " + getThingsOnListSize() + " tasks in the list.)";
    }

    /**
     * Deletes the xth task from the list.
     * @param cmd String that determines which task to delete
     */
    public static String deleteFromList(String cmd) throws DukeExceptions {
        try {
            int x = Integer.parseInt(cmd.substring("/delete".length() + 1));
            String delString = "Bark bark: bork bark. (Removing task: " + thingsOnList.get(x - 1) + ".";
            thingsOnList.remove(x - 1);
            return delString;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeExceptions("Woof? (This task doesn't exist?)");
        }
    }

    /**
     * Retrieves a help statement to guide users on the types of commands
     * @return String containing all the commands
     */
    public static String getHelp() {
        String newLine = System.getProperty("line.separator");
        return "Commands:" + newLine + "ACCESS USER GUIDE: /UG" + newLine + "ADD NEW TODO: /todo *Thing to do*" +
                newLine + "ADD NEW EVENT: *task* /at  *(start in yyyy-MM-dd HHmm) (end in yyyy-MM-dd HHmm)*"
                + newLine + "ADD NEW DEADLINE: *task* /by *deadline in yyyy-MM-dd HHmm*" + newLine +
                "DISPLAY LIST: /list" + newLine + "SEARCH FOR A KEYWORD: " + "/find *text to search for*" +
                newLine + "DELETE A TASK: /delete *index of task to delete*" + newLine +
                "MARK A TASK AS DONE: /done *index of task finished" + newLine + "UPDATE A TASK: " +
                "/update *index of task to update* *updated content of task*" + newLine + "SAVE AND CLOSE: /bye";
    }

    /**
     * Adds a task of type ToDo given the input
     * @param input content of todo
     * @return acknowledgement that todo has been added
     * @throws DukeExceptions
     */
    public static String addToDo(String input) throws DukeExceptions {
        try {
            return addToList(new ToDos(input));
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new DukeExceptions(
                    "Bark (Please make sure format of Date/Time is yyyy-MM-dd HHmm)");
        }
    }

    /**
     * Adds a task of type Event
     * @param input content of event
     * @return acknowledgement that event has been added
     * @throws DukeExceptions
     */
    public static String addEvent(String input) throws DukeExceptions {
        try {
            return addToList(new Event(input));
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new DukeExceptions(
                    "Bark (Please make sure format of Date/Time is yyyy-MM-dd HHmm)");
        }
    }

    /**
     * Adds a task of type Deadline
     * @param input content of deadline
     * @return acknowledgement that deadline has been added
     * @throws DukeExceptions
     */
    public static String addDeadline(String input) throws DukeExceptions {
        try {
            return addToList(new Deadline(input));
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new DukeExceptions(
                    "Bark (Please make sure format of Date/Time is yyyy-MM-dd HHmm)");
        }
    }

    /**
     * Gets the last task from the list.
     * @return last task on list.
     */
    public static Task getLastTask() {
        return thingsOnList.get(thingsOnList.size() - 1);
    }

    /**
     * Returns an ArrayList of the list.
     * @return ArrayList of tasks on the list.
     */
    public static ArrayList<Task> getThingsOnList() {
        return thingsOnList;
    }

    /**
     * Marks task x as done.
     * @param cmd the task to be marked as done.
     * @return acknowledgement that the task has been completed
     */
    public static String markDone(String cmd) throws DukeExceptions {
        try {
            int x = Integer.parseInt(cmd.substring(6));
            thingsOnList.get(x - 1).markAsDone();
            return getListView() + "BARK BARK!!! (Task " + x + " marked as done!!!)";
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeExceptions("Woof? (This task doesn't exist?)");
        }
    }

    /**
     * updates the text of a given task
     * @param cmd contains both the index of the task to update, as well as the update text
     * @return acknowledgement that
     * @throws DukeExceptions
     */
    public static String update(String cmd) throws DukeExceptions {
        try {
            String withoutCommand = cmd.substring("update".length() + 2);
            int spaceIndex = withoutCommand.indexOf(" ");
            int x = Integer.parseInt(withoutCommand.substring(0, spaceIndex));
            String newTask = withoutCommand.substring(spaceIndex + 1);
            thingsOnList.get(x - 1).update(newTask);
            return "Bark bark: (bark) (Message " + x + " updated to: " + newTask + ")";
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeExceptions("Woof? (This task doesn't exist?)");
        }
    }

    /**
     * Converts list into a string representation
     * @return String form of the list of thingsOnList.
     */
    public static String getListView() {
        String list = "";
        if (thingsOnList.size() == 0) {
            list = "Bark bark. (No tasks right now.)";
        } else {
            list += "BARK WOOF: (You have these tasks currently: )\n";
            for (int i = 0; i < thingsOnList.size(); i++) {
                list += (i + 1) + ". " + thingsOnList.get(i) + "\n";
            }
        }
        return list;
    }

    /**
     * Opens the github readme on the default browser
     * @return Message depending on success or failure of opening the site.
     */
    public static String getUG() {
        try {
            String UG = "https://theyellowfellow.github.io/ip/";
            if (System.getProperty("os.name").contains("Windows")) {
                Desktop desktop = java.awt.Desktop.getDesktop();
                URI userGuideSite = new URI(UG);
                desktop.browse(userGuideSite);
            } else {
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("xdg-open " + UG);
            }
        } catch (IOException | URISyntaxException e) {
            return "Bark. (Sorry, we couldn't open the website.)";
        }
        return "Bark bark!! (User Guide opened!!)";
    }

    /**
     * Searches the list for the key word, and prints a new list containing tasks with the key word.
     * @param input Contains key word for the search
     * @return Tasks which match the keyword
     */
    public static String find (String input) throws DukeExceptions {
        String keyWord;
        try {
            keyWord = input.substring("/find ".length());
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeExceptions("Error: Input a keyword.");
        }
        boolean found = false;
        String foundText = "Woof bark: (Here are the tasks that match your key word: )";
        System.out.println("Woof bark: (Here are the tasks that match your key word: )");
        for (int i = 0; i < thingsOnList.size(); i++) {
            if (thingsOnList.get(i).toString().toLowerCase().contains(keyWord.toLowerCase())) {
                foundText += "\n" + ((i + 1) + ". "  + thingsOnList.get(i));
                found = true;
            }
        }
        if (!found) {
            return "Bark bark :< (There were no tasks that matched :<)";
        }
        return foundText;
    }
}
