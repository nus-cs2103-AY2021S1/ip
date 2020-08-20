public enum Exceptions {
    NUMBEREXCESSEXCEPTION {
        @Override
        public String toString() {
            return "    OOPS!!! It seems like you don't have this option\n" +
                    "    Enter list to see options";
        }
    },

    CLASSCASTEXCEPTION {
        @Override
        public String toString() {
            return "    The content is not of data type of String";
        }
    },

    NUMBERFORMATEXCEPTION {
        @Override
        public String toString() {
            return "    OOPS!!! The format of your index number is incorrect";
        }
    },

    INPUTFORMATEXCEPTION {
        @Override
        public String toString() {
            return "    OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    },

    EMPTYTASKEXCEPTION {
        @Override
        public String toString() {
            return "    OOPS!!! The description of the task cannot be empty";
        }
    },

    NOTIMEEXCEPTION {
        @Override
        public String toString() {
            return "    OOPS!!! The time of a deadline/event cannot be empty";
        }
    }
}
