import java.util.Scanner;

public class Level1 {

    private static String top = "----------Bot made by Hendey Fan----------\n";
    private static String bottom = "\n----------Bot made by Hendey Fan----------\n";
    private static String startMessage = "Hello, I'm a chat bot made by Hendey Fan.\n" +
            "What can this magnificent bot do for you?";
    private static String endMessage = "         Bye! Bot has closed.";

    public static void main() {
        Scanner sc = new Scanner(System.in);
        boolean bye = false;

        normalPrint(startMessage);

        while (!bye) {
            String next = sc.next();
            if (next.equals("bye")) {
                bye = true;
                normalPrint(endMessage);
            } else {
                normalPrint(next);
            }
        }
        sc.close();
    }

    private static void normalPrint(String middle) {
        System.out.println(top + middle + bottom);
    }
}
