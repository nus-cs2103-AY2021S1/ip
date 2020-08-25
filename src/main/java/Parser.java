public class Parser {

    public static final String line = "____________________________________________________________";

    public static void parseCode(TaskList taskList, UI ui, boolean flag) {
        while (!flag) {
           String echo = ui.sc.nextLine();
            try {
                String split = echo;
                String arr[] = split.split(" ", 2);
                String keyword = arr[0];
                Commands command = Commands.findCommand(keyword);
                switch (command) {
                    case EXIT:
                        Storage.save(taskList, Storage.FILE_PATH);
                        System.out.println(line);
                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println(line);
                        flag = true;
                        break;
                    case LIST:
                        ui.addLines(taskList.printOutList());
                        break;
                    case DONE:
                        try {
                            int index = Integer.parseInt(arr[1]) - 1;
                            ui.addLines(taskList.markCompleted(index));
                            break;
                        } catch (Exception e) {
                            System.out.println(new DukeException("Integer not detected"));
                            break;
                        }
                    case DEADLINE:
                        try {
                            ui.addLines(taskList.add(Deadline.createDeadline(arr[1])));
                            break;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.", e));
                        }
                    case TODO:
                        try {
                            ToDo item = new ToDo(arr[1]);
                            ui.addLines(taskList.add(item));
                            break;
                        } catch (Exception e) {
                            System.out.println(new DukeException("☹ OOPS!!! The description of a todo cannot be empty.", e));
                            break;
                        }
                    case EVENT:
                        try {
                            ui.addLines(taskList.add(Event.createEvent(arr[1])));
                            break;
                        } catch (Exception e) {
                            System.out.println(new DukeException("☹ OOPS!!! The description of a event cannot be empty.", e));
                            break;
                        }
                    case DELETE:
                        try {

                            int index2 = Integer.parseInt(arr[1]) - 1;
                            ui.addLines(taskList.deleteTask(index2));
                            break;
                        } catch (Exception e) {
                            System.out.println(new DukeException("☹ OOPS!!! There is no task at that list number to delete!", e));
                            break;
                        }
                }



            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
}
