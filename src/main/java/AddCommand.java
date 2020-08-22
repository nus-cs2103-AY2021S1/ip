public class AddCommand extends Command {
    
    private static final String ADDED_NOTIFICATION = "Got it. I've added this task:";
    
    AddCommand(String[] inputArr) {
        super(inputArr);
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) 
            throws InvalidFormatDeadlineException, InvalidFormatEventException, InvalidFormatDateException {
        addTask(inputArr[0], inputArr[1], ui, tasks);
    }

    // adding the task into the list
    private void addTask(String type, String message, Ui ui, TaskList tasks)
            throws InvalidFormatDeadlineException, InvalidFormatEventException, InvalidFormatDateException {
        Task task;
        String[] dateTime;
        if (Parser.isTODO(type)) {
            task = new ToDo(message);
        } else if (Parser.isDeadline(type)) {
            dateTime = message.split(" /by ", 2);
            // checking if the input is valid
            if (dateTime.length == 1) {
                throw new InvalidFormatDeadlineException();
            }
            task = new Deadline(dateTime[0], Parser.formatDateTime(dateTime[1]));
        } else if (Parser.isEvent(type)){
            dateTime = message.split(" /at ", 2);
            // checking if the input is valid
            if (dateTime.length == 1) {
                throw new InvalidFormatEventException();
            }
            task = new Event(dateTime[0], Parser.formatDateTime(dateTime[1]));
        } else {
            return;
        }
        tasks.add(task);
        ui.messageFormatter(() -> {
            System.out.println(ADDED_NOTIFICATION);
            System.out.println(task);
            printNumTask(tasks);
        });
    }
}
