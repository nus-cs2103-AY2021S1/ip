import java.time.LocalDate;
public class Parser {


    public String uiResponse(TaskList tasks, UI ui,String uiInput) {

        String response = "";
        String textMessage = uiInput;

            try {
                String[] keyword = textMessage.split(" ");

                if (textMessage.equals("bye")) {
                    response += "Bye. Hope to see you again soon!";
                }

                else if (keyword[0].equals("find")) {

                    response += tasks.findTask(keyword[1]);
                }

                else if (keyword[0].equals("delete")) {
                    int index = Integer.parseInt(keyword[1]) - 1;
                    response += tasks.deleteTask(index);
                }

                else if (keyword[0].equals("todo")) {

                    if(keyword.length == 1) {
                        throw new InvalidTodoException();
                    }
                    Todo newTodo = new Todo(textMessage.substring(5), false);
                    response += tasks.addTask(newTodo);
                }


                else if (keyword[0].equals("deadline")) {

                    if(keyword.length == 1) {
                        throw new InvalidDeadlineException();
                    }
                    try {
                        String[] tempString = textMessage.substring(9).split(" /by ");
                        Deadline newDeadline = new Deadline(tempString[0], false, LocalDate.parse(tempString[1]));
                        response += tasks.addTask(newDeadline);
                    } catch (Exception e){
                        throw new InvalidFormatException();
                    }

                }

                else if (keyword[0].equals("event")) {

                    if(keyword.length == 1) {
                        throw new InvalidEventException();
                    }
                    try {
                        String[] tempString = textMessage.substring(7).split(" /at ");
                        Event newEvent = new Event(tempString[0], false, LocalDate.parse(tempString[1]));
                        response += tasks.addTask(newEvent);
                    } catch (Exception e){
                        throw new InvalidFormatException();
                    }

                }

                else if (textMessage.equals("list")) {
                    response += tasks.showList();
                }

                else if (keyword[0].equals("done")) {
                    int index = Integer.parseInt(keyword[1]) - 1;
                    tasks.getTasks().get(index).markDone();
                    response += "Nice I've marked this tasks as done";
                    response += tasks.getTasks().get(index);
                }

                else {
                    throw new InvalidInputException();
                }

            }
            catch (DukeException e) {
                response += e.getMessage();
            }

        return response;
    }

}
