import java.util.ArrayList;

public class FindCommand extends Command {

    String searchWord;

    public FindCommand(String input) {
        assert input != null;

        String[] splitInput = input.split(" ");
        this.searchWord = splitInput[1].trim();
    }

    public String handle(String input, TaskManager taskManager, Storage fileHandler) {

        ArrayList<String> tasksFound = new ArrayList<>();
        for (int i = 0; i < taskManager.getTaskList().size(); i++) {
            String found = taskManager.getTaskList().get(i).toString();
            if (found.contains(this.searchWord)) {
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
