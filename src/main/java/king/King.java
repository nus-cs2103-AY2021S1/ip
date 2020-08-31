/**
 * Runs the King program.
 * King is an individual project under cs2103t in the
 * National University of Singapore.
 *
 * @author Jun Siang.
 */
package king;

import java.util.Scanner;

import tasks.TaskList;

public class King {

    private final Storage storage;
    private final Parser parser;
    private TaskList taskList;

    /**
     * Creates the asset needed for the King program in the given file path.
     *
     * @param filepath file path to create the asset file.
     */
    King(String filepath) {
        storage = new Storage(filepath);
        taskList = new TaskList();
        taskList.addAll(storage.load());
        this.parser = new Parser(storage, taskList);
    }

    /**
     * Main to execute the program
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(UI.welcome());
        King king = new King("data/king.txt");
        king.chat();
    }

    /**
     * Run the King program. King replies to the user.
     */
    public void chat() {
        Scanner scanner = new Scanner(System.in);
        String phrase;
        while (scanner.hasNextLine() && !(phrase = scanner.nextLine()).equals("bye")) {
            try {
                System.out.println(parser.parse(phrase));
            } catch (KingException e) {
                System.out.println(UI.chatBox(e.message));
            }
        }
        System.out.print(UI.chatBox("Bye! Hope to see you again soon."));
        scanner.close();
    }
}
