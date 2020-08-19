import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;

public class Sparrow {
    public static ArrayList<String> dataArr = new ArrayList<String>();

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (handle(command)) {
            command = sc.nextLine();
        }
        sc.close();
    }

    public static boolean handle(String command) {
        switch (command) {
            case "bye":
                reply("Bye. Hope t' see ye again soon!");
                return false;
            case "list":
                displayList();
                return true;
            default:
                add(command);
                return true;
        }
    }

    public static void greet() {
        String welcome = "  _  _ _   ___ _                    \n" +
                " | || (_) |_ _( )_ __               \n" +
                " | __ | |  | ||/| '  \\              \n" +
                " |_||_|_| |___| |_|_|_|             \n" +
                " / __|_ __  __ _ _ _ _ _ _____ __ __\n" +
                " \\__ \\ '_ \\/ _` | '_| '_/ _ \\ V  V /\n" +
                " |___/ .__/\\__,_|_| |_| \\___/\\_/\\_/ \n" +
                "     |_|                            ";
        System.out.println(welcome);
        reply("How can I help ye?");
    }

    public static void reply(String message) {
        System.out.println("    ________________________________________");
        Scanner sc = new Scanner(message);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println("      " + line);
        }
        System.out.println("    ________________________________________");
        sc.close();
    }

    public static void add(String data) {
        dataArr.add(data);
        reply("added: " + data);
    }

    public static void displayList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dataArr.size(); i++) {
            String temp = String.format("%d. %s\n", i+1, dataArr.get(i));
            sb.append(temp);
        }
        reply(sb.toString());
    }

}
