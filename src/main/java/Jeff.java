import java.util.Scanner;

public class Jeff {

    public static void main(String[] args) {

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
            String input = Sc.next();

            if(input.equals("bye")){
                System.out.println("Bye, Have a great time! ");
                break;
            }else{
                System.out.println(input);
            }

            System.out.println(line);

        }
    }
}

