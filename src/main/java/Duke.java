import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    enum Command {
        list, done, delete, todo, deadline, event
    }

    public static void main(String[] args) {
        String logo = "\n"
                + " oooooooooooo                                                 oooo        \n"
                + "d'\"\"\"\"\"\"d888'                                                 `888        \n"
                + "      .888P    .ooooo.  oooo d8b  .ooooo.   .oooo.   oooo d8b  888  oooo  \n"
                + "     d888'    d88' `88b `888\"\"8P d88' `88b `P  )88b  `888\"\"8P  888 .8P'   \n"
                + "   .888P      888   888  888     888   888  .oP\"888   888      888888.    \n"
                + "  d888'    .P 888   888  888     888   888 d8(  888   888      888 `88b.  \n"
                + ".8888888888P  `Y8bod8P' d888b    `Y8bod8P' `Y888\"\"8o d888b    o888o o888o \n"
                + "                                                                          \n"
                + "                                                                          \n"
                + "                                                                          \n";
        String lowerLine = "\n(¯`·._.·(¯`·._.· Zoroark ·._.·´¯)·._.·´¯)\n";
        String upperLine = "    ♫♪.ılılıll|̲̅̅●̲̅̅|̲̅̅=̲̅̅|̲̅̅●̲̅̅|llılılı.♫♪\n\n";
        String goodbye = " __   __   __   __   __       ___ \n"
                + "/ _` /  \\ /  \\ |  \\ |__) \\ / |__  \n"
                + "\\__> \\__/ \\__/ |__/ |__)  |  |___ \n" + "                                  ";

        System.out.println("Hello I am\n" + logo + "How can I help you?");
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        ArrayList<Task> tasks = new ArrayList<>();
        while (!answer.equals("bye")) {
            String[] command = answer.split(" ");
            String description, time;
            int idx;
            Task task;

            System.out.println(upperLine);
            try {
                Command commandEnum;

                try {
                    commandEnum = Command.valueOf(command[0]);
                } catch (Exception e) {
                    throw new DukeException("I'm sorry but I don't recognize your command T__T.");
                }

                switch (commandEnum) {
                    case list:
                        System.out.println("Here is your list of tasks:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i) + "\n");
                        }
                        break;

                    case done:
                        idx = Integer.parseInt(command[1]);
                        if (idx < 1 || idx > tasks.size()) {
                            throw new DukeException(
                                    "Sorry, but you inputted an invalid task index.");
                        }
                        tasks.get(idx - 1).markAsDone();

                        System.out.println("Great job!\nI have marked the task as done");
                        System.out.println(tasks.get(idx - 1));
                        break;
                    case delete:
                        idx = Integer.parseInt(command[1]);
                        if (idx < 1 || idx > tasks.size()) {
                            throw new DukeException(
                                    "Sorry, but you inputted an invalid task index.");
                        }

                        System.out.println("Noted!\nI have deleted this task from your task list.");
                        System.out.println(tasks.get(idx - 1));
                        tasks.remove(idx - 1);
                        System.out.printf(
                                "Currently you have %d tasks in your list, don't forget to do them!\n",
                                tasks.size());
                        break;

                    case todo:
                        if (command.length < 2) {
                            throw new DukeException("The description can't be blank :(.");
                        }
                        description = command[1];
                        idx = 2;
                        while (idx < command.length) {
                            description += " " + command[idx];
                            idx++;
                        }

                        task = new Todo(description);
                        tasks.add(task);

                        System.out.printf("Okay! I have added the task:\n%s\n", task);
                        System.out.printf(
                                "Currently you have %d tasks in your list, don't forget to do them!\n",
                                tasks.size());
                        break;

                    case deadline:
                        if (command.length < 2) {
                            throw new DukeException("The description can't be blank :(.");
                        }
                        description = command[1];
                        idx = 2;
                        while (idx < command.length && !command[idx].equals("/by")) {
                            description += " " + command[idx];
                            idx++;
                        }
                        idx++;

                        if (idx >= command.length) {
                            throw new DukeException(
                                    "Please specify deadline time in this format: \"/by <time>\". ");
                        }

                        time = command[idx];
                        idx++;
                        while (idx < command.length) {
                            time += " " + command[idx];
                            idx++;
                        }

                        task = new Deadline(description, time);
                        tasks.add(task);

                        System.out.printf(
                                "Okay! I have added the task:\n%s\nRemember to do it before the deadline!\n",
                                task);
                        System.out.printf(
                                "Currently you have %d tasks in your list, don't forget to do them!\n",
                                tasks.size());
                        break;

                    case event:
                        if (command.length < 2) {
                            throw new DukeException("The description can't be blank :(.");
                        }
                        description = command[1];
                        idx = 2;
                        while (idx < command.length && !command[idx].equals("/at")) {
                            description += " " + command[idx];
                            idx++;
                        }
                        idx++;

                        if (idx >= command.length) {
                            throw new DukeException(
                                    "Please specify event time in this format: \"/at <time>\". ");
                        }

                        time = command[idx];
                        idx++;
                        while (idx < command.length) {
                            time += " " + command[idx];
                            idx++;
                        }

                        task = new Event(description, time);
                        tasks.add(task);

                        System.out.printf(
                                "Okay! I have added the task:\n%s\nRemember to attend it!\n", task);
                        System.out.printf(
                                "Currently you have %d tasks in your list, don't forget to do them!\n",
                                tasks.size());
                        break;

                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                System.out.println(lowerLine);
                answer = in.nextLine();
            }
        }

        System.out.println(upperLine + goodbye + "\n" + lowerLine);
        in.close();
    }
}
