public class Parser {

    TaskList taskList;

    public Parser(TaskList taskList){
        this.taskList = taskList;
    }

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
                    taskList.DoneTask(index);
                    break;
                }
                case LIST: {
                    taskList.ListTask();
                    break;
                }
                case BYE: {
                    System.out.println("Bye, Have a Great Time!");
                    taskList.isUpdating = false;
                    break;
                }
                case TODO: {
                    Task newTask = new Todo(taskDescription);
                    taskList.AddTask(newTask,true);
                    break;
                }
                case DEADLINE: {
                    Task newTask = Deadline.create(taskDescription);
                    taskList.AddTask(newTask,true);
                    break;
                }
                case EVENT: {
                    Task newTask = Event.create(taskDescription);
                    taskList.AddTask(newTask,true);
                    break;
                }
                case DELETE: {
                    int index = Integer.parseInt(taskDescription) - 1;
                    taskList.DeleteTask(index);
                }
                default: {
                    throw new DukeException("smlj??????");
                }
            }
        }catch(DukeException e){
            System.out.println(e.getMessage());
        }
    }

    public Commands GetCommand(String commandInput){
        Commands command;
        try{
            command = Commands.valueOf(commandInput.toUpperCase());
        }catch(IllegalArgumentException e){
            command = Commands.UNKNOWN;
        }
        return command;
    }
}
