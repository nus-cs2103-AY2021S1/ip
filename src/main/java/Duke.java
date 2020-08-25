package main.java;

public class Duke {

    public static void run() {
        TaskList taskList = new TaskList();
        Ui.start();
        try {
            taskList.initialize();
        } catch (InvalidArgumentException e) {
            Ui.wrap(() -> System.out.println("Error parsing file"));
        }
        String input = "";

        do {
            try {
                input = Ui.feed();
                Commands.create(Parser.parseCommand(input)).run(taskList);
            } catch (UserException e) {
                Ui.wrap(() -> System.out.println(e.getMessage()));
            } catch (Exception e) {
                System.out.println("Unhandled exception:");
                System.out.println(e.getMessage());
            }
        }
        while (!input.equals("bye"));
        Ui.close();
    }

    public static void main(String[] args) {
        run();
    }
}











