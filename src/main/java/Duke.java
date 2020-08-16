import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private String name;
    private boolean running;
    private List<Task> list;

    public Duke(String name) {
        this.name = name;
        this.running = true;
        this.list = new ArrayList<>();
        sendMessage("Hello! " + this.name + " lives to serve :)");
    }

    public boolean isRunning() {
        return running;
    }

    public void genericError() {
        sendMessage("Whoops something went wrong hehe...");
    }

    public void exit() {
        running = false;
        sendMessage("Bye :(");
    }

    public void sendMessage(String message) {
        // Pads all messages with 4-spaces indentation
        String line = "    ________________________________________________________";
        StringBuilder sb = new StringBuilder(message);
        int offset = 0;
        while (true) {
            int nextIndex = sb.indexOf("\n", offset);
            if (nextIndex != -1) {
                sb.insert(nextIndex + 1, "    ");
                offset = nextIndex + 5;
            } else {
                break;
            }
        }
        System.out.println(line + "\n    " + sb + "\n" + line);
    }

    public void addToList(String description) {
        Task task = new Task(description);
        if (list.add(task)) {
            sendMessage("Added: " + description);
        } else {
            genericError();
        }
    }

    public void markTaskDone(int i) {
        if (i < 1 || i > list.size()) {
            genericError();
            return;
        }
        int index = i - 1;
        Task t = list.get(index);
        if (t.getDone()) {
            sendMessage("That task is already done!");
        } else {
            t.markAsDone();
            sendMessage("Okay I have marked this task as done:\n    " + t);
        }
    }

    public void displayList() {
        if (list.isEmpty()) {
            sendMessage("Your list is empty!");
        } else {
            int num = 1;
            String msg = (list.size() > 1)
                    ? "Here are the tasks in your list:\n"
                    : "Here's your one and only task:\n";
            for (Task task: list) {
                msg += String.format("%d. %s", num, task);
                if (num < list.size()) {
                    msg += "\n";
                }
                num++;
            }
            sendMessage(msg);
        }
    }

    public static void main(String[] args) {
        String lvl = "3";
        String logo = "                                                 _     _\n"
                + " _______  _______  _______  _______  __   __    (c).-.(c)\n"
                + "|       ||       ||  _    ||  _    ||  | |  |    / ._. \\ \n"
                + "|_     _||    ___|| |_|   || |_|   ||  |_|  |  __\\( Y )/__\n"
                + "  |   |  |   |___ |       ||       ||       | (_.-/'-'\\-._)\n"
                + "  |   |  |    ___||  _   | |  _   | |_     _|    || " + lvl + " || \n"
                + "  |   |  |   |___ | |_|   || |_|   |  |   |    _.' `-' '._\n"
                + "  |___|  |_______||_______||_______|  |___|   (.-./`-'\\.-.)\n"
                + "                                               `-'     `-'\n"
                + "Level: " + lvl;
        System.out.println(logo);

        Duke duke = new Duke("Tebby");
        Scanner sc = new Scanner(System.in);

        while (duke.isRunning()) {
            String input = sc.nextLine();
            String[] inputSplit = input.split(" ");
            boolean doDefault = false;

            switch(inputSplit[0].toLowerCase()) {
                case "bye":
                    duke.exit();
                    break;
                case "list":
                    duke.displayList();
                    break;
                case "done":
                    try {
                        int i = Integer.valueOf(inputSplit[1]);
                        duke.markTaskDone(i);
                        break;
                    } catch (ArrayIndexOutOfBoundsException
                            | NumberFormatException e) {
                        doDefault = true;
                        break;
                    }
                default:
                    duke.addToList(input);
                    break;
            }
            if (doDefault) {
                duke.addToList(input);
            }
        }

        sc.close();
    }
}
