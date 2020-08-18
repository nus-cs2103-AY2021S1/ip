public class Salutations {

    enum type {
        WELCOME, GOODBYE, TASKDONE
    }

    type typeOfMessage;

    public Salutations(type typeOfMessage) {
        this.typeOfMessage = typeOfMessage;
    }

    public void printMessage(int numOfIndents) {
        String indents = "  ";
        for (int i = 1; i < numOfIndents; i++) {
            indents = indents += indents;
        }
        switch (typeOfMessage) {
            case WELCOME:
                System.out.println(indents + "Hello! I'm Duke\n" + indents + "What can I do for you?");
                break;
            case GOODBYE:
                System.out.println(indents + "Bye!!! Hope to see you again real soon.");
                break;
            case TASKDONE:
                System.out.println(indents + "The following task has been marked done:");
        }

    }


}
