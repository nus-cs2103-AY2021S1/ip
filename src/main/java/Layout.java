package ip.src.main.java;

import java.util.ArrayList;

public class Layout {
    String line = "\t";

    public void printLine() {
        line = "\t";
        for (int i = 0; i < 50; i++) {
            line += "\u2500";
        }
        System.out.println(line);
    }

    public void print(String s) {
        printLine();
        System.out.println("\t" + s);
        printLine();
    }

    public void printList(ArrayList<String> arr) {
        if (arr.size() != 0) {
            printLine();
            for (int i = 0; i < arr.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + arr.get(i));
            }
            printLine();
        }
    }

}
