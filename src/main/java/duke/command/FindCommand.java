package duke.command;

import duke.main.TaskList;
import duke.task.Task;

public class FindCommand extends Command {

    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void perform(TaskList tasks) {
        TaskList temp = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String description = t.getInfo()[1];
            if (containSameWord(keyword, description)) {
                System.out.println(keyword + " " + description);
                temp.add(t);
            }
        }
        if (temp.size() > 0) {
            System.out.println(" Here are the matching tasks in your list:");
            for (int i = 0; i < temp.size(); i++) {
                Task t = temp.get(i);
                System.out.println(" " + (i + 1) + "." + t.toString());
            }
        } else {
            System.out.println(" There are no tasks that match the keyword.");
        }
    }

    // String 'a' must be a single word
    public boolean containSameWord(String a, String b) {
        boolean hasSameWord = false;
        String[] stringArr = b.split(" ");
        for (String s : stringArr) {
            //System.out.println(s + " " + a);
            if (s.equals(a)) {
                hasSameWord = true;
                break;
            }
        }
        return hasSameWord;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
