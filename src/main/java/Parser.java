public class Parser {

    private static TaskList listOfContent;

    /**
     * Returns a boolean to indicate whether program should exit.
     * @param input user input.
     * @return Returns a boolean.
     */
    public static boolean exit(String input) {
        return input.equals("bye");
    }

    /**
     * initiate taskList for Parser.
     * @param list TaskList that manages tasks.
     */
    public static void initiateTaskList(TaskList list) {
        listOfContent = list;
    }
    protected static String processDoneCommand(String input) throws InvalidException {

        String[] isDone = input.split(" ");
        if (isDone.length == 1) {
            throw new InvalidDoneException("OOPS!!! please provide me with the task to be marked as done");
        }
        if (isDone.length > 2) {
            throw new InvalidDoneException("OOPS!!! I can only mark one task as done at a time");
        }
        String lastChar = isDone[isDone.length - 1];
        int index = Integer.parseInt(lastChar);
        if (index > listOfContent.getSizeOfList()) {
            int noOfTasks = listOfContent.getSizeOfList();
            throw new InvalidException("There are only " + noOfTasks
                    + " tasks in the list; Please restate the task to be mark as done");
        }
        if (index <= 0) {
            throw new InvalidException("Please choose a task to mark as done, the index start from 1");
        }
        Task current = listOfContent.getTheList().get(index - 1);
        current.setTaskAsDone();
        return current.timeConverted();
    }
    protected static String processDeleteCommand(String input) throws InvalidException {

        String[] isDone = input.split(" ");
        if (isDone.length == 1) {
            throw new InvalidDeleteException("OOPS!!! please provide me with the task to be deleted");
        }

        if (isDone.length > 2) {
            throw new InvalidDeleteException("OOPS!!! I can only delete one task at a time");
        }

        String lastChar = isDone[isDone.length - 1];
        int index = Integer.parseInt(lastChar);
        if (index > listOfContent.getSizeOfList()) {
            int noOfTasks = listOfContent.getSizeOfList();
            throw new InvalidException("There are only " + noOfTasks
                    + " tasks in the list; Please restate the task to be deleted");
        }
        if (index <= 0) {
            throw new InvalidException("Please choose a task to delete, the index start from 1");
        }
        Task toBeRemove = listOfContent.getTheList().get(index - 1);
        int noOfTasksLeft = listOfContent.getSizeOfList();
        return listOfContent.removeTask(index - 1);
    }
    protected static String processTodoCommand(String input) throws InvalidException {
        String[] isDone = input.split(" ");
        String firstChar = isDone[0];
        if (isDone.length == 1) {
            throw new InvalidTodoException("OOPS!!! The description of a todo cannot be empty."
                    + "please provide me with the task to be completed");
        }
        ToDo newTask = new ToDo(input.substring(firstChar.length() + 1));
        listOfContent.addTask(newTask);
        return listOfContent.addStringTask(newTask);
    }
    protected static String processDeadlineCommand(String input) throws InvalidException {
        String[] isDone = input.split(" ");
        String firstChar = isDone[0];
        if (isDone.length == 1) {
            throw new InvalidDeadlineException("OOPS!!! The description of a task cannot be empty."
                    + "please provide me with the name and duration of the task to be completed");
        }
        if (input.split("/by").length < 2) {
            throw new InvalidDeadlineException("OOPS!!! please provide me with the"
                    + " duration of the task to be completed");
        } else {
            int timeLength = isDone[isDone.length - 1].length();
            int dateLength = isDone[isDone.length - 2].length();
            if (timeLength < 4 || dateLength < 10) {
                throw new InvalidDeadlineException("Fail to add task :( . "
                        + "Please check the time and date format again. "
                        + "The correct format should be dd/mm/yyyy tttt. Eg: 02/08/2019 1800");
            }
        }
        int index = input.indexOf("/by");
        String task = input.substring(firstChar.length() + 1, index);
        String time = input.substring(index + 4);
        Deadline deadline = new Deadline(task, time);
        listOfContent.addTask(deadline);
        return listOfContent.addStringTask(deadline);
    }
    protected static String processEventCommand(String input) throws InvalidException {
        String[] isDone = input.split(" ");
        String firstChar = isDone[0];
        if (isDone.length == 1) {
            throw new InvalidDeadlineException("OOPS!!! The description of a task cannot be empty."
                    + "please provide me with the name and time of the task");
        }
        if (input.split("/at").length < 2) {
            throw new InvalidDeadlineException("OOPS!!! please provide me with the"
                    + " time of the task to be completed");
        } else {
            int timeLength = isDone[isDone.length - 1].length();
            int dateLength = isDone[isDone.length - 2].length();
            if (timeLength < 4 || dateLength < 10) {
                throw new InvalidDeadlineException("Fail to add task :( . "
                        + "Please check the time and date format again. "
                        + "The correct format should be dd/mm/yyyy tttt. Eg: 02/08/2019 1800");
            }
        }
        int index = input.indexOf("/at");
        String task = input.substring(firstChar.length() + 1, index);
        String duration = input.substring(index + 4);
        Task event = new Event(task, duration);
        listOfContent.addTask(event);
        return listOfContent.addStringTask(event);
    }
    protected static String processHelpCommand() {
        String toDoCommand = "Use TODO to add a new task. Format todo [taskName], Eg: todo ip \n";
        String deleteCommand = "\n Use DELETE a new task. Format delete [taskIndex], Eg: delete 5 \n";
        String findCommand = "\n Use FIND to find a new task. Format find [taskName], Eg: find ip \n";
        String eventCommand = "\n Use EVENT to add a new task with date and time. "
                + " Format event [taskName] /at dd/mm/yyyy tttt , Eg: event ip /at 02/08/2019 0800 \n";
        String deadlineCommand = "\n Use DEADLINE to add a new task with date and time. "
                + " Format deadline [taskName] /by dd/mm/yyyy tttt , Eg: deadline ip /at 02/08/2019 0800 \n";
        String listCommand = "\n use LIST to find the lists of task. Format: list. Eg: list \n";
        String doneCommand = "\n Use DONE to mark a task as done. Format: done [taskIndex], Eg: done 5 \n";
        String byeCommand = "\n Use BYE to exit from the application. Format: bye. Eg: bye \n";
        String ending = "\n Please use the above mentioned command, other command are not supported :( ";
        return toDoCommand + deleteCommand
                + findCommand
                + eventCommand + deadlineCommand + listCommand + doneCommand + byeCommand + ending;
    }

    /**
     * Processes input and produce the require output according to the command.
     * @param input user input.
     * @return the output after receiving the command.
     * @throws InvalidException InvalidException if input is illegal.
     */
    public static String processInput(String input) throws InvalidException {
        String[] isDone = input.split(" ");
        String firstChar = isDone[0];

        if (input.equals("bye")) {
            Storage.write(listOfContent);
            return Ui.exitMessage();
        }
        if (input.equals("help")) {
            return processHelpCommand();
        }
        if (input.equals("list")) {
            return listOfContent.showAllContent();
        } else if (firstChar.equals("find")) {
            String keyword = isDone[isDone.length - 1];
            return listOfContent.findKeyword(keyword);
        } else if (firstChar.equals("done")) {
            return processDoneCommand(input);
        } else if (firstChar.equals("delete")) {
            return processDeleteCommand(input);
        } else {
            if (firstChar.equals("todo")) {
                return processTodoCommand(input);
            } else if (firstChar.equals("deadline")) {
                return processDeadlineCommand(input);
            } else if (firstChar.equals("event")) {
                return processEventCommand(input);
            } else {
                throw new InvalidException("OOPS!!! I'm sorry, but I don't know what that means :-( "
                       + "You can you \"help\" command to find the list of command to use");
            }
        }
    }
}

