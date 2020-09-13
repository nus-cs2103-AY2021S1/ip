import java.time.LocalDate;
/**
 * A Parser class that handles user input.
 */
public class Parser {

    /**
     * Returns the String message from ui.
     * @param tasks The task list.
     * @param ui The UI handler.
     * @param uiInput The user input message.
     * @return the String from ui.
     * @throws DukeException different by case.
     */
    public String uiResponse(TaskList tasks, UI ui,String uiInput) {

        String response = "";
        String textMessage = uiInput;

        try {
            String[] keyword = textMessage.split(" ");
            switch (keyword[0]) {

                case "bye": {
                    response += "Bye. Hope to see you again soon!";
                    break;
                }

                case "find": {

                    response += tasks.findTask(keyword[1]);
                    break;
                }

                case "delete": {
                    int index = Integer.parseInt(keyword[1]) - 1;
                    response += tasks.deleteTask(index);
                    break;
                }

                case "todo": {

                    if (keyword.length == 1) {
                        throw new InvalidTodoException();
                    }
                    Todo newTodo = new Todo(textMessage.substring(5), false);
                    response += tasks.addTask(newTodo);
                    break;
                }


                case "deadline": {

                    if (keyword.length == 1) {
                        throw new InvalidDeadlineException();
                    }
                    try {
                        String[] tempString = textMessage.substring(9).split(" /by ");
                        Deadline newDeadline = new Deadline(tempString[0], false, LocalDate.parse(tempString[1]));
                        response += tasks.addTask(newDeadline);
                    } catch (Exception e) {
                        throw new InvalidFormatException();
                    }
                    break;

                }

                case "event": {

                    if (keyword.length == 1) {
                        throw new InvalidEventException();
                    }
                    try {
                        String[] tempString = textMessage.substring(7).split(" /at ");
                        Event newEvent = new Event(tempString[0], false, LocalDate.parse(tempString[1]));
                        response += tasks.addTask(newEvent);
                    } catch (Exception e) {
                        throw new InvalidFormatException();
                    }
                    break;

                }

                case "list": {
                    response += tasks.showList();
                    break;
                }

                case "stats": {
                    response += tasks.numberOfTaskCompleted();
                    break;
                }

                case "done": {
                    int index = Integer.parseInt(keyword[1]) - 1;
                    tasks.getTasks().get(index).markDone();
                    response += "Nice I've marked this tasks as done";
                    response += tasks.getTasks().get(index);
                    break;
                }

                case "hi": {
                    response += ui.greeting();
                    break;
                }

                default:
                    response += ui.inputInstruction();
                    throw new InvalidInputException();
            }


        } catch (DukeException e) {
            response += e.getMessage();
            ui.print(e.getMessage());
        }


        return response;
    }

}
