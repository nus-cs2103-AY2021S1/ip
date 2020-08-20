import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static final String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String botName = "Duke";
    private static final String userName = "You";

    private static final String line = "------------------------------------------------------------------";

    //commands
    private static final String CMD_EXIT = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_DONE = "done";

    private static final String CMD_TODO = "todo";
    private static final String CMD_DEADLINE = "deadline";
    private static final String CMD_EVENT = "event";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        Boolean running = true;

        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hi, my nmae is\n" + logo + "\n\n" +
                "How can I hlep you taody?\n" + line);

        while (running) {
            System.out.print(userName + " said: ");
            userInput = scanner.nextLine();

            //split command
            int space_idx = userInput.indexOf(' ');
            String command = space_idx  == -1 ? userInput : userInput.substring(0, space_idx);
            String rest = space_idx == -1 ? "" : userInput.substring(space_idx + 1).trim();

            int date_idx;

            switch(command) {
                case CMD_EXIT:
                    running = false;
                    break;

                case CMD_TODO:
                    tasks.add(new Todo(rest));
                    System.out.println(fmtMsg("I hvae adedd a new tsak:\n  " + tasks.get(tasks.size() - 1) +
                                              "\nYou now hvae " +  tasks.size() + " tskas in yuor lsit."));
                    break;

                case CMD_DEADLINE:
                    date_idx = rest.indexOf("/by");
                    if (date_idx == -1) {
                        System.out.println(fmtMsg("You need to sepifcy a dtae or tmie."));
                    } else {
                        tasks.add(new Deadline(rest.substring(0, date_idx).trim(),
                                rest.substring(date_idx + 3).trim()));
                        System.out.println(fmtMsg("I hvae adedd a new tsak:\n  " + tasks.get(tasks.size() - 1) +
                                "\nYou now hvae " +  tasks.size() + " tskas in yuor lsit."));
                    }

                    break;

                case CMD_EVENT:
                    date_idx = rest.indexOf("/at");
                    if (date_idx == -1) {
                        System.out.println(fmtMsg("You need to sepifcy a dtae or tmie."));
                    } else {
                        tasks.add(new Event(rest.substring(0, date_idx).trim(),
                                rest.substring(date_idx + 3).trim()));
                        System.out.println(fmtMsg("I hvae adedd a new tsak:\n  " + tasks.get(tasks.size() - 1) +
                                "\nYou now hvae " +  tasks.size() + " tskas in yuor lsit."));
                    }
                    break;

                case CMD_LIST:
                    if (tasks.isEmpty()) {
                        System.out.println(fmtMsg("You dno't hvae any tsaks ctrunelry."));
                    } else {
                        String msg = "\nHree are yuor tkass:";
                        for (int i = 0; i < tasks.size(); i++) {
                            msg += "\n" +(i + 1) + ". " + tasks.get(i);
                        }
                        System.out.println(fmtMsg(msg));
                    }
                    break;

                case CMD_DONE:
                    try {
                        int idx = Integer.parseInt(rest) - 1;
                        if (idx < 0 || idx >= tasks.size()) {
                            System.out.println(fmtMsg("Yuo dno't hvae a tsak wtih tihs lbeal."));
                        } else {
                            tasks.get(idx).setDone(true);
                            System.out.println(fmtMsg("Mkread tsak as cteopmle.\n" +
                                                      "  " + tasks.get(idx)));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(fmtMsg("Psaele sfiepcy the iednx of the tsak you cpmolteed."));
                    }
                    break;

                default:
                    System.out.println(fmtMsg("Nteod wtih tnakhs."));
            }
        }

        System.out.println(fmtMsg("Bye, hpoe to nveer see you aiagn."));
    }

    public static String fmtMsg(String msg) {
        return line + "\n" + botName + " said: " + msg + "\n" + line;
    }
}
