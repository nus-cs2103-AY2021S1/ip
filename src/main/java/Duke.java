import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        Message start = new Message("start", list);
        try {
            start.reply();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        while (sc.hasNext()) {
            Message msg = new Message(sc.nextLine(), list);
            try {
                msg.reply();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            if (msg.getCmd() == Command.BYE) {
                break;
            }
        }
    }
}
