import java.io.IOException;
import java.time.LocalDate;

/**
 * Handles parsing of user inputs.
 */
public class Parser {

    /**
     * Stores the parsed user inputs and prints response messages for user.
     * 
     * @param userInput User input read by scanner.
     * @param duke Duke object.
     * @return Duke response message.
     */
    public String parse(String userInput, Duke duke) {
        String[] strArr = userInput.split(" ");
        assert strArr.length > 0 : "User input is not processed";
        try {
            check(strArr);
            if (userInput.equals("help")) {
                return duke.getUi().printHelp();
            } else if (userInput.equals("bye")) {
                return duke.getUi().printBye();
            } else if (userInput.equals("list")) {
                return duke.getUi().printTaskList(duke.getTasks());
            } else if (strArr[0].equals("done")) {
                return handleDone(strArr, duke);
            } else if (strArr[0].equals("delete")) {
                return handleDelete(strArr, duke);
            } else if (strArr[0].equals("find")) {
                return handleFind(strArr, duke);
            } else {
                return handleAddTask(strArr, duke);
            }
        } catch (Exception e) {
            return duke.getUi().showError(e.toString());
        }
    }

    /**
     * Handles response message on find command.
     *
     * @param command User input.
     * @param duke Duke object.
     * @return Duke response message.
     */
    public String handleFind(String[] command, Duke duke) throws IOException {
        String keyword = command[1];
        TaskList taskLst = new TaskList();
        for (int i = 0; i < duke.getTasks().getSize(); i++) {
            if (duke.getTasks().getTask(i).getDescription().contains(keyword)) {
                taskLst.addTask(duke.getTasks().getTask(i));
            }
        }
        return duke.getUi().printMatchingTasks(taskLst);
    }
    
    /**
     * Handles response message on done command.
     *
     * @param command User input.
     * @param duke Duke object.
     * @return Duke response message.
     */
    public String handleDone(String[] command, Duke duke) throws IOException {
        duke.getTasks().getTask(Integer.parseInt(command[1]) - 1).markAsDone();
        duke.getStorage().writeFile(duke.getTasks().getTaskList());
        return duke.getUi().printDoneTask(duke.getTasks().getTask(Integer.parseInt(command[1]) - 1));
    }

    /**
     * Handles response message on delete command.
     *
     * @param command User input.
     * @param duke Duke object.
     * @return Duke response message.
     */
    public String handleDelete(String[] command, Duke duke) throws IOException {
        int integer = Integer.parseInt(command[1]) - 1;
        Task task = duke.getTasks().getTask(Integer.parseInt(command[1]) - 1);
        duke.getTasks().deleteTask(integer);
        duke.getStorage().writeFile(duke.getTasks().getTaskList());
        return duke.getUi().printDeleteTask(task, duke.getTasks());
    }
    
    /**
     * Handles response message on task adding commands.
     * 
     * @param command User input.
     * @param duke Duke object.
     * @return Duke response message.
     */
    public String handleAddTask(String[] command, Duke duke) throws IOException, DukeException {
        if (command[0].equals("yes")) {
            if (duke.getDuplicateTask() == null) {
                throw new DukeException("duplicate");
            }
            Task task = duke.getDuplicateTask();
            duke.getTasks().addTask(task);
            duke.getStorage().writeFile(duke.getTasks().getTaskList());
            duke.resetDuplicateTask();
            return duke.getUi().printAddTask(task, duke.getTasks());
        } else if (command[0].equals("no")) {
            if (duke.getDuplicateTask() == null) { 
                throw new DukeException("duplicate");
            }
            duke.resetDuplicateTask();
            return duke.getUi().printHandleDuplicate();
        } else if (command[0].equals("todo")) {
            String description = "";
            for (int i = 1; i < command.length; i++) {
                description += command[i] + " ";
            }
            Task task = new Todo(description);
            if (detectDuplicates(task.toString(), duke.getTasks())) {
                duke.setDuplicateTask(task);
                return duke.getUi().detectDuplicatesError();
            } else {
                duke.getTasks().addTask(task);
                duke.getStorage().writeFile(duke.getTasks().getTaskList());
                return duke.getUi().printAddTask(task, duke.getTasks());
            }
        } else if (command[0].equals("deadline")) {
            String description = "";
            String date = "";
            boolean isBy = false;
            for (int i = 1; i < command.length - 1; i++) {
                if (command[i].equals("/by")) {
                    isBy = true;
                    description = description.substring(0, description.length() - 1);
                }
                if (!isBy) {
                    description += command[i] + " ";
                } else {
                    if (i == command.length - 2) {
                        date = date + command[i + 1];
                    } else {
                        date = date + command[i + 1] + " ";
                    }
                }
            }
            Task task = new Deadline(description, LocalDate.parse(date));
            if (detectDuplicates(task.toString(), duke.getTasks())) {
                duke.setDuplicateTask(task);
                return duke.getUi().detectDuplicatesError();
            } else {
                duke.getTasks().addTask(task);
                duke.getStorage().writeFile(duke.getTasks().getTaskList());
                return duke.getUi().printAddTask(task, duke.getTasks());
            }
        } else if (command[0].equals("event")) {
            String description = "";
            String date = "";
            boolean isAt = false;
            for (int i = 1; i < command.length - 1; i++) {
                if (command[i].equals("/at")) {
                    isAt = true;
                    description = description.substring(0, description.length() - 1);
                }
                if (isAt) {
                    description += command[i] + " ";
                } else {
                    if (i == command.length - 2) {
                        date = date + command[i + 1];
                    } else {
                        date = date + command[i + 1] + " ";
                    }
                }
            }
            Task task = new Event(description, LocalDate.parse(date));
            if (detectDuplicates(task.toString(), duke.getTasks())) {
                duke.setDuplicateTask(task);
                return duke.getUi().detectDuplicatesError();
            } else {
                duke.getTasks().addTask(task);
                duke.getStorage().writeFile(duke.getTasks().getTaskList());
                return duke.getUi().printAddTask(task, duke.getTasks());
            }
        }
        return "";
    }
    
    public boolean detectDuplicates(String str, TaskList taskList) {
        for (int i = 0; i < taskList.getSize(); i++) {
            boolean isDuplicate = str.equals(taskList.getTask(i).toString());
            if (isDuplicate) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks whether has invalid user inputs.
     * 
     * @param arr Array of user input.
     * @throws DukeException Exception specific to Duke.
     */
    public void check(String[] arr) throws DukeException {
        if (arr.length == 1 && (arr[0].equals("todo") || arr[0].equals("deadline") || arr[0].equals("event") ||
                arr[0].equals("done") || arr[0].equals("delete") || arr[0].equals("find"))) {
            throw new DukeException(arr[0]);
        } else if (!arr[0].equals("todo") && !arr[0].equals("deadline") && !arr[0].equals("event") &&
                !arr[0].equals("list") && !arr[0].equals("bye") && !arr[0].equals("done") 
                && !arr[0].equals("delete") && !arr[0].equals("find") && !arr[0].equals("help") 
                && !arr[0].equals("yes") && !arr[0].equals("no")) {
            throw new DukeException("other");
        }
    }
}
