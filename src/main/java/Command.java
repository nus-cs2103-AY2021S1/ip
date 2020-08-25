public class Command {
    private CommandType commandType;
    private int taskNumber;
    private String description;
    private String dateTime;
    private boolean isInProgram = true;

    public Command(CommandType commandType){
        this.commandType = commandType;
    }

    public Command(CommandType commandType, int taskNumber){
        this.commandType = commandType;
        this.taskNumber = taskNumber;
    }

    public Command(CommandType commandType, String description){
        this.commandType = commandType;
        this.description = description;
    }

    public Command(CommandType commandType, String description, String dateTime){
        this.commandType = commandType;
        this.description = description;
        this.dateTime = dateTime;
    }

    private static final String line = "____________________________________________________________";
    public static final String exit_message = line +
            "\n Waddling off now. See you soon! \n" + line;

    public void execute(TaskList taskList) throws DukeException {
        if (commandType == CommandType.BYE) {
            System.out.println(exit_message);
            isInProgram = false;
        } else if (commandType == CommandType.LIST) {
            taskList.listStoredTasks();
        } else if (commandType == CommandType.DONE) {
            taskList.markTaskAsDone(taskNumber);
        } else if (commandType == CommandType.DELETE) {
            taskList.deleteTask(taskNumber);
        } else if (commandType == CommandType.TODO) {
            ToDo newTask = new ToDo(description);
            taskList.addTask(newTask);
        } else if (commandType == CommandType.DEADLINE) {
            Deadline newTask = new Deadline(description, dateTime);
            taskList.addTask(newTask);
        } else if (commandType == CommandType.EVENT) {
            Event newTask = new Event(description, dateTime);
            taskList.addTask(newTask);
        } else {
            throw new DukeException("Wrong command type");
        }
    }

    public boolean isInProgram() {
        return isInProgram;
    }
}
