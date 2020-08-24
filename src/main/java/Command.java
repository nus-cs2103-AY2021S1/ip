import java.io.IOException;

public class Command {
    CommandType commandType;
    String[] commandArr;

    public Command(CommandType commandType, String[] commandArr) {
        this.commandType = commandType;
        this.commandArr = commandArr;
    }

    public void printAllTask(TaskList tasks) {
        int numTask = 0;
        System.out.println("Here are the tasks in your list:");
        while (numTask < tasks.size()) {
            System.out.println(Integer.valueOf(numTask + 1) + "." + tasks.getTask(numTask));
            numTask++;
        }
    }

    public void deleteTask(int pos, TaskList tasks) {
        System.out.println("Noted. I've removed this task: \n" + tasks.getTask(pos) +
                "\n" + "Now you have " + Integer.valueOf(tasks.size() - 1) + " tasks in the list.");
        tasks.removeTask(pos);
    }

    public static String[] removeFirst(String[] arr) {
        String[] tempArr = new String[arr.length];
        for (int i = 0; i < arr.length - 1; i++) {
            tempArr[i] = arr[i + 1];
        }
        return tempArr;
    }

    public static String[] removeAfterWord(String[] arr, String word) {
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

    public static String[] keepAfterWord(String[] arr, String word) {
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

    public static String joinString(String[] arr) {
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

    public Task createTodo() throws InvalidTodoDescripDukeException {
        if (commandArr.length == 1) {
            throw new InvalidTodoDescripDukeException();
        }
        String[] modifiedCommand = removeFirst(commandArr);
        Task newTask = new Todo(joinString(modifiedCommand));
        return newTask;
    }

    public Task createDeadline() throws InvalidDeadlineDescripDukeException {
        if (commandArr.length == 1) {
            throw new InvalidDeadlineDescripDukeException();
        }
        String[] modifiedCommand = removeFirst(commandArr);
        String[] upper = removeAfterWord(modifiedCommand, "/by");
        String[] lower = keepAfterWord(modifiedCommand, "/by");
        Task newTask = new Deadline(joinString(upper), joinString(lower));
        return newTask;
    }

    public Task createEvent() throws InvalidEventDescripDukeException {
        if (commandArr.length == 1) {
            throw new InvalidEventDescripDukeException();
        }
        String[] modifiedCommand = removeFirst(commandArr);
        String[] upper = removeAfterWord(modifiedCommand, "/at");
        String[] lower = keepAfterWord(modifiedCommand, "/at");
        Task newTask = new Event(joinString(upper), joinString(lower));
        return newTask;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTodoDescripDukeException,
            InvalidDeadlineDescripDukeException, InvalidEventDescripDukeException, IOException,
            InvalidFirstDukeException {
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
        } else {}
    }

    public boolean isExit() {
        if (commandType.equals(CommandType.EXITDUKE)) {
            // close scanner
            return true;
        } else {
            return false;
        }
    }
}
