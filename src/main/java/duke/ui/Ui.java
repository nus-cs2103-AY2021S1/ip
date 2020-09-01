package duke.ui;

import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private static final String LOGO =
            "       ,\n" +
                    "       \\`-._           __\n" +
                    "        \\\\  `-..____,.'  `.\n" +
                    "         :`.         /    \\`.\n" +
                    "         :  )       :      : \\\n" +
                    "          ;'        '   ;  |  :\n" +
                    "          )..      .. .:.`.;  :\n" +
                    "         /::...  .:::...   ` ;\n" +
                    "         ; _ '    __        /:\\\n" +
                    "         `:o>   /\\o_>      ;:. `.\n" +
                    "        `-`.__ ;   __..--- /:.   \\\n" +
                    "        === \\_/   ;=====_.':.     ;\n" +
                    "         ,/'`--'...`--....        ;\n" +
                    "              ;                    ;\n" +
                    "            .'                      ;\n" +
                    "          .'                        ;\n" +
                    "        .'     ..     ,      .       ;\n" +
                    "       :       ::..  /      ;::.     |\n" +
                    "      /      `.;::.  |       ;:..    ;\n" +
                    "     :         |:.   :       ;:.    ;\n" +
                    "     :         ::     ;:..   |.    ;\n" +
                    "      :       :;      :::....|     |\n" +
                    "      /\\     ,/ \\      ;:::::;     ;\n" +
                    "    .:. \\:..|    :     ; '.--|     ;\n" +
                    "   ::.  :''  `-.,,;     ;'   ;     ;\n" +
                    ".-'. _.'\\      / `;      \\,__:      \\\n" +
                    "`---'    `----'   ;      /    \\,.,,,/\n" +
                    "                   `----`              ";

    private static final String LINE = "-------------------------------------------------------------------------------";

    private String botName = "duke.Duke";
    private String userName = "You";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Overloaded constructor for specifying name of the bot and user.
     *
     * @param botName
     * @param userName
     */
    public Ui(String botName, String userName) {
        this();
        this.botName = botName;
        this.userName = userName;
    }

    /**
     * Prints the tasks currently existing in the taskList that is loaded from
     * a log file.
     *
     * @param taskList
     */
    public void showLoadedTasks(TaskList taskList) {
        System.out.println("\nTASKS LOADED FROM DATA FILE!!");
        System.out.println(taskList);
        this.showLine();
    }

    /**
     * Prints a welcome message for the chat application.
     *
     */
    public void showWelcome() {
        System.out.println("\nOh hai kittehs! I r lolcatus. reziztents is fu... fut...\n"
                + this.LOGO + "\n\n" + "reziztents dun werk.\n" + this.LINE);
    }

    /**
     * Prompts the user to enter a command and returns a string for the user's input.
     *
     * @return
     */
    public String readCommand() {
        System.out.print(this.userName + " sed: ");
        return scanner.nextLine();
    }

    /**
     * Prints a horizontal line.
     */
    public void showLine() {
        System.out.println(this.LINE);
    }

    /**
     * Formats and prints an error in the chat application.
     *
     * @param e
     */
    public void showError(String e) {
        System.out.println(this.fmtMsg(botName + " HAZ FINDED ERRRR!\n" + "=> " + e));
    }

    /**
     * Formats and displays desired output to the chat application.
     *
     * @param msg
     */
    public void display(String msg) {
        System.out.println(this.fmtMsg(msg));
    }

    /**
     * Displays error when tasks cannot be read from log file.
     *
     * @param e
     */
    public void showLoadingError(String e) {
        System.out.println(this.fmtMsg("CANNOT LOAD TASKS FROM FILE uwu owo \n" + "  " + e));
    }

    /**
     * Formats messages to be sent to the chat application.
     *
     * @param msg
     * @return
     */
    public String fmtMsg(String msg) {
        return this.botName + " sed: " + msg;
    }




}
