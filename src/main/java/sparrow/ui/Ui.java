package sparrow.ui;

import java.util.List;
import java.util.Scanner;

import sparrow.data.task.Task;
import sparrow.data.trivia.Vocabulary;


/**
 * Responsible for sending messages to the user.
 */
public class Ui {

    private static final String DIVIDER = "    ________________________________________";

    private static final String MESSAGE_PREFIX = "    ";

    /**
     * Welcomes the user to the program.
     */
    public void greet() {
        String welcome = "  _  _ _   ___ _                    \n"
                + " | || (_) |_ _( )_ __               \n"
                + " | __ | |  | ||/| '  \\              \n"
                + " |_||_|_| |___| |_|_|_|             \n"
                + " / __|_ __  __ _ _ _ _ _ _____ __ __\n"
                + " \\__ \\ '_ \\/ _` | '_| '_/ _ \\ V  V /\n"
                + " |___/ .__/\\__,_|_| |_| \\___/\\_/\\_/ \n"
                + "     |_|                            ";
        System.out.println(welcome);
        replyToUser("How can I help ye?");
    }


    /**
     * Sends a reply to the user in a standard format.
     * @param message Reply to be formatted.
     */
    public void replyToUser(String message) {
        System.out.println(DIVIDER);
        Scanner sc = new Scanner(message);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(MESSAGE_PREFIX + line);
        }
        System.out.println(DIVIDER);
        sc.close();
    }

    /**
     * Converts input task list to string.
     */
    public String taskListToString(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String temp = String.format("%d. %s\n", i + 1, tasks.get(i));
            sb.append(temp);
        }
        return sb.toString();
    }

    /**
     * Converts input task list to string.
     */
    public String vocabListToString(List<Vocabulary> vocabList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vocabList.size(); i++) {
            String temp = String.format("%d. %s\n", i + 1, vocabList.get(i));
            sb.append(temp);
        }
        return sb.toString();
    }

}
