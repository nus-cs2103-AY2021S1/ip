/*input
list
blah
bye
*/
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GreetExit greetExit = new GreetExit();
        greetExit.greet();
        while (in.hasNextLine()) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                greetExit.exit();
                break;
            }
            System.out.println(command);
        }
    }
}
