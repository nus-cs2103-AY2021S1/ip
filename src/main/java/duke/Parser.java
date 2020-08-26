package duke;

/**
 * Parser class to handle user inputs.
 */
public class Parser {

    /**
     * Categorise user input into different types of tasks to add.
     *
     * @param input User input.
     * @return A task to be added by the TaskList.
     * @throws InvalidDescriptionException In case the task description is empty.
     * @throws InvalidTypeException        In case the task type is not one of Event, Deadline, Todo.
     */
    public static Task handleInput(String input) throws InvalidDescriptionException, InvalidTypeException {
        String type = input.split(" ")[0];
        switch (type) {
            case "todo":
                if (input.substring(4).equals("") || input.substring(5).equals("")) {
                    throw new InvalidDescriptionException();
                }
                return new Todo(input.substring(5));
            case "deadline":
                String[] dl = input.split(" /by ");
                if (dl[0].substring(8).equals("") || dl[0].substring(9).equals("")) {
                    throw new InvalidDescriptionException();
                }
                return new Deadline(dl[0].substring(9), dl[1]);
            case "event":
                String[] e = input.split(" /at ");
                if (e[0].substring(5).equals("") || e[0].substring(6).equals("")) {
                    throw new InvalidDescriptionException();
                }
                return new Event(e[0].substring(6), e[1]);
            default:
                throw new InvalidTypeException();
        }
    }


    /**
     * Maps user input to actions the TaskList must carry out.
     *
     * @param input User input.
     * @param tl    TaskList to be used.
     */
    public static void allocate(String input, TaskList tl) {
        String[] arr = input.split(" ");
        int idx;

        switch (arr[0]) {
            case "bye":
                tl.bye();
                break;
            case "list":
                tl.display();
                break;
            case "find":
                try {
                    tl.find(input);
                } catch (InvalidDescriptionException e) {
                    Ui.addLine(e.toString());
                }
                break;
            case "done":
                idx = Integer.parseInt(arr[1]) - 1;
                try {
                    tl.completeTask(idx);
                } catch (InvalidIndexException e) {
                    Ui.addLine(e.toString());
                }
                break;
            case "delete":
                idx = Integer.parseInt(arr[1]) - 1;
                try {
                    tl.deleteTask(idx);
                } catch (InvalidIndexException e) {
                    Ui.addLine(e.toString());
                }
                break;
            default:
                try {
                    Task toAdd = handleInput(input);
                    tl.add(toAdd);
                } catch (InvalidDescriptionException e) {
                    Ui.addLine(e.toString());
                } catch (InvalidTypeException e) {
                    Ui.addLine(e.toString());
                }
        }
    }
}