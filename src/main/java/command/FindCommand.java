package command;

import exception.DukeException;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prints tasks that match the search keyword.
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        String output = "";
        for (int i = 1; i <= taskList.size(); i++) {
            if (taskList.get(i - 1).toString().contains(keyword)) {
                output = output + i + ". " + taskList.get(i - 1) + "\n";
            }
        }
        System.out.println(ui.LINE + "Here are your task that matches your search: \n" + output + ui.LINE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
