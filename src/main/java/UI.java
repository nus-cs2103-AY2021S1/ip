import java.util.ArrayList;

public class UI {

    public void printFormattedMessage(String errorMessage) {
        System.out.println(errorMessage);

    }

    public String printByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String printTaskAdd(Task task, int sizeStore) {
        return "Got it. I've added this task: \n"  + task.toString() + "\n"
                + "Now you have " + String.valueOf(sizeStore) + " tasks in the list.\n";
    }

    public String printDeleteMessage(Task task, int sizeStore) {
        return "Noted. I've removed this task:\n" + task.toString() + "\n"
                + "Now you have " + String.valueOf(sizeStore) + " tasks in the list.\n";
    }

    public String printListOfTasks(ArrayList<Task> tasks) {
        String message = "Here are the tasks in your list:\n";
        int sizeStore = tasks.size();
        for (int i = 1; i < sizeStore + 1; i++) {
            message += String.valueOf(i) + "." + tasks.get(i - 1).toString() + "\n";
        }
        return message;
    }

    public static String printMarkAsDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString() + "\n";

    }

    public String printKeywordTasks(String substring, ArrayList<Task> stringStore) throws DukeNoMatchesExcpetion {
        String message = "Here are the matching tasks in your list:\n";
        try {
            ArrayList<Task> keywordTasks = new ArrayList<>();

            for (int i = 0; i < stringStore.size(); i++) {
                if (stringStore.get(i).containsKeyword(substring)) {
                    keywordTasks.add(stringStore.get(i));
                }
            }
            if (keywordTasks.size() == 0) {
                throw new DukeNoMatchesExcpetion("");
            }
            int sizeStore = keywordTasks.size();
            for (int i = 1; i < sizeStore + 1; i++) {
                message += String.valueOf(i) + "." + keywordTasks.get(i - 1).toString() + "\n";
            }
        } catch (DukeNoMatchesExcpetion e) {
            message = e.getMessage();
        }
        return message;
    }
}
