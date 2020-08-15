package main.java;


import java.util.ArrayList;
import java.util.Scanner;

public class Commands {
    private boolean shouldBreak = true;
    private ArrayList<Task> stringsArrayList = new ArrayList<>();

    private final String INPUT_LIST = "list";
    private final String INPUT_BLAH = "blah";
    private final String INPUT_BYE = "bye";

    public void start() {
        this.greet();

        Scanner scanner = new Scanner(System.in);

        String inputs = scanner.nextLine().toLowerCase().trim();

        while (shouldBreak) {
            switch (inputs) {
            case INPUT_LIST:
                this.lst();
                inputs = scanner.nextLine().toLowerCase().trim();
                break;
            case INPUT_BLAH:
                this.blah();
                inputs = scanner.nextLine().toLowerCase().trim();
                break;
            case INPUT_BYE:
                System.out.println("~ \n I’ll Be Back \n~ ");
                shouldBreak = !shouldBreak;
                break;
            default:
                stringsArrayList.add(new Task(inputs));
                System.out.println("~ \n added: " + inputs + "\n~");
                inputs = scanner.nextLine().toLowerCase().trim();
            }
        }
    }

    public void greet() {
        System.out.println("~ \n Hello I'm the Terminator \n What can I do for you? \n~");
    }

    public void lst() {
        System.out.println("~ \n Here are targets in your kill list: ");
        if (!stringsArrayList.isEmpty()) {
            for (int i = 0; i < stringsArrayList.size(); i++) {
                int count = i + 1;
                System.out.println(String.format(" %d.[%s] %s", count, stringsArrayList.get(i).getStatusIcon(),
                        stringsArrayList.get(i).getDescription()));
            }
        } else {
            System.out.println(" list");
        }
        System.out.println("\n~ ");
    }

    public void blah() {
        System.out.println("~ \n blah \n~");
    }
}
