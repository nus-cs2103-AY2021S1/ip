package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;

public class Ui {

    /** Directory of local file to store tasks */
    protected String filePath;

    /** Name of local file to store tasks */
    protected String fileName;

    /** Parser object to understand requests */
    protected Parser parser;

    /** TaskList object to store Task objects and modify the list */
    protected TaskList taskList;

    /** Storage object to store and read Task objects */
    protected Storage storage;

    protected static boolean sleepMode = false;


    /**
     * Constructor of Ui class.
     * Initializes the filepath, filename, and list of tasks already stored in memory.
     *
     * @param filePath  Directory of local file to read and write.
     * @param fileName  Name of local file to read and write.
     * @param memoTask  List of Task objects stored in memory.
     */
    public Ui(String filePath, String fileName, List<Task> memoTask) {
        this.filePath = filePath;
        this.fileName = fileName;
        parser = new Parser();
        storage = new Storage(filePath, fileName);
        taskList = new TaskList(memoTask, filePath, fileName);
    }



    /**
     * Returns processed user input of commands once the system is awake.
     * Returns system response if the chatbot is in sleeping mode.
     *
     * @param input  User input of command.
     * @return processed system response to user commands.
     */
    public String[] processRequests(String input) {
        if (sleepMode && !input.equals("hello")) {
            return new String[]{"You have left the dialog.\nPlease restart the app or enter \"hello\" to wake me up!"};
        }

        List<String> response;
        // Parse the command and obtain the segmented parsed-command information
        String[] commandTask = parser.commandParser(input);

        if (commandTask.length == 1) {
            response = processSingleWordRequest(commandTask[0]);
        } else {
            response = processComplexRequest(commandTask);
        }

        return response.toArray(new String[response.size()]);
    }


    /**
     * Returns system response to single-word commands.
     *
     * @param command  user input of command.
     * @return output list in response to simple commands.
     */
    public List<String> processSingleWordRequest(String command) {
        List<String> response = new ArrayList<>();

        switch(command){
        case "hello":
            response.addAll(processHelloCommand());
            break;
        case "help":
            response.add(DukeException.fullGuide);
            break;
        case "bye":
            sleepMode = true;
            response.add("Bye. Hope to see you again soon!");
            break;
        case "archive":
            response.addAll(processArchiveRequest());
            break;
        case "listArchive":
            response.addAll(processListArchiveRequest());
            break;
        case "list":
        default:
            response.add(processListCommand());
            break;
        }

        return response;
    }


    /**
     * Returns list of all tasks in response to 'list' command.
     *
     * @return system response to 'hello' command.
     */
    public String processListCommand() {
        String output = "Here are the tasks in your list:\n";
        Iterator task_iter = taskList.showList().iterator();

        int temp = 1;
        while (task_iter.hasNext()) {
            output += "\n" + temp + ". " + task_iter.next();
            temp++;
        }
        return output;
    }


    /**
     * Returns opening address in response to 'hello' command that wakes the system.
     * Modifies the system sleep-status when the system is successfully waken.
     *
     * @return system response to 'hello' command.
     */
    public List<String> processHelloCommand() {
        List<String> output = new ArrayList<>();
        if (sleepMode) {
            sleepMode = false;
            output.add("Hello! This is J.A.R.V.I.S.\nHow may I help you?");
            output.add("Enter 'help' for command guide.");
        } else {
            output.add("You have already started our dialog.\nPlease enter your command :)");
        }
        return output;

    }


    /**
     * Returns system response to multiple-word commands.
     *
     * @param commandTask  processed user input of command.
     * @return output list containing system response to complex commands.
     */
    public List<String> processComplexRequest(String[] commandTask) {

        String commandType = commandTask[0];
        switch (commandType) {
        case "find":
            return processFindKeyword(commandTask[1]);
        case "exception":
            return processException(commandTask[1]);
        case "done":
        case "delete":
            return processEditRequest(commandTask);
        case "loadArchive":
            return processLoadArchiveRequest(commandTask[1]);
        case "binArchive":
            return processBinArchiveRequest(commandTask[1]);
        case "todo":
        case "event":
        case "deadline":
        default:
            return processAddRequest(commandTask);
        }
    }


    /**
     * Returns system response to 'editing' requests, i.e. 'done' and 'delete'.
     *
     * @param commandTask  processed user input of command.
     * @return System response to user input of modification commands.
     */
    public List<String> processEditRequest(String[] commandTask) {
        List<String> response = taskList.editTask(commandTask);
        return response;
    }


    /**
     * Returns system response to 'archive' request.
     *
     * @return system response to 'archive' request.
     */
    public List<String> processArchiveRequest() {
        List<String> response = new ArrayList<>();

        // fileTimeCode is unique for every file since it implies its creation time
        long fileTimeCode = new Date().getTime();
        String archiveFileName = "Archive-" + fileTimeCode + ".txt";

        Storage s = new Storage(filePath, archiveFileName);
        boolean success = s.write_memory(taskList.showList());

        if (success) {
            response.add("Tasks successfully archived! Enter 'listArchive' to observe a new file being added.");
        } else {
            response.addAll(HandleException.handleException(
                    DukeException.ExceptionType.READ_FILE));
        }
        return response;
    }


    /**
     * Returns system response to 'listArchive' request.
     *
     * @return system response to 'listArchive' request.
     */
    public List<String> processListArchiveRequest() {
        List<String> response = new ArrayList<>();
        File archiveDirPath = new File(filePath);
        FilenameFilter archiveFilefilter = (directory, fileName) -> {
            return fileName.startsWith("Archive-");
        };

        File filesList[] = archiveDirPath.listFiles(archiveFilefilter);
        Arrays.sort(filesList, Comparator.comparingLong(File::lastModified));

        String output = "";
        output += "* Please Note:\n";
        output += "Your current list will be discarded once you switch to an archive file.\n";
        output += "Key in 'archive' to save your work before continuing with 'loadArchive FILE_NAME'.\n\n";
        output += "Use 'File name' of the archive file for any further operation.\n";

        for(File file : filesList) {
            output += "\n" + "File name: " + file.getName();
            output += "\n" + "Archived at: " + new Date(file.lastModified()) + "\n";
        }
        response.add(output);
        return response;
    }


    /**
     * Returns system response to 'loadArchiveLoad' request.
     *
     * @param arcFileName  Name of the file to load and read from.
     * @return system response to 'loadArchiveLoad' request.
     */
    public List<String> processLoadArchiveRequest(String arcFileName) {
        List<String> response = new ArrayList<>();
        File testPath = new File (filePath + arcFileName);
        if (!testPath.exists()) {
            response.add("☹ OOPS!!! Invalid input of filename. Please copy and paste with care.");
            return response;
        }

        try {
            response.addAll(loadArchiveHelper(arcFileName));
        } catch (Exception e) {
            response.addAll(HandleException.handleException(DukeException.ExceptionType.READ_FILE));
        }
        return response;
    }


    /**
     * Returns system response to a successful 'loadArchiveLoad' operation.
     * Helps with the operation of loading an archived list to the current list.
     *
     * @param arcFileName  Name of the file to load and read from.
     * @return system response to successful 'loadArchiveLoad' operation.
     */
    public List<String> loadArchiveHelper(String arcFileName) throws IOException {
        List<String> response = new ArrayList<>();
        Storage s = new Storage(filePath, arcFileName);
        // Read the tasks from the archive file
        List<Task> tl = s.readMemoTasks();
        // Rewrite memory of the current file (to load the archived list)
        boolean successReplacement = storage.write_memory(tl);
        if (!successReplacement) {
            response.addAll(HandleException.handleException(DukeException.ExceptionType.READ_FILE));
            return response;
        }
        // If successfully loaded to the current setting, return response message
        taskList = new TaskList(tl, filePath, fileName);
        File arcFile = new File(filePath, arcFileName);
        Date arcFileCreationDate = new Date(arcFile.lastModified());
        String output = "";
        output += "Task List successfully loaded from Archive file '" + arcFileName + "' created at  "
                + arcFileCreationDate + "!";
        output += "\nEnter 'list' to see the tasks!";
        response.add(output);
        return response;
    }


    /**
     * Returns system response to 'binArchiveLoad' request.
     *
     * @param arcFileName  Name of the archive file to delete.
     * @return system response to 'binArchiveLoad' request.
     */
    public List<String> processBinArchiveRequest(String arcFileName) {
        List<String> response = new ArrayList<>();
        File testPathFile = new File (filePath + arcFileName);

        // Check if the file exists (if the user inputs the wrong filename)
        if (!testPathFile.exists()) {
            response.add("☹ OOPS!!! Invalid input of filename. Please copy and paste with care.");
            return response;
        }

        // Delete that archived file and return response message
        Date binFileCreationDate = new Date(testPathFile.lastModified());
        testPathFile.delete();
        response.add("Successful deletion of archive file '" + arcFileName + "' created at "
                + binFileCreationDate + "!" + "\nEnter 'listArchive' to see the current file list!");

        return response;
    }


    /**
     * Returns output list containing system response to task-addition commands'.
     *
     * @param commandTask  processed user input of task-addition command.
     * @return output list containing system response to todo', 'event', 'deadline' commands.
     */
    public List<String> processAddRequest(String[] commandTask) {
        List<String> response = taskList.addTask(commandTask);
        return response;
    }


    /**
     * Returns output list containing system response to 'find' command.
     *
     * @param keyword  word to be searched in the task list.
     * @return output list containing system response to 'find' command.
     */
    public List<String> processFindKeyword(String keyword) {
        List<String> response = new ArrayList<>();
        List<Task> matchList = taskList.searchTask(keyword);

        // If there is no match, return a corresponding message
        if (matchList.size() == 0) {
            response.add("Sorry, there is no match for your keyword!");
            return response;
        }

        String output = "Here are the tasks that match your keyword:\n";
        Iterator itr = matchList.iterator();
        int taskNumber = 1;

        while (itr.hasNext()) {
            output += "\n" + taskNumber + "." + itr.next();
            taskNumber++;
        }

        response.add(output);
        return response;
    }


    /**
     * Returns output list after adding the information about the exception.
     *
     * @param exceptionType  type of exception encountered.
     * @return output list after adding the information about the exception.
     */
    public List<String> processException(String exceptionType) {
        List<String> response = new ArrayList<>();
        response.addAll(HandleException.handleException(
                        returnException(exceptionType)));
        return response;
    }


    /**
     * Returns ExceptionType in response to different exceptions caught.
     *
     * @param exceptionType  type of exception encountered.
     * @return ExceptionType that corresponds to the exception caught.
     */
    public DukeException.ExceptionType returnException(String exceptionType) {
        switch (exceptionType) {
        case "todo":
            return DukeException.ExceptionType.TODO_INCOMPLETE;
        case "event":
            return DukeException.ExceptionType.EVENT_INCOMPLETE;
        case "deadline":
            return DukeException.ExceptionType.DEADLINE_INCOMPLETE;
        case "empty_illegal":
            return DukeException.ExceptionType.EMPTY_ILLEGAL;
        case "improperDateTime":
            return DukeException.ExceptionType.IMPROPER_DATETIME;
        case "find":
            return DukeException.ExceptionType.FIND_INCOMPLETE;
        case "no_meaning":
        default:
            return DukeException.ExceptionType.NO_MEANING;
        }

    }

}