import java.util.Scanner;

public class Event {
    //This class handles the simulation of the Duke chat bot
    public static void simulate(){
        TaskList ls= new TaskList();
        boolean hasBye = false;
        String input;
        String[] splitList;

        String line = "     ___________________________________________________________________";
        String introText1 = "     Hello! I'm Duke";
        String introText2 = "     What can I do for you?";

        System.out.println(line);
        System.out.println(introText1);
        System.out.println(introText2);
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();

        while(!hasBye){
            splitList = input.split(" ", 2);
            System.out.println(line);
            if(input.equals("bye")){
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(line);
                hasBye = true;
            }else if(input.equals("list")){
                if(ls.isEmpty()){
                    System.out.println("     No tasks in the list");
                }else{
                    ls.printTask();
                }
                System.out.println(line);
                input = sc.nextLine();
            }else if(splitList[0].equals("done")){
                try{
                    ls.setDone(Integer.parseInt(splitList[1]) - 1);
                }catch (Exception e){
                    System.out.println(e.toString());
                }finally {
                    System.out.println(line);
                    input = sc.nextLine();
                }
            }else{
                ls.add(input);
                System.out.println("     added: " + input);
                System.out.println(line);
                input = sc.nextLine();
            }
        }
    }
}
