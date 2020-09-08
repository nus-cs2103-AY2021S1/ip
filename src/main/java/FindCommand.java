import java.util.ArrayList;

public class FindCommand extends IOHandler {

    String searchWord;

    public FindCommand(String input) {
        String[] splitInput = input.split(" ");
        this.searchWord = splitInput[1];
    }

    public String handleIO(String input, TaskManager taskManager, FileHandler fileHandler) {

        ArrayList<String> tasksFound = new ArrayList<>();
        for (int i = 0; i < taskManager.getTasksList().size(); i++) {
            String found = taskManager.getTasksList().get(i).toString();
            if (found.contains(this.searchWord)) {
                tasksFound.add(found);
            }
        }

        //print tasks that match the search word
        if (tasksFound.size() == 0) {
            return "Nothing matches :(";

        } else {
            String result = "";
            for (int j = 0; j < tasksFound.size(); j++) {
                result = result + (j + 1) + ". " + tasksFound.get(j) + "\n";
            }
            return "Here are the matching tasks in your list:\n" + result;
        }
    }
}
