public class Task {

    public static String[] taskStorage = new String[100];

    public static void write(String input){
        for(int i = 0; i < 100; i++){
            if(taskStorage[i] == null){
                taskStorage[i] = input;
                System.out.println("added: " + input);
                break;
            }
        }
    }

    public static void read(){
        for(int i = 0; i < 100; i ++){
            if(taskStorage[i] != null){
                System.out.println(taskStorage[i]);
            }else{
                break;
            }
        }
    }

}
