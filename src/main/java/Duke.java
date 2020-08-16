import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    private final String name;
    private boolean isRunning;
    private final List<Task> list;

    public Duke(String name) {
        this.name = name;
        this.isRunning = true;
        this.list = new ArrayList<>();
        sendMessage("Hello! " + this.name + " lives to serve :)");
    }

    public void exit() {
        isRunning = false;
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

    public void addToList(String type, String description) throws DukeException {
        Task task;
        String[] split;
        String desc;
        description = description.trim();
        if (description.isEmpty()) {
            throw new DukeException("Description cannot be empty!");
        }
        switch (type) {
            case "todo":
                task = new Todo(description);
                break;
            case "deadline":
                try {
                    split = description.split(" /by ");
                    desc = split[0];
                    String by = split[1];
                    task = new Deadline(desc, by);
                    break;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid deadline description!");
                }
            case "event":
                try {
                    split = description.split(" /at ");
                    desc = split[0];
                    String at = split[1];
                    task = new Event(desc, at);
                    break;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid event description!");
                }
            default:
                task = new Task(description);
                break;
        }
        list.add(task);
        String msg = "Okay I've added:\n    " + task + "\n";
        msg += String.format("Now you have %d tasks in the list.", list.size());
        sendMessage(msg);
    }

    public void markTaskDone(String si) throws DukeException {
        try {
            int i = Integer.parseInt(si);
            int index = i - 1;
            Task t = list.get(index);
            if (t.getDone()) {
                throw new DukeException("That task is already done!");
            } else {
                t.markAsDone();
                sendMessage("Okay I've marked this task as done:\n    " + t);
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry, I don't know what that means :(");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    public void deleteTask(String si) throws DukeException {
        try {
            int i = Integer.parseInt(si);
            int index = i - 1;
            Task task = list.get(index);
            list.remove(task);
            String msg = "Okay I've deleted:\n    " + task + "\n";
            msg += String.format("Now you have %d tasks in the list.", list.size());
            sendMessage(msg);
        } catch (NumberFormatException e) {
            throw new DukeException("Sorry, I don't know what that means :(");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    public void displayList() {
        if (list.isEmpty()) {
            sendMessage("Your list is empty!");
        } else {
            int num = 1;
            StringBuilder msg = new StringBuilder((list.size() > 1)
                    ? "Here are the tasks in your list:\n"
                    : "Here's your one and only task:\n");
            for (Task task: list) {
                msg.append(String.format("%d. %s", num, task));
                if (num < list.size()) {
                    msg.append("\n");
                }
                num++;
            }
            sendMessage(msg.toString());
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (isRunning) {
            String input = sc.nextLine();
            String[] inputSplit = input.split(" ");
            String command = inputSplit[0];
            boolean doDefault = false;

            switch(command.toLowerCase()) {
                case "bye":
                    exit();
                    break;
                case "list":
                    displayList();
                    break;
                case "done":
                    try {
                        String si = inputSplit[1];
                        markTaskDone(si);
                        break;
                    } catch (DukeException e) {
                        sendMessage(e.getMessage());
                        break;
                    }
                case "todo":
                case "deadline":
                case "event":
                    try {
                        String[] descArr = Arrays.copyOfRange(inputSplit,
                                1, inputSplit.length);
                        String desc = String.join(" ", descArr);
                        addToList(command, desc);
                        break;
                    } catch (DukeException e) {
                        sendMessage(e.getMessage());
                        break;
                    }
                case "delete":
                    try {
                        String si = inputSplit[1];
                        deleteTask(si);
                        break;
                    } catch (DukeException e) {
                        sendMessage(e.getMessage());
                        break;
                    }
                default:
                    sendMessage(
                            new DukeException("Sorry, I don't know what that means :(")
                            .getMessage());
                    break;
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        String lvl = "6";
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
        duke.run();
    }
}