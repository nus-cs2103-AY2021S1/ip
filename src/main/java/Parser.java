/**
 * Parser class handles userinput from UI class and parses throught it to create the Task List
 */
public class Parser {


    /**
     * Takes all the necessary arguments to create the list of tasks
     *
     * @param taskList main list where all tasks are kept on
     * @param ui       scanner that takes user inputs
     * @param flag     boolean value that exits the program once true
     */
    public static String parseCode(TaskList taskList, UI ui, boolean flag, String userInput) {
       // while (!flag) {

            String echo = userInput;//ui.sc.nextLine();
            try {
                String split = echo;
                String arr[] = split.split(" ", 2);
                String keyword = arr[0];
                Commands command = Commands.findCommand(keyword);
                switch (command) {
                    case EXIT:
                        Storage.save(taskList, Storage.FILE_PATH);


                        flag = true;
                        return ui.addLines("Bye. Hope to see you again soon!");
                      //  break;
                    case LIST:


                        return ui.addLines(taskList.printOutList());
                        //break;
                    case DONE:
                        try {

                            int index = Integer.parseInt(arr[1]) - 1;
                          return ui.addLines(taskList.markCompleted(index));
                          //  break;
                        } catch (Exception e) {
                            System.out.println(new DukeException("Integer not detected"));
                            break;
                        }
                    case DEADLINE:
                        try {


                           return ui.addLines(taskList.add(Deadline.createDeadline(arr[1])));
                           // break;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println(new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.", e));
                        }
                    case TODO:
                        try {

                            ToDo item = new ToDo(arr[1]);
                          return ui.addLines(taskList.add(item));
                           // break;
                        } catch (Exception e) {
                            System.out.println(new DukeException("☹ OOPS!!! The description of a todo cannot be empty.", e));
                            break;
                        }
                    case EVENT:
                        try {

                            return ui.addLines(taskList.add(Event.createEvent(arr[1])));
                          //  break;
                        } catch (Exception e) {
                            System.out.println(new DukeException("☹ OOPS!!! The description of a event cannot be empty.", e));
                            break;
                        }
                    case DELETE:
                        try {

                            int index2 = Integer.parseInt(arr[1]) - 1;
                           return ui.addLines(taskList.deleteTask(index2));
                           // break;
                        } catch (Exception e) {
                            System.out.println(new DukeException("☹ OOPS!!! There is no task at that list number to delete!", e));
                            break;
                        }
                    case FIND:

                        String findWord = arr[1];
                        taskList.findTask(findWord);
                       return ui.addLines(taskList.printOutKeyWordList());

                }


            } catch (DukeException e) {
                System.out.println(e);
                return e.toString();
            }
       // }
        return "Unable to parse";
    }


}
