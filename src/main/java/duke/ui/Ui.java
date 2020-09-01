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

    public Ui(String botName, String userName) {
        this();
        this.botName = botName;
        this.userName = userName;
    }

    public void showLoadedTasks(TaskList taskList) {
        System.out.println("\nTASKS LOADED FROM DATA FILE!!");
        System.out.println(taskList);
        this.showLine();
    }

    public void showWelcome() {
        System.out.println("\nOh hai kittehs! I r lolcatus. reziztents is fu... fut...\n"
                + this.LOGO + "\n\n" + "reziztents dun werk.\n" + this.LINE);
    }

    public String readCommand() {
        System.out.print(this.userName + " sed: ");
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println(this.LINE);
    }

    public void showError(String e) {
        System.out.println(this.fmtMsg(botName + " HAZ FINDED ERRRR!\n" + "=> " + e));
    }

    public void display(String msg) {
        System.out.println(this.fmtMsg(msg));
    }

    public void showLoadingError(String e) {
        System.out.println(this.fmtMsg("CANNOT LOAD TASKS FROM FILE uwu owo \n" + "  " + e));
    }

    public String fmtMsg(String msg) {
        return this.botName + " sed: " + msg;
    }




}
