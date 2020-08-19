import java.util.Scanner;

public class Bot {
    public Bot() {
    }

    String line = "____________________________________________________________";

    public void serve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                return;
            } else {
                System.out.println(line + "\n" + s + "\n" + line);

            }


        }
    }


}
