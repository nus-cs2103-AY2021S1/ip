import java.time.LocalDate;
import java.util.Scanner;

/**
 * Listener of inputs
 */
public class Parser {


    public String listenerForUI(TaskList myTasklist, Ui myUi,String inputUI) {

        String currInput = inputUI;

        String response= "";
            myUi.horizontalRule();
            String[] fullArg = currInput.split("/");
            String[] args1 = fullArg[0].split(" ");


            if (currInput.equals("bye")) {
                response += "Bye. Hope to see you again soon!\n";
                myUi.horizontalRule();
            }

            if (args1[0].equals("find")) {
                String findItem = "";
                for (int i = 1; i < args1.length; i++) {
                    findItem += args1[i] + " ";
                }
                response+=myTasklist.findTaskUI(findItem);
            } else if (args1[0].equals("todo")) {
                String task = "";
                for (int i = 1; i < args1.length; i++) {
                    task += args1[i] + " ";
                }
                try {
                    response+=myTasklist.addTaskUI("[T]", task);
                } catch (InSuffArgsException E) {
                    System.out.println("☹ OOPS!!! The description of a " + args1[0] + " cannot be empty.");
                }
                response+=myTasklist.numTaskUI();
            } else if (args1[0].equals("delete")) {
                response+=myTasklist.deleteTaskUI(Integer.parseInt(args1[1]) - 1);
            } else if (args1[0].equals("deadline")) {
                String task = "";
                for (int i = 1; i < args1.length; i++) {
                    task += args1[i] + " ";
                }
                try {
                    if (fullArg.length == 1) {
                        throw new InSuffArgsException();
                    }
                    if (fullArg.length == 4) {
                        String day = Integer.parseInt(fullArg[1].substring(fullArg[1].length() - 1)) + "";
                        if (day.length() == 1) {
                            day = "0" + day;
                        }
                        String month = Integer.parseInt(fullArg[2]) + "";
                        if (month.length() == 1) {
                            month = "0" + month;
                        }
                        int year = Integer.parseInt(fullArg[3].split(" ")[0]);
                        //int time = Integer.parseInt(fullArg[3].split(" ")[1]);

                        //System.out.println(time);

                        LocalDate d1 = LocalDate.parse(year + "-" + month + "-" + day);
                        response+=myTasklist.addTaskUI("[D]", task, d1);

                    } else {
                        response+=myTasklist.addTaskUI("[D]", task, fullArg[1]);
                    }

                } catch (InSuffArgsException E) {
                    System.out.println("☹ OOPS!!! The description of a " + args1[0] + " cannot be empty.");
                }

                response+=myTasklist.numTaskUI();

            } else if (args1[0].equals("event")) {

                String task = "";
                for (int i = 1; i < args1.length; i++) {
                    task += args1[i] + " ";
                }
                try {
                    if (fullArg.length == 1) {
                        throw new InSuffArgsException();
                    }
                    response+= myTasklist.addTaskUI("[E]", task, fullArg[1]);
                } catch (InSuffArgsException E) {
                    System.out.println("☹ OOPS!!! The description of a " + args1[0] + " cannot be empty.");
                }
                response+=myTasklist.numTaskUI();

            } else if (currInput.equals("list")) {
                response+=myTasklist.listUI();
            } else if (args1[0].equals("done")) {
                int index = Integer.parseInt(args1[1]) - 1;
                myTasklist.getTasks().get(index).setDone();
                response+="Nice I've marked this tasks as done\n";
                response+=myTasklist.getTasks().get(index)+"\n";
            } else {
                response+="☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
            }
            response+=myUi.horizontalRule();


        return response;


    }

}
