public class AddCommand extends Command {
    public String commandWord;
    public TaskList taskList;
    public String filePath;
    public String secondWord;

    public AddCommand(TaskList taskList, String filePath, String firstWord, String secondWord) {
        super(firstWord);
        this.commandWord = firstWord;
        this.taskList = taskList;
        this.filePath = filePath;
        this.secondWord = secondWord;
    }

    @Override
    public String execute() {
        Task t = new Task("");
        String[] array;
        String s = "";
        switch (commandWord) {
            case "todo":
                t = new ToDo(secondWord);
                break;

            case "deadline":
                array = secondWord.split("/by ");
                if (array.length == 1) {
                    return Ui.missingDeadline();
                } else {
                    t = new Deadline(array[0], array[1]);
                }
                break;

            case "event":
                array = secondWord.split("/at ");
                if (array.length == 1) {
                    return Ui.missingEventTime();
                } else {
                    t = new Event(array[0], array[1]);
                }
                break;

            default:
                break;
        }
        if (t.description != "") {
            taskList.addTask(t);
            Storage.updateTasks(taskList.getNoOfTasks() , taskList.list, filePath);
            return Ui.addSuccessful(t, taskList.getNoOfTasks());
        } else {
            return s;
        }
    }
}

