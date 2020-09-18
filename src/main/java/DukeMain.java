public class DukeMain {

    public static void main(String[] args) {
        TaskList taskList = new TaskList("../savedata/data.txt");
        Ui.startUpMessage();
        taskList.runCommands();
        taskList.save();
    }
}