import java.util.Scanner;

public class Duke {

//    public static void echo(String s1){
//        System.out.println(s1);
//    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo +
                "People call me Duke the All-Known ," +
                "ask me anything by typing a line.");

        Scanner sc = new Scanner(System.in);
        String input;

        for (int i = 0; i < 100000; i++) {

            input = sc.nextLine();

            if (!input.equals("bye")) {

                if (!input.equals("list")) {

                    if (!input.contains("done") && !input.contains("delete")) {

                        if (input.contains("todo") || input.contains("event")
                                || input.contains("deadline")) {

                            String description;
                            String correctedInput;
                            DateAndTime byOrAt;

                            if (input.contains("todo")) {

                                if(input.length() > "todo ".length()) {

                                    description = input.substring("todo ".length(), "todo ".length() + 1);

                                    if (!description.equals(" ")) {

                                        correctedInput = input.substring("todo ".length());
                                        byOrAt = new DateAndTime();
                                        Task.write(correctedInput, "todo", byOrAt);

                                    } else {
                                        System.out.println(
                                                new DukeException(
                                                        "OOPS!!! The description of a todo cannot be empty."));
                                    }

                                }else{
                                    System.out.println(
                                            new DukeException(
                                                    "The description of a todo cannot be empty lah."));
                                }

                            } else if (input.contains("event")) {

                                if(input.length() > "event ".length()) {

                                    description = input.substring("event ".length(), "event ".length() + 1);

                                    if (!description.equals(" ")) {

                                        byOrAt =new DateAndTime(input.substring(input.indexOf("/") + "/at ".length()));

                                        correctedInput = input.substring("event ".length());
                                        Task.write(correctedInput, "event", byOrAt);

                                    } else {
                                        System.out.println(new DukeException(
                                                "The description of an event cannot be empty lah."));
                                    }
                                }else{
                                    System.out.println(new DukeException(
                                            "The description of an event cannot be empty lah."));
                                }

                            }else {
                                if(input.length() > "deadline ".length()) {
                                    description = input.substring("deadline ".length(), "deadline ".length() + 1);

                                    if (!description.equals(" ")) {
                                        byOrAt = new DateAndTime(input.substring(input.indexOf("/") + "/by ".length()));
                                        correctedInput = input.substring("deadline ".length());
                                        Task.write(correctedInput, "deadline", byOrAt);
                                    } else {
                                        System.out.println(
                                                new DukeException(
                                                        "The description of a deadline cannot be empty lah.")
                                        );
                                    }
                                }else{
                                    System.out.println(
                                            new DukeException(
                                                    "The description of a deadline cannot be empty lah.")
                                    );
                                }

                            }

                        } else {
                            System.out.println(
                                    new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(")
                            );
                        }

                    } else {

                        if(input.contains("done")) {

                            int ref = Integer.parseInt(Character.toString(
                                    input.charAt("done 1".length() - 1))) - 1;

                            if (Task.taskStorage.get(ref) != null) {

                                Task done = Task.taskStorage.get(ref).markAsDone();
                                Task.taskStorage.add(ref, done);

                                System.out.println("Nice! I've marked this task as done:\n"
                                        + Task.taskStorage.get(ref));

                            } else {

                                System.out.println("I am afraid that it is not possible" +
                                        "to do an unknown task.");

                            }

                        }else{
                            int ref = Integer.parseInt(Character.toString(
                                    input.charAt("delete 1".length() - 1))) - 1;

                            Task.delete(ref);
                        }

                    }
                }else {

                    Task.read();
                }
            } else {
                System.out.println(" Bye. Hope to see you again soon!");
                sc.close();
                System.exit(0);
            }
        }
    }

}
