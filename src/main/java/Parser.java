public class Parser {

    /** TaskList class that stores and deals with the tasks **/
    private TaskList taskList;

    /**
     *Class constructor
     *
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parse the inputs and carry out the respective commands
     *
     * @param inputs the array of command and taskDescription obtain from user
     */
    public String parseCommand(String[] inputs) {

        try {
            Commands command = getCommand(inputs[0]);
            String taskDescription = "";
            if (inputs.length > 1) {
                taskDescription = inputs[1];
            } else if (inputs[0].equals("todo") || inputs[0].equals("deadline") || inputs[0].equals("event")) {
                throw new DukeException(String.format("The description of %s cannot be empty", command.toString()));
            }

            switch (command) {
            case DONE: {
                int index = Integer.parseInt(taskDescription) - 1;
                taskList.getTask(index).complete();
                return taskList.getTask(index).toString();
            }
            case LIST: {
                return taskList.listTask();
            }
            case BYE: {
                taskList.setTaskListNotUpdating();
                return "Bye, Have a Great Time!";
            }
            case FIND: {
                return taskList.findTask(taskDescription);
            }
            case TODO: {
                Task newTask = new Todo(taskDescription);
                return taskList.addTask(newTask, true);
            }
            case DEADLINE: {
                Task newTask = Deadline.create(taskDescription);
                return taskList.addTask(newTask, true);
            }
            case EVENT: {
                Task newTask = Event.create(taskDescription);
                return taskList.addTask(newTask, true);
            }
            case DELETE: {
                int index = Integer.parseInt(taskDescription) - 1;
                return taskList.deleteTask(index);
            }
            default: {
                throw new DukeException("smlj??????");
            }
            }
        } catch (DukeException e) {
           return e.getMessage();
        }
    }


    /**
     * convert string command into enum Command
     *
     * @param commandInput command from the user
     */
    public Commands getCommand(String commandInput) {
        Commands command;
        try {
            command = Commands.valueOf(commandInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            command = Commands.UNKNOWN;
        }
        return command;
    }
}
