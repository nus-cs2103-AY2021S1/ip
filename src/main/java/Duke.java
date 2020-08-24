public class Duke {

    public static void main(String[] args) throws DukeException {
        Tasks tasks = Storage.read();
        UI ui = new UI();
        ui.welcome(tasks);
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
