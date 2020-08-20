import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Message start = new Message("start");
        start.reply();

        while (sc.hasNext()) {
            Message msg = new Message(sc.next());
            msg.reply();
            if (msg.getCmd() == Command.BYE) {
                break;
            }
        }
    }
}
