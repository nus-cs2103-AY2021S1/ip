import java.util.Scanner;

public class Ui {

    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = "                                                 _     _\n"
                + " _______  _______  _______  _______  __   __    (c).-.(c)\n"
                + "|       ||       ||  _    ||  _    ||  | |  |    / ._. \\ \n"
                + "|_     _||    ___|| |_|   || |_|   ||  |_|  |  __\\( Y )/__\n"
                + "  |   |  |   |___ |       ||       ||       | (_.-/'-'\\-._)\n"
                + "  |   |  |    ___||  _   | |  _   | |_     _|    ||   || \n"
                + "  |   |  |   |___ | |_|   || |_|   |  |   |    _.' `-' '._\n"
                + "  |___|  |_______||_______||_______|  |___|   (.-./`-'\\.-.)\n"
                + "                                               `-'     `-'\n"
                + "By: Andy Wu";
        System.out.println(logo);
        sendMessage("Hello! Tebby lives to serve :)");
    }

    public void showExit() {
        sendMessage("Have a good day! Tebby logging off...");
    }

    public void sendMessage(String message) {
        String line = "    --------------------------------------------------------";
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

    public String readCommand() {
        System.out.print(">> ");
        return sc.nextLine();
    }

    public String getTaskReportMessage(int size) {
        String task = size == 1 ? "task" : "tasks";
        String num = size == 0 ? "no" : String.valueOf(size);
        return "Now you have " + num + " " + task + " in the list!";
    }

    public String getSuccessMessage(String type, Task task) {
        StringBuilder sb = new StringBuilder("Okay I've ");
        switch(type) {
        case "add":
            sb.append("added:");
            break;
        case "remove":
            sb.append("removed:");
            break;
        case "done":
            sb.append("marked this task as done:");
        }
        sb.append(String.format("\n    %s", task));
        return sb.toString();
    }

}
