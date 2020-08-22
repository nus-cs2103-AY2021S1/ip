public class ListCommand extends Command {
    
    private static final String EMPTY_MSG = "Your list is empty!!!";
    private static final String SHOW_TASK = "Here are the tasks in your list:";
    
    ListCommand(String[] inputArr) {
        super(inputArr);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        showListTasks(tasks, ui);
    }

    // Printing out the items in the list
    private void showListTasks(TaskList tasks, Ui ui) {
        if (tasks.size() == 0) {
            ui.messageFormatter(() -> System.out.println(EMPTY_MSG));
        } else {
            ui.messageFormatter(() -> {
                System.out.println(SHOW_TASK);
                for(int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + ". " + tasks.get(i - 1));
                }
            });
        }
    }
}
