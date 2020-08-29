import java.util.ArrayList;

public class FindCommand extends Command {
    private final String TAB = "  ";
    private final String FIND_TITLE = TAB + " Here are the matching tasks in your list:";
    private String[] input;

    public FindCommand(String[] input) {
        super();
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FindException {
        ArrayList<Task> store = tasks.getTaskList();
        ArrayList<Task> filteredList = new ArrayList<>();

        if (input.length == 1) { //incomplete done command
            throw new FindException(" ☹ OOPS!!! The description of a find cannot be empty.");
        }
        
        String keyword = input[1];
        for (Task task : store) {
            String[] description = task.getDescription().split("\\s");
            boolean isFound = false;
            for (String word : description) {
                if(word.equals(keyword)) {
                    isFound = true;
                    break;
                }
            }
            if (isFound) {
                filteredList.add(task);
            }
        }
        
        if (filteredList.size() == 0) {
            throw new FindException(" ☹ OOPS!!! There is no task with this keyword.");
        }
        
        int i = 1;
        System.out.println(FIND_TITLE);
        for (Task task : filteredList) {
            System.out.println(TAB + " " + i++ + "." + task);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
