import java.util.Arrays;

public class Command {

    private Ui ui;
    private Parser parser;
    private boolean terminated;

    Command() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.terminated = false;
    }

    public void execute(String userInput, TaskList tasks, Storage storage) throws DukeException {
        //determining user input type via the first word
        String[] splitInput = splitUserInput(userInput);
        String keyWord = getKeyWord(userInput);

        if (keyWord.equals("bye")) {
            byeCommand();
            terminated = true;
        } else if (keyWord.equals("help")) {
            helpCommand();
        } else if (keyWord.equals("list")) {
            listCommand(tasks);
        } else if (keyWord.equals("done")) {
            doneCommand(splitInput, tasks, storage);
        } else if (keyWord.equals("delete")) {
            deleteCommand(tasks, splitInput, ui, storage);
        } else if (keyWord.equals("todo") || keyWord.equals("deadline") || keyWord.equals("event")){
            addTaskCommand(splitInput, keyWord, tasks, storage);
        } else {
            throw new DukeException("Unknown execution error.");
        }
    }

    public boolean isTerminated() {
        return this.terminated;
    }

    public String[] splitUserInput(String userInput) {
        return userInput.split(" ");
    }

    public void welcomeCommand() {
        ui.showWelcome();
    }

    public void listCommand(TaskList tasks) {
        if (tasks.noOfTasks() == 0) {
            ui.showNoPastTasks();
        } else {
            ui.showPastTasks(tasks);
        }
    }

    public void byeCommand() {
        ui.showGoodbye();
    }

    public void helpCommand() {
        ui.showHelp();
    }

    public void deleteCommand(TaskList tasks, String[] splitInput, Ui ui, Storage storage) throws DeleteFailureException {
        tasks.deleteTask(tasks, splitInput, ui);
        storage.updateTaskFile(tasks);
    }

    public void doneCommand(String[] splitInput, TaskList tasks, Storage storage) throws DukeException {
        //checks the formatting of user input
        if (splitInput.length > 2) {
            throw new DukeException("Please use the correct format: done <task number>");
        }
        tasks.doneTask(splitInput, tasks, storage, ui);
        storage.updateTaskFile(tasks);
    }

    public void addTaskCommand(String[] splitInput, String keyWord, TaskList tasks, Storage storage) throws DukeException {
        String[] data = processUserInput(splitInput);
        tasks.addTask(data, keyWord, tasks, storage, ui);
        storage.updateTaskFile(tasks);
    }

    public String getKeyWord(String input) throws InvalidKeyWordException {
        String[] splitInput = splitUserInput(input);
        if (parser.isValidKeyWord(splitInput[0])) {
            return splitInput[0];
        } else {
            throw new InvalidKeyWordException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    //returns an array with index 0:description, index 1:date (if applicable), index 2:time (if applicable)
    public static String[] processUserInput(String[] array) throws DukeException {
        Parser parser = new Parser();
        if (array.length <= 1) {
            if (array[0].equals("event")) {
                throw new EmptyTaskException("☹ OOPS!!! The description of a event cannot be empty.");
            } else if (array[0].equals("deadline")) {
                throw new EmptyTaskException("☹ OOPS!!! The description of a deadline cannot be empty.");
            } else if (array[0].equals("todo")) {
                throw new EmptyTaskException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                throw new EmptyTaskException("Unknown error!");
            }
        } else if (array[0].equals("event")) {
            String des = "";
            String date = "";
            String time = "";

            boolean toBreak = false;
            int pivotIndex = 0;
            for (int i = 1; i < array.length; i++) {
                if (array[i].equals("/at")) {
                    //sets the breaking point of input
                    toBreak = true;
                    pivotIndex = i;
                    break;
                } else {
                    //before breaking point
                    des += array[i] + " ";
                }
            }
            String[] dateTimeArr = Arrays.copyOfRange(array, pivotIndex + 1, array.length);
            //after breaking point, there should only be a maximum of 2 items remaining
            //first item is date, second item is time
            if (dateTimeArr.length > 2) {
                throw new DukeException("Please enter a valid format");
            } else {
                if (dateTimeArr.length == 1) {
                    //only has date but no time
                    date = dateTimeArr[0];
                } else if (dateTimeArr.length == 2) {
                    //has date and time
                    date = dateTimeArr[0];
                    time = dateTimeArr[1];
                }
            }
            //index 0 is description, index 1 is date, index 2 is time
            if (toBreak && parser.isValidDate(date) && parser.isValidTime(time)) {
                return new String[]{des, date, time};
            } else {
                //exception is thrown when the format is off, since there is no breaking point, or
                //if the input date or time is wrong
                throw new DukeException("Please use the correct format and include the keyword: /at");
            }
        } else if (array[0].equals("deadline")) {
            String des = "";
            String date = "";
            String time = "";

            boolean toBreak = false;
            int pivotIndex = 0;
            for (int i = 1; i < array.length; i++) {
                if (array[i].equals("/by")) {
                    //sets the breaking point of input
                    toBreak = true;
                    pivotIndex = i;
                    break;
                } else {
                    //before breaking point
                    des += array[i] + " ";
                }
            }
            String[] dateTimeArr = Arrays.copyOfRange(array, pivotIndex + 1, array.length);
            //after breaking point, there should only be a maximum of 2 items remaining
            //first item is date, second item is time
            if (dateTimeArr.length > 2) {
                throw new DukeException("Please enter a valid format");
            } else {
                if (dateTimeArr.length == 1) {
                    //only has date but no time
                    date = dateTimeArr[0];
                } else if (dateTimeArr.length == 2) {
                    //has date and time
                    date = dateTimeArr[0];
                    time = dateTimeArr[1];
                }
            }
            //index 0 is description, index 1 is date, index 2 is time
            if (toBreak && parser.isValidDate(date) && parser.isValidTime(time)) {
                return new String[]{des, date, time};
            } else {
                //exception is thrown when the format is off, since there is no breaking point, or
                //if the input date or time is wrong
                throw new DukeException("Please use the correct format and include the keyword: /by");
            }
        } else if (array[0].equals("todo")) {
            String des = "";
            for (int i = 1; i < array.length; i++) {
                des += array[i];
                if (i != array.length - 1) {
                    //adds a white space in between each word
                    des += " ";
                }
            }
            //index 0 is description
            return new String[]{des};
        }
        return new String[]{};
    }
}
