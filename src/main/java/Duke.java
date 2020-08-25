package main.java;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private enum Messages {
        WRONG_INPUT("    A real Spartan would know  " +
            "those commands don't work."),
        MISSING_INPUT("    The Covenant are trying to plug up " +
                "our list with meaningless garbage. " +
                "Try again with commands this time."),
        DONE_ERROR("    Sorry Chief, I can't do that."),
        BYE("bye"),
        LIST("list"),
        DONE("done"),
        DELETE("delete"),
        GOODBYE_MSG("    Don't make a girl a promise... If you know you can't keep it."),
        LOGO("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@@@@&&&&&&&&&&&&&&&&&&&&@@@&&&&@@@&&&&&&&&&&&&&&&&@\n" +
                "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&&&&&\n" +
                "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@@@@@@@@@&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&%&%%%%&%&&&&&&&&&&&&&&&&&&&&&&&&&@@&@&@&&&&&&&&@@&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "%%%%%%%%%%%%%%%&&&&&&&&&&&&&&&&&&&&&%%%#%%%%%%%&%&&&&&&%%%&&&&&&&&&&&&&&&@&&&&@&&&&&&&&&&&&&&@@@&@&&&&&&&@@@@@@@&@@@@@@@\n" +
                "%%%%%%%%%%%%%%%%%%%%%%%%%&&&&&&&&&&#%%%#%#%%#%%#%#%//((###%&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&@@@@@@\n" +
                "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#%#%###%####(#,,,,**///((#%&%&&%&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n" +
                "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%###%%%#%##(.. ...,,**///((%&##%%%%&&&&&&&&&&&&&%%%%%%%%%%%%%%%%%%%&&&&&&&&&&&&&&&&&&&\n" +
                "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%##%#%#%%%%/(......,,****//(((#%%%%&&&&&&%%&&&&&&&&%%%%%%%%%&&&&&&&%%%%%%%%%%%%&&&&%%%%%\n" +
                "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%##%##%##%%# /(((///*****////((###%%%%&&&&&&&&&&&&&&&&%%&&&&&&%%%%%%%%&&&&%&&&%&&&%%%&&&%%\n" +
                "####%%%%%%%%%%%%%%%%%%%%%%%%%%#%%##%###%#(.,(///*****,***//////(((%#%%&&&&&%&&&&&&&&%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                "#########%%%%%%%%%%%%%%%%%%%%%%#%##%#####,/((,/#((/*,..,**///#,/%##%%#%&%&&&&&&&&&&&&%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                "#################%%%%%%%%%%%#%###%#####(/  ./.,,*/*,   *//(/,.,///((#(#%&&&&&&&&&&&&&%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                "############################%###%#####((.  , ..,,..    */((((/***//((###%&&&%&&&&&&&&%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                "################################%#####(/*      ...    ,*/(((((((((((((###%%&%&&&&&&&&%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%&%&&&\n" +
                "#####################################((/ ,       ..   .**/((///(((((((###%%&&&&&&&&&&%%%%%%%%%%%%%%%%%%%%%%%&&&%%%&%%%%%\n" +
                "#####################################((/.       .. .,,*//////**//////((#%%%%%%&&&&&&&%%%%%%%%%%%%%%%%&%%%%%%%%%%%%%%%%%%\n" +
                "(#((((((((((((((############(########((/*             *,/((((/////////(#%%%%%%%%%&&&%######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                "######################################((/*         .,****/////****////#%%%%%%%%%%%%%##%%%%%%%%%%%%%%%%%%%###%%%%%%%%%%%%\n" +
                "(((((################################(((//*       ...,**/((((/***///###%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%##########\n" +
                "(((((((((((((((((((((((((((((((((((((((((//*        ..,,,,,******/(######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                "((((((((((((((((((((((((((((((((((((((((((//*         .,********/(/(##########%#######%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                "///////////////////(/((((((((((((((((((((///*,,      ...,,,***/*////((#################################%#%%%%%%%%%%%%%%%\n" +
                "*******////////////////////////////////////**. . .,,,,,,,,******////(((((##(#########################################%%%\n" +
                ",**********************///////////////////***.     ..,,,,,****//////((((((((((((########################################\n" +
                ",,,**********************/////////////////***,      ..,,,*****///////(((((((((((((######################################\n" +
                ",,,***********************////////////////***,     ...,,,,,,*****/*///((((((((((((######################################\n" +
                ",,,*************************//////////////***,     ...,,,,,,**,,*****//((((#(((####################################%%%%%\n" +
                "*****************/////////////////////////**,      ...,.,,,,,,.,****//((#############################%%%##%%%%%%%%%%%%%%\n" +
                "*******///////////////////////////////*,,..,,.      ,,,.,,,*,,,,***//(((############%%%%%%%%##%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                "//////////////////////////////*,.......   ,,,,.     .,,.,,.,,,,,***(((#################%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                "///////////////////////**,,,........,.....*,,*,,    .,,.., ...,**/(((########################%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" +
                "/((((///////////////.. .          ....,,,,***//**. .,,,,,, ..,,*/((#######((####(((#((((//((((((/((#(#%%%%&&%%%%%%%%%%%%\n" +
                "///////////////*...                  .,..***/////,**///**,...,,*(/(#######(((###%#(#((#(((((////****/((#%%%&&&&&%%%%%%%%\n" +
                "//////////***.  . ..              ...   .,, ,****.,*******,..,,*((########((((((#%%%%%###(((////**,****((##%%%%&&%%%%%%%\n" +
                "////////**,                   .....     ,,,. , *, ..,,,,,,.,,*,*((#########(((##(#%%%%%%##((//********///(((##%%%&%%%%%%\n" +
                "///////**,                  ..,,,.     ,,*******. ......,,,,,,,*(((#########(%####%%%%%%%#(#((/((**/*****/(//(((#%&%%%%%\n" +
                "///////*,                  ..,,,,.. ..,*********,........,,,,*/(((((########%%%%%%%%%%%%%%#(((((/(/(/((//*////(((#%&%&&&\n" +
                "//////**,                 ...,,,.... ,****///*////,........,,//((((((#########%%%%%%%%%%%%%%%#(((///***//*//////(##&%&&&\n" +
                "//////**,               .......     .,***/////////...,,,,,.,,//*//((##########%%%%%%%%%%%%%%%(%#((/(///*****/***/(#%%&&&\n" +
                "//////**,              ......      .,,***///////.*...........//**/(((########((#%%%%%%%##%%%%%(##(//*/**,,,,,,**/((%%&&&\n" +
                "///////*,,           ......        .,***////////**...........*////((((((###(/(((((#((((##%%%%%%(##(/****,,,,.,**//(%%&&&\n" +
                "##((((/**,          ......         .,***/////////*.,.... ....**/*/((((((((#(*/((((#(((##(##(%%%%/%(//,,*,,,,,,*//(#%%&&&\n" +
                "###((((/**,         ....          .,,***//(((((//*,*..... .  ,***/////((/(((**(((#(((((#(((/(/#####//,,,,,,,**///(%%%%&&\n" +
                "####((((/***       ...           ..,.**///(((((*,**,,,,... .  .**///(((((,(((*/((##(####(.((//*####(.......,,**/(#%&&&&&\n" +
                "#####((((/***       .           .,,**////((((/*****,,..        ,**//(((((((##(//(##########((//(###,..,,..,,,*##((%%%&&&");

        private final String value;

        Messages(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }

    private static final Messages[] ERROR_MESSAGES = {Messages.WRONG_INPUT, Messages.MISSING_INPUT,
        Messages.MISSING_INPUT, Messages.DONE_ERROR};
    private static List<Task> thingsOnList = new ArrayList<>();

    protected static void printLine() {
        System.out.println("    ************************************************************");
    }

    private static void viewList() {
        if (thingsOnList.size() == 0) {
            System.out.println("    It's getting a little lonely here Chief. No tasks here.");
        } else {
            for (int i = 0; i < thingsOnList.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + thingsOnList.get(i));
            }
        }
    }
    protected static String getWrongInput() {
        int rnd = new Random().nextInt(ERROR_MESSAGES.length);
        return ERROR_MESSAGES[rnd].toString();
    }

    private static void addToList(String input) throws DukeExceptions {
        int startingSize = thingsOnList.size();
        if (!input.isEmpty()) {
            printLine();
            if (input.equals(Messages.BYE.toString())) {
                System.out.println(Messages.GOODBYE_MSG.toString());
            } else if (input.equals(Messages.LIST.toString())) {
                viewList();
            } else if (!input.isEmpty()) {
                int spaceIndex = input.indexOf(" ");
                if (spaceIndex != -1 && spaceIndex != input.length() - 1 && (input.contains(Messages.DELETE.toString())
                        || input.contains(Messages.DONE.toString()))) {
                    try {
                        int x = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
                        if (x + 1 > thingsOnList.size()) {
                            throw new DukeExceptions("    This task doesn't exist Chief!");
                        }
                        if (input.substring(0, spaceIndex).equals(Messages.DONE.toString())) {
                            System.out.println("    Mission accomplished.");
                            thingsOnList.get(x).markAsDone();
                            viewList();
                        } else if (input.substring(0, spaceIndex).equals(Messages.DELETE.toString())) {
                            System.out.println("    Roger. I'm removing the task: " + thingsOnList.get(x));
                            thingsOnList.remove(x);
                        }
                    } catch (NumberFormatException e) {
                        throw new DukeExceptions("    That's not a task number.");
                    }
                } else {
                    int cmdIndex = input.indexOf("/");
                    if (cmdIndex == 0 || (input.contains("todo") && input.length() == 4)) {
                        throw new DukeExceptions("    There's no content for this command");
                    } else if (cmdIndex != -1 && cmdIndex != input.length() - 1) {
                        String cmd = input.substring(cmdIndex, cmdIndex + 3);
                        if (cmd.equals("/by")) {
                            thingsOnList.add(new Deadlines(input));
                        } else if (cmd.equals("/at")) {
                            thingsOnList.add(new Events(input));
                        }
                    } else if (input.contains("todo") && !(input.substring(input.length() - 4).contains("todo"))) {
                        if (input.substring(5).isEmpty()) {
                            throw new DukeExceptions("    Can't make a todo without something to do, Chief.");
                        }
                        thingsOnList.add(new ToDos(input));
                    }
                    if (thingsOnList.size() == startingSize) {
                        throw new DukeExceptions("    There has been an incorrect use of a command.");
                    } else {
                        System.out.println("    Roger. I've added this task:\n    " +
                                thingsOnList.get(thingsOnList.size() - 1) + "\n    " +
                                "Now you have " + thingsOnList.size() + " tasks in the list.");
                    }
                }
            }
            printLine();
        }
    }
    protected static void Echo() throws DukeExceptions {
        Scanner sc = new Scanner(System.in);
        File tmpFile = new File(System.getProperty("user.dir") + "\\src\\main\\List.txt");
        FileReader taskReader;
        FileWriter writer;
        if(!tmpFile.exists()) {
            try {
                tmpFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Too bad");
                throw new DukeExceptions("    Can't make this list.");
            }
        } else if(thingsOnList.isEmpty()) {
            try {
                taskReader = new FileReader(tmpFile);
                PrintStream oldOutput = System.out;
                PrintStream dummyOut = new PrintStream(new OutputStream() {
                    @Override
                    public void write(int i) throws IOException {
                        //nothing happens
                    }
                });
                System.setOut(dummyOut);
                BufferedReader bufferedTaskReader = new BufferedReader(taskReader);
                String line;
                while ((line = bufferedTaskReader.readLine()) != null) {
                    addToList(line);
                    System.out.println(line);
                }
                System.setOut(oldOutput);
                System.out.println("    You have these tasks currently:");
                viewList();
            } catch ( IOException e) {
                throw new DukeExceptions(e.getMessage());
            }
        }
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            addToList(input);
            if (input.equals(Messages.BYE.toString())) {
                try {
                    writer = new FileWriter(tmpFile);
                    for (int i = 0; i < thingsOnList.size(); i++) {
                        writer.write(thingsOnList.get(i).getFullText() + "\n");
                    }
                    writer.close();
                    System.out.println("List Saved!");
                } catch (IOException e) {
                    throw new DukeExceptions("Sorry, I couldn't save that list before closing.");
                }
                sc.close();
                break;
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(Messages.LOGO + "\nCortana here to help you with your lists.");
        try {
            Echo();
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
            System.out.println(getWrongInput());
            printLine();
            e.continueTrying();
        }
    }
}