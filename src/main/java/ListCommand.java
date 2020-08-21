package main.java;

public class ListCommand extends Command {

    @Override
    void excecute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList);
    }
}
