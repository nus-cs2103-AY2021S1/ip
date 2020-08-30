public class PrintsearchCommand extends Command {
    private String keyword;

    public PrintsearchCommand(String keyword) {
        this.keyword = keyword;
    }

    /**Calls the displayList function in the ui class which prints out a provided
     * list of task.
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        ui.displayList(TaskList.searchList(this.keyword),
                "Doge found the following tasks you asked for!");
    }
}
