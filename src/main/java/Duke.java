import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> lists = new ArrayList<Task>();
        String logo = "      ___                __     \n"
                + "     |  _ \\   ____      |  |    \n"
                + "     | |_| | / __  \\  __|  |__  \n"
                + "     |  _ / | |  |  ||__    __| \n"
                + "     | |    | |_/   |   |  |    \n"
                + "     |_|     \\___/|_|   |__|    \n";
        String[] greetingTexts = {"Hello! I'm Pat", "What can I do for you?"};
        Response greeting = new Response(greetingTexts);
        System.out.println("     Hello from\n" + logo);
        System.out.println(greeting.getResponse());
        while(sc.hasNext()) {
            String received = sc.nextLine();
            if (received.equals("bye")) {
                String[] byeMessage = {"Bye. Hope to see you again soon!"};
                Response bye = new Response(byeMessage);
                System.out.println(bye.getResponse());
                break;
            } else if (received.equals("list")) {
                Response list = new Response(lists.toArray(new Task[0]), Response.Tag.LIST);
                System.out.println(list.getResponse());
            } else {
                String[] test = received.split(" ");
                if (test[0].equals("done")) {
                    int idx = Integer.parseInt(test[1]) - 1;
                    Task target = lists.get(idx);
                    target.markDone();
                    Response msg = new Response(new String[]{"Nice! I've marked this task as done:", "  " + target});
                    System.out.println(msg.getResponse());
                } else {
                    if (test[0].equals("todo")) {
                        try {
                            String description = received.split("todo ")[1];
                            Todo todo = new Todo(description);
                            lists.add(todo);
                            Response msg = new Response(new Task[]{todo}, Response.Tag.ADD, lists.size());
                            System.out.println(msg.getResponse());
                        } catch (ArrayIndexOutOfBoundsException err) {
                            MissingDescriptionException realError = new MissingDescriptionException("a todo");
                            Response msg = new Response(new String[]{String.valueOf(realError)});
                            System.out.println(msg.getResponse());
                        }
                    } else if (test[0].equals("deadline")) {
                        try {
                            String[] str = received.split("deadline ")[1].split(" /");
                            if (str.length == 1) {
                                throw new MissingDeadlineException();
                            } else {
                                String description = str[0];
                                String by = str[1];
                                Deadline deadline = new Deadline(description, by);
                                lists.add(deadline);
                                Response msg = new Response(new Task[]{deadline}, Response.Tag.ADD, lists.size());
                                System.out.println(msg.getResponse());
                            }    
                        } catch (ArrayIndexOutOfBoundsException e) {
                            MissingDescriptionException realError = new MissingDescriptionException("a deadline");
                            Response msg = new Response(new String[]{String.valueOf(realError)});
                            System.out.println(msg.getResponse());
                        } catch (MissingDeadlineException e){
                            Response msg = new Response(new String[]{String.valueOf(e)});
                            System.out.println(msg.getResponse());
                        }
                    } else if (test[0].equals("event")) {
                        try {
                            String[] str = received.split("event ")[1].split(" /");
                            if (str.length == 1) {
                                throw new MissingTimeException();
                            }
                            Event event = new Event(str[0], str[1]);
                            lists.add(event);
                            Response msg = new Response(new Task[]{event}, Response.Tag.ADD, lists.size());
                            System.out.println(msg.getResponse());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            MissingDescriptionException realError = new MissingDescriptionException("an event");
                            Response msg = new Response(new String[]{String.valueOf(realError)});
                            System.out.println(msg.getResponse());
                        } catch (MissingTimeException e) {
                            Response msg = new Response(new String[]{String.valueOf(e)});
                            System.out.println(msg.getResponse());
                        }
                    } else {
                        UnknownInputException ue = new UnknownInputException();
                        Response msg = new Response(new String[]{String.valueOf(ue)});
                        System.out.println(msg.getResponse());
                    }
                }
            }
        }
    }
}
