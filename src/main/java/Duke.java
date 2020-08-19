import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static final String line = "------------------------------------------------------------------";

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

        ArrayList<Task> tasks = new ArrayList<>();

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
                    if (tasks.isEmpty()) {
                        System.out.println(fmtMsg("В списке нет ничего глупого."));
                    } else {
                        String msg = "Вот что в списке:";
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
                            System.out.println(fmtMsg("Нет такой задачи, тупица."));
                        } else {
                            tasks.get(idx).setDone(true);
                            System.out.println(fmtMsg("Наконец-то натворил ты, ленивая задница.\n" +
                                                      "  " + tasks.get(idx)));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(fmtMsg("Введите число после команды, тупица."));
                    }
                    break;

                default:
                    tasks.add(new Task(userInput));
                    System.out.println(fmtMsg(botName + " said: " + "Я добавил " + userInput + " в список."));
            }
        }

        System.out.println(fmtMsg(botName + " said: Пока, надеюсь никогда больше не увидеть тебя!"));
    }

    public static String fmtMsg(String msg) {
        return line + "\n" + msg + "\n" + line;
    }
}
