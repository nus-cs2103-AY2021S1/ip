package duke;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Ui duke = new Ui();
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
