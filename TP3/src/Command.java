public class Command {
    private char command;
    public Command (char command) {
        this.command = command;
    }
    public Nemo operateMe(Nemo nemo) {

        if (command == 'u') {
            return nemo.ascend();
        }
        else if (command == 'd') {
            return nemo.descend();
        }

        else if (command == 'r') {
            return nemo.turnRight();
        }
        else if (command == 'l') {
            return nemo.turnLeft();
        }

        else if (command == 'f') {
            return nemo.moveFront();
        }
        else if (command == 'm') {
            return nemo.dropCapsule();
        }

        return nemo;
    }


}
