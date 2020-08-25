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
    },

    FILEEXCEPTION {
        @Override
        public String toString() {
            return "    OOPS!!! The file exists but is a directory rather than a regular file,\n" +
                    "    or does not exist but cannot be created,\n" +
                    "    or cannot be opened for any other reason";
        }
    },

    READLINEEXCEPTION {
        @Override
        public String toString() {
            return "    OOPS!!! There is a problem reading";
        }
    }
}
