import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Duke {
    static char line = '*';
    static TaskList taskList;
    static Ui ui;
    private static File file;
    private static Storage storage;

    static void loopMethod() {
        //gets input and displays it
        ui.handleInteraction();
    }


    /**
     * Loads a file from a given file path.
     * @param filePath Location of the file
     */
    static void loadFile(String filePath) {
        file = new File(filePath);
    }


    /**
     * Given a file path, read all contents of the file and convert them to Tasks
     */
    static void readFile() {
        List<String> sentences = storage.readFile();
        for (String sentence : sentences) {
            String taskName = "";
            int index;
            if (sentence.startsWith("[T]")) {
                //Sentence is in the form [T][<Status of Task> ]  <Task Name>
                //find out whether task is done
                index = sentence.indexOf("]");
                if (index == -1 || index >= sentence.length() - 1) {
                    continue;
                }
                //remove [T]
                sentence = sentence.substring(index + 1);
                boolean isCompleted = sentence.startsWith("[DONE]");
                //get index after closing bracket ]
                index = sentence.indexOf("]");
                if (index == -1 || index >= sentence.length() - 3) {
                    continue;
                }

                //get task name
                taskName = sentence.substring(index + 3);
                Task toBeAdded = isCompleted ? new Todo(taskName, true) : new Todo(taskName);
                taskList.addTask(toBeAdded);
            } else if (sentence.startsWith("[D]")) {
                //Sentence is in the form [D][<Status of Task>]  <Task Name>  (<Deadline>)
                //remove [D]
                index = sentence.indexOf("]");
                if (index == -1 || index >= sentence.length() - 1) {
                    continue;
                }
                sentence = sentence.substring(index + 1);


                //check if task is completed
                boolean isCompleted = sentence.startsWith("[DONE]");


                //get index after closing bracket ]
                index = sentence.indexOf("]");
                if (index == -1 || index >= sentence.length() - 3) {
                    continue;
                }

                //get task name, i.e. everything before opening bracket
                sentence = sentence.substring(index + 3);
                taskName = getStringBeforeCharacter(sentence, '(');
                if (taskName.length() == 0) {
                    //corrupted entry
                    continue;
                }

                //get task deadline
                String deadline = getStringAfterCharacter(sentence, ' ');
                if (deadline.length() == 0) {
                    //corrupted entry
                    continue;
                }
                //remove () brackets and by
                index = deadline.indexOf(":");
                if (index == -1 || index >= deadline.length() - 1) {
                    continue;
                }

                deadline = deadline.substring(index + 2);
                //remove ) closing bracket
                deadline = deadline.substring(0, deadline.length() - 1);
                //add task
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                    LocalDate deadlineDate = LocalDate.parse(deadline, formatter);
                    Task toBeAdded = new Deadline(taskName, deadlineDate, isCompleted);
                    taskList.addTask(toBeAdded);
                } catch (DateTimeParseException e) {
                    //corrupted input
                    continue;
                }

            } else if (sentence.startsWith("[E")) {
                //Sentence is in the form [E][<Status of Task>]   (at: tmr)

                //remove [E]
                index = sentence.indexOf("]");
                if (index == -1 || index >= sentence.length() - 1) {
                    continue;
                }
                sentence = sentence.substring(index + 1);


                //check if task is completed
                boolean isCompleted = sentence.startsWith("[DONE]");


                //get index after closing bracket ]
                index = sentence.indexOf("]");
                if (index == -1 || index >= sentence.length() - 3) {
                    continue;
                }

                //get task name
                sentence = sentence.substring(index + 3);
                taskName = getStringBeforeCharacter(sentence, '(');
                if (taskName.length() == 0) {
                    //corrupted entry
                    continue;
                }

                //get task deadline
                String time = getStringAfterCharacter(sentence, ' ');
                if (time.length() == 0) {
                    //corrupted entry
                    continue;
                }

                //remove () brackets and at
                index = time.indexOf(":");
                if (index == -1 || index >= time.length() - 1) {
                    continue;
                }
                time = time.substring(index + 2);

                //remove ) closing bracket
                time = time.substring(0, time.length() - 1);

                //add task
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                    LocalDate timeDate = LocalDate.parse(time, formatter);
                    Task toBeAdded = new Event(taskName, timeDate, isCompleted);
                    taskList.addTask(toBeAdded);
                } catch (DateTimeParseException e) {
                    //corrupted input
                    continue;
                }
            }
        }
    }

    /**
     * Get the substring that comes after the first instance of the character
     * Returns an empty String if character is not found in the String
     * Example: getStringAfterCharacter("abc def", ' ') returns "def"
     * @param task String to be operated on
     * @param character Character for the String to search for
     * @return Substring after the character
     */
    static String getStringAfterCharacter(String task, Character character) {
        //gets String after character, e.g. if input is "Apple Juice" and character is " ", method returns "Juice"
        int index = task.indexOf(character);
        if (index == -1 || index == task.length() - 1) {
            //index not found, or index is last char in string
            return "";
        } else {
            //get everything after the space
            String taskName = task.substring(index + 1);
            return taskName;
        }
    }


    /**
     * Get the substring that comes before the first instance of the character
     * Returns an empty String if character is not found
     * Example: getStringAfterCharacter("abc def", ' ') returns "abc"
     * @param task String to be operated on
     * @param character Character for the String to search for
     * @return Substring after the character
     */
    static String getStringBeforeCharacter(String task, char character) {
        //gets a string before the first character
        //e.g. if input is "Apple juice", and character is " ", method returns "Apple"
        int index = task.indexOf(character);
        if (index == -1 || index == 0) {
            //space not found, or index is first char in String
            return "";
        } else {
            return task.substring(0, index);
        }
    }


    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        String filePath = home + "/tasks.txt";
        taskList = new TaskList();
        storage = new Storage(filePath);
        ui = new Ui(taskList, storage);
        loadFile(filePath);
        boolean fileExists = file.exists();
        if (fileExists) {
            readFile();
        }
        loopMethod();
    }
}
