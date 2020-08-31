public class Parser {

    /** TaskList class that stores and deals with the tasks **/
    private TaskList taskList;

    /**
     *Class constructor
     *
     */
    public Parser(TaskList taskList){
        this.taskList = taskList;
    }

    /**
     * Parse the inputs and carry out the respective commands
     *
     * @param inputs the array of command and taskDescription obtain from user
     */
    public void ParseCommand(String[] inputs) {

        try {
            Commands command = GetCommand(inputs[0]);
            String taskDescription = "";
            if (inputs.length > 1) {
                taskDescription = inputs[1];
            } else if (inputs[0].equals("todo") || inputs[0].equals("deadline") || inputs[0].equals("event")) {
                throw new DukeException(String.format("The description of %s cannot be empty", command.toString()));
            }

            switch (command) {
            case DONE: {
                int index = Integer.parseInt(taskDescription) - 1;
                taskList.doneTask(index);
                break;
            }
            case LIST: {
                taskList.listTask();
                break;
            }
            case BYE: {
                System.out.println("Bye, Have a Great Time!");
                taskList.setTaskListNotUpdating();
                break;
            }
            case FIND: {
                taskList.findTask(taskDescription);
                break;
            }
            case TODO: {
                Task newTask = new Todo(taskDescription);
                taskList.addTask(newTask,true);
                break;
            }
            case DEADLINE: {
                Task newTask = Deadline.create(taskDescription);
                taskList.addTask(newTask,true);
                break;
            }
            case EVENT: {
                Task newTask = Event.create(taskDescription);
                taskList.addTask(newTask,true);
                break;
            }
            case DELETE: {
                int index = Integer.parseInt(taskDescription) - 1;
                taskList.deleteTask(index);
            }
            default: {
                throw new DukeException("smlj??????");
            }
            }
        } catch(DukeException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * convert string command into enum Command
     *
     * @param commandInput command from the user
     */
    public Commands GetCommand(String commandInput){
        Commands command;
        try{
            command = Commands.valueOf(commandInput.toUpperCase());
        } catch(IllegalArgumentException e) {
            command = Commands.UNKNOWN;
        }
        return command;
    }
}
