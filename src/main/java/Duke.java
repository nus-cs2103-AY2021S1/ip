import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    static String newLine = System.lineSeparator();

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in); // Creates a new scanner object
        ArrayList<String> itemsLs = new ArrayList<>();

        //Intro
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String toPrint = myObj.nextLine();

        while(!toPrint.equalsIgnoreCase("bye")) {
            if (toPrint.equalsIgnoreCase("list")) {
                int i = 1;
                itemsLs.forEach(n -> System.out.println(itemsLs.indexOf(n) + 1 + ". " + n));
                toPrint = myObj.nextLine();
            } else {
                itemsLs.add(toPrint);
                System.out.println("added:" + toPrint);
                toPrint = myObj.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
