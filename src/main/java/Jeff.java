import java.util.Scanner;

public class Jeff {

    public static void main(String[] args) {

        Task[] rubbishBin = new Task[100];
        String add = "added: ";
        int counter = 0;

        Scanner Sc = new Scanner(System.in);
        String line = "____________________________\n"
                +"____________________________";

        String logo = "****** ****** ****** ******\n"
                +"   *   *      *      *\n"
                +"   *   ****** ****** ******\n"
                +"*  *   *      *      *\n"
                +"***    ****** *      *\n";
        System.out.println("My name is\n" + logo);
        System.out.println("What do you want?");
        System.out.println(line);

        while(true){
            String input = Sc.nextLine().trim();
            System.out.println(input);
            if(input.equals("bye")){
                System.out.println("Bye, Have a great time! ");
                break;
            }else{

                if(input.contains("done")){
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    rubbishBin[index].complete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(rubbishBin[index]);
                }else if(input.equals("list")){
                    for(int i=0; i<counter; i++){
                        System.out.println(rubbishBin[i]);
                    }
                }else{
                    Task newTask = new Task(input);
                    rubbishBin[counter] = newTask;
                    System.out.println(add + input);
                    counter++;
                }
            }

            System.out.println(line);

        }
    }
}