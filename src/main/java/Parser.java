import java.time.LocalDate;
import java.util.Scanner;

public class Parser {

    public void listener(TaskList myTasklist, Ui myUi) {
        Scanner fetch = new Scanner(System.in);
        String currInput = fetch.nextLine();

        while (true) {
            myUi.horizontalRule();
            String[] fullArg = currInput.split("/");
            String[] args1 = fullArg[0].split(" ");


            if (currInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                myUi.horizontalRule();
                break;
            }

            if(args1[0].equals("find")){
                String findItem = "";
                for (int i = 1; i < args1.length; i++) {
                    findItem += args1[i] + " ";
                }
                myTasklist.findTask(findItem);
            }

            else if (args1[0].equals("todo")) {
                String task = "";
                for (int i = 1; i < args1.length; i++) {
                    task += args1[i] + " ";
                }
                try {
                    myTasklist.addTask("[T]",task);
                }
                catch (InSuffArgsException E){
                    System.out.println("☹ OOPS!!! The description of a " + args1[0] + " cannot be empty.");
                }
                myTasklist.numTask();
            } else if (args1[0].equals("delete")) {
                myTasklist.deleteTask(Integer.parseInt(args1[1]) - 1);
            } else if (args1[0].equals("deadline")) {
                String task = "";
                for (int i = 1; i < args1.length; i++) {
                    task += args1[i] + " ";
                }
                try {
                    if(fullArg.length==1){
                        throw new InSuffArgsException();
                    }
                    if(fullArg.length==4){
                        String day = Integer.parseInt(fullArg[1].substring(fullArg[1].length()-1))+"";
                        if(day.length()==1){
                            day= "0"+day;
                        }
                        String month = Integer.parseInt(fullArg[2])+"";
                        if(month.length()==1){
                            month= "0"+month;
                        }
                        int year = Integer.parseInt(fullArg[3].split(" ")[0]);
                        //int time = Integer.parseInt(fullArg[3].split(" ")[1]);

                        //System.out.println(time);

                        LocalDate d1 = LocalDate.parse(year+"-"+month+"-"+day);
                        myTasklist.addTask("[D]",task,d1);

                    }else{
                        myTasklist.addTask("[D]",task ,fullArg[1]);
                    }

                }
                catch (InSuffArgsException E){
                    System.out.println("☹ OOPS!!! The description of a " + args1[0] + " cannot be empty.");
                }

                myTasklist.numTask();

            } else if (args1[0].equals("event")) {

                String task = "";
                for (int i = 1; i < args1.length; i++) {
                    task += args1[i] + " ";
                }
                try {
                    if(fullArg.length==1){
                        throw new InSuffArgsException();
                    }
                    myTasklist.addTask("[E]",task ,fullArg[1]);
                }
                catch (InSuffArgsException E){
                    System.out.println("☹ OOPS!!! The description of a " + args1[0] + " cannot be empty.");
                }
                myTasklist.numTask();

            } else if (currInput.equals("list")) {
                myTasklist.list();
            } else if (args1[0].equals("done")) {
                int index = Integer.parseInt(args1[1]) - 1;
                myTasklist.getTasks().get(index).setDone();
                System.out.println("Nice I've marked this tasks as done");
                System.out.println( myTasklist.getTasks().get(index));
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            myUi.horizontalRule();

            currInput = fetch.nextLine();
        }


    }

}
