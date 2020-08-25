import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Command {
    private CommandType commandType;
    private String[] commandArr;

    /**
     * Command constructor specifying CommandType and command array.
     *
     * @param commandType
     * @param commandArr String array which has remove spaces from string
     */
    public Command(CommandType commandType, String[] commandArr) {
        this.commandType = commandType;
        this.commandArr = commandArr;
    }

    /**
     * Print all the tasks in the TaskList.
     *
     * @param tasks
     */
    public void printAllTask(TaskList tasks) {
        int numTask = 0;
        System.out.println("Here are the tasks in your list:");
        while (numTask < tasks.size()) {
            System.out.println(Integer.valueOf(numTask + 1) + "." + tasks.getTask(numTask));
            numTask++;
        }
    }

    /**
     * Print all the matching tasks.
     *
     * @param tasks ArrayList
     */
    public void printSearchedTask(ArrayList<Task> tasks) {
        int numTask = 0;
        System.out.println("Here are the matching tasks in your list:");
        while (numTask < tasks.size()) {
            System.out.println(Integer.valueOf(numTask + 1) + "." + tasks.get(numTask));
            numTask++;
        }
    }

    /**
     * Delete a task using its position from TaskList.
     *
     * @param pos int
     * @param tasks TaskList
     */
    public void deleteTask(int pos, TaskList tasks) {
        System.out.println("Noted. I've removed this task: \n" + tasks.getTask(pos) +
                "\n" + "Now you have " + Integer.valueOf(tasks.size() - 1) + " tasks in the list.");
        tasks.removeTask(pos);
    }

    /**
     * Remove first string in the string array.
     *
     * @param arr string array
     * @return string array
     */
    private static String[] removeFirst(String[] arr) {
        String[] tempArr = new String[arr.length];
        for (int i = 0; i < arr.length - 1; i++) {
            tempArr[i] = arr[i + 1];
        }
        return tempArr;
    }

    /**
     * Remove the word and after the word from string array.
     *
     * @param arr string array
     * @param word string
     * @return string array before the word
     */
    private static String[] removeAfterWord(String[] arr, String word) {
        String[] temp = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(word)) {
                break;
            } else {
                temp[i] = arr[i];
            }
        }
        return temp;
    }

    /**
     * Keep those string after the word into a string array and return.
     *
     * @param arr original string array
     * @param word string
     * @return string array
     */
    private static String[] keepAfterWord(String[] arr, String word) {
        String[] temp = new String[arr.length];
        int counter = 0;
        // find position of the word
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(word)) {
                counter = i;
                break;
            }
        }
        counter++;
        for (int i = 0; (counter + i) < arr.length; i++) {
            temp[i] = arr[counter + i];
        }
        return temp;
    }

    /**
     * Join all string in the array into a single string with spacing.
     *
     * @param arr string array
     * @return string
     */
    private static String joinString(String[] arr) {
        String text = arr[0];
        if (arr.length == 1) {
            return text;
        } else {
            for (int i = 1; i < arr.length && arr[i] != null; i++) {
                text = text + " " + arr[i];
            }
        }
        return text;
    }

    /**
     * Create a Todo task.
     *
     * @return Task
     * @throws InvalidTodoDescripDukeException
     */
    private Task createTodo() throws InvalidTodoDescripDukeException {
        if (commandArr.length == 1) {
            throw new InvalidTodoDescripDukeException();
        }
        String[] modifiedCommand = removeFirst(commandArr);
        Task newTask = new Todo(joinString(modifiedCommand));
        return newTask;
    }

    /**
     * Create a Deadline task.
     *
     * @return Task
     * @throws InvalidDeadlineDescripDukeException
     */
    private Task createDeadline() throws InvalidDeadlineDescripDukeException, ParseException {
        if (commandArr.length == 1) {
            throw new InvalidDeadlineDescripDukeException();
        }
        String[] modifiedCommand = removeFirst(commandArr);
        String[] upper = removeAfterWord(modifiedCommand, "/by");
        String[] lower = keepAfterWord(modifiedCommand, "/by");
        LocalDateTime dateAndTime = Parser.changeDateAndTime(lower);
        Task newTask = new Deadline(joinString(upper), dateAndTime);
        return newTask;
    }

    /**
     * Create an Event task.
     *
     * @return Task
     * @throws InvalidEventDescripDukeException
     */
    private Task createEvent() throws InvalidEventDescripDukeException {
        if (commandArr.length == 1) {
            throw new InvalidEventDescripDukeException();
        }
        String[] modifiedCommand = removeFirst(commandArr);
        String[] upper = removeAfterWord(modifiedCommand, "/at");
        String[] lower = keepAfterWord(modifiedCommand, "/at");
        LocalDateTime dateAndTime = Parser.changeDateAndTime(lower);
        Task newTask = new Event(joinString(upper), dateAndTime);
        return newTask;
    }

    /**
     * Search keyword from all the Tasks' description in the TaskList given and
     * return arraylist of task that match the keyword.
     *
     * @param tasks TaskList
     * @param keyword string
     * @return ArrayList of Task
     */
    public ArrayList<Task> searchKeyWord(TaskList tasks, String keyword) {
        ArrayList<Task> tempTasks = new ArrayList<>();
        for (Task task: tasks.tasks) {
            if (task.description.matches("(.*)" + keyword + "(.*)")) {
                tempTasks.add(task);
            }
        }
        return tempTasks;
    }

    /**
     * Execute Command.
     *
     * @param tasks TaskList
     * @param ui Ui
     * @param storage Storage
     * @throws InvalidTodoDescripDukeException
     * @throws InvalidDeadlineDescripDukeException
     * @throws InvalidEventDescripDukeException
     * @throws InvalidFirstDukeException
     * @throws ParseException
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTodoDescripDukeException,
            InvalidDeadlineDescripDukeException, InvalidEventDescripDukeException,
            InvalidFirstDukeException, ParseException, IOException {

        if (commandType.equals(CommandType.PRINTALLTASKS)) {
            printAllTask(tasks);
        } else if (commandType.equals(CommandType.EXITDUKE)) {
            ui.closeDuke();
        } else if (commandType.equals(CommandType.MARKASDONE)){
            int counter = Integer.parseInt(commandArr[1]);
            tasks.getTask(counter - 1).markAsDone();
            storage.saveToFile(tasks.tasks);
        } else if (commandType.equals(CommandType.DELETETASK)){
            int counter = Integer.parseInt(commandArr[1]);
            deleteTask(counter - 1, tasks);
            storage.saveToFile(tasks.tasks);
        } else if (commandType.equals(CommandType.ADDTODO)){
            tasks.addTask(createTodo());
            storage.saveToFile(tasks.tasks);
        } else if (commandType.equals(CommandType.ADDDEADLINE)) {
            tasks.addTask(createDeadline());
            storage.saveToFile(tasks.tasks);
        } else if (commandType.equals(CommandType.ADDEVENT)) {
            tasks.addTask(createEvent());
            storage.saveToFile(tasks.tasks);
        } else if (commandType.equals(CommandType.FINDTASK)) {
            String keyword = joinString(removeFirst(commandArr));
            ArrayList<Task> tempTasks = searchKeyWord(tasks, keyword);
            printSearchedTask(tempTasks);
        } else {}
    }

    /**
     * Return true if the command is "bye". Else false.
     *
     * @return boolean
     */
    public boolean isExit() {
        if (commandType.equals(CommandType.EXITDUKE)) {
            return true;
        } else {
            return false;
        }
    }

}
