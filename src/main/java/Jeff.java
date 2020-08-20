import java.util.Scanner;

public class Jeff {

    public static void main(String[] args) {

        String[] rubbishBin = new String[100];
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
            String input = Sc.nextLine();

            if(input.equals("bye")){
                System.out.println("Bye, Have a great time! ");
                break;
            }else{
                if(input.equals("list")){
                    for(int i=0; i<counter; i++){
                        System.out.println( i+1 +"."+" "+ rubbishBin[i]);
                    }
                }else{
                    rubbishBin[counter] = input;
                    System.out.println(add + rubbishBin[counter]);
                    counter++;
                }
            }

            System.out.println(line);

        }
    }
}