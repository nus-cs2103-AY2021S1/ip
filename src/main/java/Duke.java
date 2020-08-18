package main.java;

public class Duke {

    private static void drawLine(char ch, int length) {
        System.out.println();
        for (int i = 0; i < length; i++) {
            System.out.print(ch);
        }
        System.out.println();
    }

    private static void greet() {
        char star = '*';
        int length = 8;

        drawLine(star, length);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        drawLine(star, length);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
    }
}
