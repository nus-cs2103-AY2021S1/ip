import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Store store = new Store();

        String[] welcomeTexts = new String[]{
                "Hello, my name is ",
                " ____        _        ",
                "|  _ \\ _   _| | _____ ",
                "| | | | | | | |/ / _ \\",
                "| |_| | |_| |   <  __/",
                "|____/ \\__,_|_|\\_\\___|",
                "How may I help you?"
        };

        StringUtils.printWithWrapper(welcomeTexts, false);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            processInput(input, store);
            input = sc.nextLine();
        }

        StringUtils.printWithWrapper(new String[]{"Bye bye! Hope to see you again soon!"}, false);
    }

    private static void processInput(String input, Store store) {
        String[] splitInput = input.split(" ");
        String firstInput = splitInput[0];
        String[] sections;

        switch (firstInput) {
            case "done":
                store.markTaskAsDone(Integer.parseInt(splitInput[1]));
                return;
            case "todo":
                sections = parseInputSections(input, firstInput, null);
                store.add(sections, firstInput);
                return;
            case "deadline":
                sections = parseInputSections(input, firstInput, "/by");
                store.add(sections, firstInput);
                return;
            case "event":
                sections = parseInputSections(input, firstInput, "/at");
                store.add(sections, firstInput);
                return;
        }

        switch (input) {
            case "list":
                store.list();
                break;
            default:
                store.add(new String[]{input}, "");
        }
    }

    private static String[] parseInputSections(String input, String breakPt1, String breakPt2) {
        int size = 2;
        if (breakPt2 == null) {
            size = 1;
        }
        String[] res = new String[size];

        String[] splitByBP1 = input.split(breakPt1 + " ");
        if (size == 1) {
            res[0] = splitByBP1[1];
        } else {
            String[] splitByBP2 = splitByBP1[1].split(" " + breakPt2 + " ");
            res[0] = splitByBP2[0];
            res[1] = splitByBP2[1];
        }
        return res;
    }
}
