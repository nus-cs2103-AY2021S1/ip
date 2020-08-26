package duke.command;

import duke.TaskList;
import duke.enums.Message;
import duke.exception.DukeException;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindCommand implements Command {
    
    private final String searchTerm;
    
    public FindCommand(String userInput) {
        StringTokenizer st = new StringTokenizer(userInput);
        st.nextToken();
        StringBuilder searchTermBuilder = new StringBuilder();
        while (st.hasMoreTokens()) {
            searchTermBuilder.append(st.nextToken()).append(" ");
        }
        this.searchTerm = searchTermBuilder.toString().trim();
    }
    
    /**
     * Returns an appropriate matcher for that exact search term
     *
     * @param searchTerm User input search term
     *
     * @return Matcher based on the regex with boundaries
     */
    private Matcher getMatcher(String searchTerm) {
        Pattern p = Pattern.compile("\\b" + searchTerm.trim() + "\\b");
        return p.matcher("");
    }
    
    /**
     * Displays the tasks that match a specific word/phrase passed in
     *
     * @param tasks Current TaskList
     * @param ui    Where the User shall receive messages about the command
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        Matcher m = getMatcher(this.searchTerm);
        ArrayList<Task> allTasks = tasks.getAllTasks();
        ArrayList<String> lines = new ArrayList<>();
        lines.add(Message.FOUND_MSG.getMsg());
        AtomicInteger counter = new AtomicInteger();
        allTasks.stream()
                .filter(task -> this.containsExactWord(task.getDescription(), searchTerm))
                .forEach(foundTask -> lines.add(counter.incrementAndGet() + ". " + foundTask.toString()));
        if (lines.isEmpty()) {
            ui.displayError(Message.ERROR_NO_FIND.getMsg());
        } else {
            ui.display(lines);
        }
    }
    
    /**
     * Tests if a word/phrases is exactly present in a text
     *
     * @param text Text to search within
     * @param word The search term (word/phrase) to search for
     *
     * @return True if exact word is within the text
     */
    public boolean containsExactWord(String text, String word) {
        String regex = "\\b" + word + "\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher myMatcher = pattern.matcher(text);
        return myMatcher.find();
    }
    
    
    @Override
    public boolean isExit() {
        return false;
    }
}
