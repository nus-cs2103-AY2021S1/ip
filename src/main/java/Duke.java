import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

public class Duke {

    enum CMD {
        BYE,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DONE,
        DELETE,
        DEFAULT
    }

    private static final String logo =
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
    private static final String botName = "Duke";
    private static final String userName = "You";

    private static final String line = "-------------------------------------------------------------------------------";

    private static final Path dataPath = Paths.get("data");
    private static final String taskLogName = "tasks.txt";

    public static void main(String[] args) {
        System.out.println("Tasks will be saved to: " + dataPath.toAbsolutePath());


        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        Boolean running = true;

        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Oh hai kittehs! I r lolcatus. reziztents is fu... fut...\n"
                            + logo + "\n\n" + "reziztents dun werk.\n" + line);

        while (running) {
            System.out.print(userName + " sed: ");
            userInput = scanner.nextLine();
            try {
                //split command
                int space_idx = userInput.indexOf(' ');
                CMD command;

                try {
                    command = CMD.valueOf((space_idx  == -1 ? userInput : userInput.substring(0, space_idx)).toUpperCase());
                } catch (IllegalArgumentException e) {
                    command = CMD.DEFAULT;
                }
                String rest = space_idx == -1 ? "" : userInput.substring(space_idx + 1).trim();

                String item, dateStr;
                int dateStrIdx;

                switch(command) {
                    case BYE:
                        running = false;
                        break;

                    case TODO:
                        tasks.add(new Todo(rest));
                        if (rest.isEmpty()) {
                            throw new DukeException("ME FINKZ DAT U NED 2 ENTR NAYM 4 UR TODO ITEM LULZ");
                        } else {
                            System.out.println(fmtMsg("I PUT NEW TING IN DA LIST\n  " + tasks.get(tasks.size() - 1) +
                                    "\nNAO U HAS " +  tasks.size() + " FINGS IN DA LIST LULZIES"));
                            saveTasksToFile(tasks);
                        }

                        break;

                    case DEADLINE:
                        if (rest.isEmpty()) {
                            throw new DukeException("ME FINKZ DAT U NED 2 ENTR NAYM 4 UR DEDLINE ITEM LULZ");
                        } else {
                            dateStrIdx = rest.indexOf("/by");
                            if (dateStrIdx == -1) {
                                throw new DukeException("ME FINKZ U NED 2 GIV DATE 4 TIEM 4 DA DEDLINE USIN /by");
                            } else {
                                item = rest.substring(0, dateStrIdx).trim();
                                dateStr = rest.substring(dateStrIdx + 3).trim();

                                if (item.isEmpty()) {
                                    throw new DukeException("ME FINKZ U NED 2 GIV DA DEDLINE A NAEM");
                                } else if (dateStr.isEmpty()) {
                                    throw new DukeException("ME FINKZ U NED 2 PUT SUMTHIN 4 DA DATE OR TIEM");
                                } else {
                                    tasks.add(new Deadline(item, dateStr));
                                    System.out.println(fmtMsg("I PUT NEW TING IN DA LIST\n  " + tasks.get(tasks.size() - 1) +
                                            "\nNAO U HAS " +  tasks.size() + " FINGS IN DA LIST LULZIES"));
                                    saveTasksToFile(tasks);
                                }
                            }
                        }
                        break;

                    case EVENT:
                        dateStrIdx = rest.indexOf("/at");

                        if (rest.isEmpty()) {
                            throw new DukeException("ME FINKZ DAT U NED 2 ENTR NAYM 4 UR EVENT ITEM LULZ");
                        } else {
                            dateStrIdx = rest.indexOf("/at");
                            if (dateStrIdx == -1) {
                                throw new DukeException("ME FINKZ U NED 2 GIV DATE 4 TIEM 4 DA EVENT USIN /at");
                            } else {
                                item = rest.substring(0, dateStrIdx).trim();
                                dateStr = rest.substring(dateStrIdx + 3).trim();

                                if (item.isEmpty()) {
                                    throw new DukeException("ME FINKZ U NED 2 GIV DA EVENT A NAEM");
                                } else if (dateStr.isEmpty()) {
                                    throw new DukeException("ME FINKZ U NED 2 PUT SUMTHIN 4 DA DATE OR TIEM");
                                } else {
                                    tasks.add(new Event(item, dateStr));
                                    System.out.println(fmtMsg("I PUT NEW TING IN DA LIST\n  " + tasks.get(tasks.size() - 1) +
                                            "\nNAO U HAS " +  tasks.size() + " FINGS IN DA LIST LULZIES"));
                                    saveTasksToFile(tasks);
                                }
                            }
                        }

                        break;

                    case LIST:
                        if (tasks.isEmpty()) {
                            System.out.println(fmtMsg("UR LIST HAZ NUTHIN LOLOL"));
                        } else {
                            dateStrIdx = rest.indexOf("/by");
                            if (dateStrIdx == -1) {
                                String msg = "U HAS DEES TINGS IN UR LIST.";
                                for (int i = 0; i < tasks.size(); i++) {
                                    msg += "\n" +(i + 1) + ". " + tasks.get(i);
                                }
                                System.out.println(fmtMsg(msg));
                            } else {
                                dateStr = rest.substring(dateStrIdx + 3).trim();
                                if (DateTimeUtility.checkDateTimeType(dateStr) == DateTimeFormat.String) {
                                    throw new DukeException("U NID 2 GIV CORRECT DATE FOMAT!");
                                } else {
                                    String msg = "U HAS DEES TINGS IN UR LIST DAT R DUE/HAPPENIN BY "
                                                 + DateTimeUtility.formatString(dateStr) + ": ";

                                    for (int i = 0; i < tasks.size(); i++) {
                                        if (tasks.get(i) instanceof TimedTask) {
                                            try {
                                                if (DateTimeUtility.compare(dateStr,
                                                        ((TimedTask) tasks.get(i)).getByString()) >= 0) {
                                                    msg += "\n" + (i + 1) + ". " + tasks.get(i);
                                                }
                                            } catch (DateTimeException e) {}
                                        }
                                    }
                                    System.out.println(fmtMsg(msg));

                                }




                            }




                        }
                        break;

                    case DONE:
                        try {
                            int idx = Integer.parseInt(rest) - 1;
                            if (idx < 0 || idx >= tasks.size()) {
                                throw new DukeException("U DOAN HAS TASK WIF DIS LABEL");
                            } else {
                                tasks.get(idx).setDone(true);
                                System.out.println(fmtMsg("TASK IZ NAO DUNZ!!!!1!11!\n" +
                                        "  " + tasks.get(idx)));
                                saveTasksToFile(tasks);
                            }
                        } catch (NumberFormatException e) {
                            throw new DukeException("U MUST ONLY PUT INDEX OV TASK LULS");
                        }
                        break;

                    case DELETE:
                        try {
                            int idx = Integer.parseInt(rest) - 1;
                            if (tasks.isEmpty()) {
                                throw new DukeException("U CANT DELET ANYTHIN COZ U HAS NO TASKZ NAO LOLOL");
                            }
                            if (idx < 0 || idx >= tasks.size()) {
                                throw new DukeException("U DOAN HAS TASK WIF DIS LABEL");
                            } else {
                                System.out.println(fmtMsg("TASK IZ NAO DELETZ!!!!1!11!\n" +
                                        "  " + tasks.get(idx) + "\nNAO U HAS " +  (tasks.size() - 1) + " FINGS IN DA LIST LULZIES"));
                                tasks.remove(idx);
                                saveTasksToFile(tasks);
                            }
                        } catch (NumberFormatException e) {
                            throw new DukeException("U MUST ONLY PUT INDEX OV TASK LULS");
                        }
                        break;

                    case DEFAULT:
                        System.out.println(fmtMsg("NOTD WIF THX."));
                        break;
                }

            } catch (DukeException e) {
                System.out.println(fmtMsg(botName + " HAZ FINDED ERRRR!\n" + "=> " + e));
            }

        }

        System.out.println(fmtMsg("OKAIS I IZ GOIN 2 NOM BYEEEEE C U !!!1!1!!"));
    }

    private static void saveTasksToFile(ArrayList tasks) throws DukeException {
        //save tasks
        File dataDir = dataPath.toAbsolutePath().toFile();
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(dataPath.toString(), taskLogName))) {
            LocalDateTime now = LocalDateTime.now();
            String msg = "Task list (Last updated "
                         + now.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm:ss a"))
                         + "):";
            for (int i = 0; i < tasks.size(); i++) {
                msg += "\n" +(i + 1) + ". " + tasks.get(i);
            }
            writer.write(msg);
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    public static String fmtMsg(String msg) {
        return line + "\n" + botName + " sed: " + msg + "\n" + line;
    }
}
