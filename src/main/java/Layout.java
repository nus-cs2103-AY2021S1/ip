package ip.src.main.java;

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
        System.out.println("\t" + s);
    }

}
