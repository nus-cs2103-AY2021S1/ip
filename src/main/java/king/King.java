/**
 * King is an individual project under cs2103t in the
 * National University of Singapore.
 *
 * @author Jun Siang.
 */
package king;

import java.util.Scanner;

import parser.Parser;
import tasks.TaskList;
import ui.UI;

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
     * Returns a response from King given an input
     * from the user.
     *
     * @param input input from the user.
     * @see Parser
     */
    public String getResponse(String input) {
        try {
            return parser.parse(input);
        } catch (KingException e) {
            return UI.kingChatBox(e.message);
        }
    }

    /**
     * Run the King program. King replies to the user.
     * Used to run the King Program on the terminal.
     *
     * @deprecated Deprecated method since JavaFx was implemented.
     */
    @Deprecated
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String phrase;
        while (scanner.hasNextLine() && !(phrase = scanner.nextLine()).equals("bye")) {
            System.out.println(getResponse(phrase));
        }
        System.out.print(UI.kingChatBox("Bye! Hope to see you again soon."));
        scanner.close();
    }

}
