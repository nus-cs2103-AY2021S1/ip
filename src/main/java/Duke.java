import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
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
                    try {
                        if (lists.size() == 0) {
                            throw new IndexOutOfBoundsException();
                        }
                        if (test.length == 1) {
                            throw new MissingNumberException();
                        }
                        int idx = Integer.parseInt(test[1]) - 1;
                        Task target = lists.get(idx);
                        target.markDone();
                        Response msg = new Response(new String[]{"Nice! I've marked this task as done:", "  " + target});
                        System.out.println(msg.getResponse());
                    } catch (IndexOutOfBoundsException e) {
                        Response msg = new Response(new String[]{"No remaining tasks in the list!"});
                        System.out.println(msg.getResponse());
                    } catch (MissingNumberException e) {
                        Response msg = new Response(new String[]{"Please select the task that you want to mark done!"});
                        System.out.println(msg.getResponse());
                    }
                } else if (test[0].equals("delete")) {
                    try {
                        if (lists.size() == 0) {
                            throw new IndexOutOfBoundsException();
                        }
                        if (test.length == 1) {
                            throw new MissingNumberException();
                        }
                        int idx = Integer.parseInt(test[1]) - 1;
                        Response msg = new Response(new Task[]{lists.get(idx)}, Response.Tag.REMOVE, lists.size() - 1);
                        System.out.println(msg.getResponse());
                        lists.remove(idx);
                    } catch (IndexOutOfBoundsException e) {
                        Response msg = new Response(new String[]{"No remaining tasks in the list!"});
                        System.out.println(msg.getResponse());
                    } catch (MissingNumberException e) {
                        Response msg = new Response(new String[]{"Please select the task that you want to delete!"});
                        System.out.println(msg.getResponse());
                    }
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
                                try {
                                    String description = str[0];
                                    String by = str[1].split("by ")[1];
                                    LocalDateTime byTime = LocalDateTime.parse(by);
                                    Deadline deadline = new Deadline(description, byTime);
                                    lists.add(deadline);
                                    Response msg = new Response(new Task[]{deadline}, Response.Tag.ADD, lists.size());
                                    System.out.println(msg.getResponse());
                                } catch (DateTimeParseException e) {
                                    Response msg = new Response(new String[]{"Format of date and time is incorrect! Please fill in the date and time following the format below.",
                                            "YYYY-MM-DDTHH:MM:SS"});
                                    System.out.println(msg.getResponse());
                                }
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
                            } else {
                                try {
                                    String description = str[0];
                                    String at = str[1].split("at ")[1];
                                    LocalDateTime atTime = LocalDateTime.parse(at);
                                    Event event = new Event(description, atTime);
                                    lists.add(event);
                                    Response msg = new Response(new Task[]{event}, Response.Tag.ADD, lists.size());
                                    System.out.println(msg.getResponse());
                                } catch (DateTimeParseException e) {
                                    Response msg = new Response(new String[]{"Format of date and time is incorrect! Please fill in the date and time following the format below.",
                                            "YYYY-MM-DDTHH:MM:SS"});
                                    System.out.println(msg.getResponse());
                                }
                            }
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
