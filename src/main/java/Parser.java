import java.time.LocalDate;
import java.util.Scanner;
public class Parser {

    public void run(TaskList tasks, UI ui) {
        Scanner sc = new Scanner(System.in);
        String textMessage = sc.nextLine();

        while (true) {
            try {
                String[] keyPhrase = textMessage.split("/");
                String[] keyword = keyPhrase[0].split(" ");

                if (textMessage.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }

                if (keyword[0].equals("find")) {
                    System.out.println(tasks.findTask(keyword[1]));
                }

                if (keyword[0].equals("delete")) {
                    int index = Integer.parseInt(keyword[1]) - 1;
                    System.out.println(tasks.deleteTask(index));
                }

                if (keyword[0].equals("todo")) {

                    if(keyPhrase.length == 1) {
                        throw new InvalidTodoException();
                    }
                    Todo newTodo = new Todo(textMessage.substring(5), false);
                    System.out.println(tasks.addTask(newTodo));
                }


                if (keyword[0].equals("deadline")) {

                    if(keyPhrase.length == 1) {
                        throw new InvalidDeadlineException();
                    }

                    String[] tempString = textMessage.substring(9).split(" /by");
                    Deadline newDeadline = new Deadline(tempString[0], false, LocalDate.parse(tempString[1]));

                    System.out.println(tasks.addTask(newDeadline));

                }

                if (keyword[0].equals("event")) {

                    if(keyPhrase.length == 1) {
                        throw new InvalidEventException();
                    }
                    String[] tempString = textMessage.substring(7).split(" /at");
                    Event newEvent = new Event(tempString[0], false,  LocalDate.parse(tempString[1]));
                    System.out.println(tasks.addTask(newEvent));

                }

                if (textMessage.equals("list")) {
                    System.out.println(tasks.showList());
                }

                if (keyword[0].equals("done")) {
                    int index = Integer.parseInt(keyword[1]) - 1;
                    tasks.getTasks().get(index).markDone();
                    System.out.println("Nice I've marked this tasks as done");
                    System.out.println(tasks.getTasks().get(index));
                }

                else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }


            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            textMessage = sc.nextLine();
        }


    }


    public String uiResponse(TaskList tasks, UI ui,String uiInput) {

        String response = "";
        String textMessage = uiInput;

            try {
                String[] keyPhrase = textMessage.split("/");
                String[] keyword = keyPhrase[0].split(" ");

                if (textMessage.equals("bye")) {
                    response += "Bye. Hope to see you again soon!";
                }

                if (keyword[0].equals("find")) {

                    response += tasks.findTask(keyword[1]);
                }

                if (keyword[0].equals("delete")) {
                    int index = Integer.parseInt(keyword[1]) - 1;
                    response += tasks.deleteTask(index);
                }

                if (keyword[0].equals("todo")) {

                    if(keyPhrase.length == 1) {
                        throw new InvalidTodoException();
                    }
                    Todo newTodo = new Todo(textMessage.substring(5), false);
                    response += tasks.addTask(newTodo);
                }


                if (keyword[0].equals("deadline")) {

                    if(keyPhrase.length == 1) {
                        throw new InvalidDeadlineException();
                    }

                    String[] tempString = textMessage.substring(9).split(" /by");
                    Deadline newDeadline = new Deadline(tempString[0], false, LocalDate.parse(tempString[1]));

                    response += tasks.addTask(newDeadline);

                }

                if (keyword[0].equals("event")) {

                    if(keyPhrase.length == 1) {
                        throw new InvalidEventException();
                    }
                    String[] tempString = textMessage.substring(7).split(" /at");
                    Event newEvent = new Event(tempString[0], false,  LocalDate.parse(tempString[1]));
                    response += tasks.addTask(newEvent);

                }

                if (textMessage.equals("list")) {
                    response += tasks.showList();
                }

                if (keyword[0].equals("done")) {
                    int index = Integer.parseInt(keyword[1]) - 1;
                    tasks.getTasks().get(index).markDone();
                    response += "Nice I've marked this tasks as done";
                    response += tasks.getTasks().get(index);
                }

                else {
                    response +="☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
                }

            }
            catch (DukeException e) {
                response += e.getMessage();
            }

        return response;
    }

}
