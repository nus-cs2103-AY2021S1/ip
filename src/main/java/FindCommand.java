import java.util.ArrayList;

/**
 * Represents a command that searches for tasks that match a keyword.
 */
public class FindCommand extends Command {

    String keyWord;

    public FindCommand(String input) {
        assert input != null;

        String[] splitInput = input.split(" ");
        this.keyWord = splitInput[1].trim();
    }

    /**
     * Finds all tasks that match the keyword.
     * @param input user input.
     * @param taskManager task manager that contains a list of tasks.
     * @param fileHandler saves input into a file.
     * @return String of the tasks that match the keyword.
     */
    public String handle(String input, TaskManager taskManager, Storage fileHandler) {

        ArrayList<String> tasksFound = new ArrayList<>();
        for (int i = 0; i < taskManager.getTaskList().size(); i++) {
            String found = taskManager.getTaskList().get(i).toString();
            if (found.contains(this.keyWord)) {
                tasksFound.add(found);
            }
        }

        //print tasks that match the search word
        if (tasksFound.size() == 0) {
            return "Sorry nothing matches :(";

        } else {
            String result = "";
            for (int j = 0; j < tasksFound.size(); j++) {
                result = result + (j + 1) + ". " + tasksFound.get(j) + "\n";
            }
            return "Here are the matching tasks in your list:\n" + result;
        }
    }
}
