import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static final String line = "------------------------------------------------------------------";
    private static final String tick = "✓";
    private static final String cross = "✗";

    //commands
    private static final String CMD_EXIT = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_DONE = "done";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String botName = "Duke";
        String userName = "You";

        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        Boolean running = true;

        ArrayList<String> list = new ArrayList<>();
        ArrayList<Boolean> check = new ArrayList<>();

        System.out.println("привет, меня зовут \n" + logo + "\n\n" +
                "Приветствую мою Родину, Мать Россию.\n" +
                "Что ты хочешь?\n" + line);

        while (running) {
            System.out.print(userName + " said: ");
            userInput = scanner.nextLine();

            //split command
            int space_idx = userInput.indexOf(' ');
            String command = space_idx  == -1 ? userInput : userInput.substring(0, space_idx);
            String rest = space_idx == -1 ? "" : userInput.substring(space_idx + 1);

            switch(command) {
                case CMD_EXIT:
                    running = false;
                    break;
                case CMD_LIST:
                    if (list.isEmpty()) {
                        System.out.println(fmtMsg("В списке нет ничего глупого."));
                    } else {
                        String msg = "Вот что в списке:";
                        for (int i = 0; i < list.size(); i++) {
                            msg += "\n" +(i + 1) + ". " + fmtTask(list.get(i), check.get(i));
                        }
                        System.out.println(fmtMsg(msg));
                    }
                    break;

                case CMD_DONE:
                    try {
                        int idx = Integer.parseInt(rest) - 1;
                        if (idx < 0 || idx >= list.size()) {
                            System.out.println(fmtMsg("Нет такой задачи, тупица."));
                        } else {
                            check.set(idx, true);
                            System.out.println(fmtMsg("Наконец-то натворил ты, ленивая задница.\n" +
                                                      "  " + fmtTask(list.get(idx), check.get(idx))));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(fmtMsg("Введите число после команды, тупица."));
                    }
                    break;

                default:
                    list.add(userInput);
                    check.add(false);
                    System.out.println(fmtMsg(botName + " said: " + "Я добавил " + userInput + " в список."));
            }
        }

        System.out.println(fmtMsg(botName + " said: Пока, надеюсь никогда больше не увидеть тебя!"));
    }

    public static String fmtMsg(String msg) {
        return line + "\n" + msg + "\n" + line;
    }

    public static String fmtTask(String task, boolean status) {
        return "[" + (status ? tick : cross) + "] " + task;
    }
}
