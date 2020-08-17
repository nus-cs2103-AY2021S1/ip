import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        DK duke = new DK();
        duke.start();
        Scanner sc = new Scanner(System.in);
        int x = 1;
        while (x > 0) {
            if (sc.hasNext()) {
                x = duke.takeCommand(sc.nextLine());
            } else {

            }
        }
    }

}
