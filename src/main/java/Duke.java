public class Duke {

    public static void main(String[] args) throws DukeException {
        UI ui = new UI();
        ui.welcome();
        Tasks tasks = Storage.read();
        String input = ui.getInput();
        Parser parser = new Parser(tasks);
        while (!input.equals("bye")) {
            try {
                if (input.isEmpty()) {
                    input = ui.getInput();
                    continue;
                }
                parser.parse(input);
            } catch (DukeException e) {
                UI.print(e.getMessage() + "\n");
            }

            input = ui.getInput();
        }
        ui.bye();
    }

}
