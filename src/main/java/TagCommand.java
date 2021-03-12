public class TagCommand extends Command {
    public String commandWord = "tag";
    public TaskList taskList;
    public String filePath;
    public String secondWord;

    public TagCommand(TaskList taskList, String filePath, String secondWord) {
        super("list");
        this.taskList = taskList;
        this.filePath = filePath;
        this.secondWord = secondWord;
    }

    @Override
    public String execute() {
        String[] p = secondWord.split(" ");
        if (p.length !=2 ) {
            return Ui.unknownCommand();
        }
        int task = Integer.parseInt(p[0]); // get the task number
        if (task > taskList.noOfTasks) {
            return Ui.uncreatedTask(); // task has not been created
        } else {
            assert (taskList.list.get(task - 1) != null) : "Incorrect index.";
            Task cur = taskList.list.get(task - 1);
            String tag = p[1];
            cur.setTag(tag);
            Storage.updateTasks(taskList.getNoOfTasks(), taskList.list, filePath);
            return Ui.setTagSuccessful(tag,cur);
        }
    }


}
