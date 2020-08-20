import java.util.HashSet;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<String> list = new HashSet<>();

        Message start = new Message("start", list);
        start.reply();

        while (sc.hasNext()) {
            Message msg = new Message(sc.next(), list);
            msg.reply();
            if (msg.getCmd() == Command.BYE) {
                break;
            }
        }
    }
}
