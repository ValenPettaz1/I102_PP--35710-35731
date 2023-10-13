
public class Nemo {

    private Point position;
    private Cardinal direction;
    private static Point Up = new Point(0,0,1);
    private static Point Down = new Point(0,0,-1);

    public Nemo(int xCoord, int yCoord, Cardinal direction) {
        this.position = new Point(xCoord, yCoord, 0);
        this.direction = direction;
    }

    public Nemo operate(String operations) {
        operations.chars()
                .mapToObj(c -> (char) c)
                .forEach(this::funcion());
    }



    private void funcion(Character character) {

    }



    /*public void operate(){}

    public void operate(Character character) {
        this.operate(character.toString());
    }
    public Nemo operate(String operations) {

        for (int i = 0; i < operations.length(); i++) {

            char operation = operations.charAt(i);

            if (operation == 'u') {
                if (position.getZ() != 0) {
                    position.add(Up);
                }
            } else if (operation == 'd') {
                position.add(Down);
            } else if (operation == 'r') {
                direction = direction.getRight();
            }
            else if (operation == 'l') {
                    Operations.turnLeft(Nemo);
            } else if (operation == 'f') {
                if (direction == north) {
                    y += 1;
                } else if (direction == west) {
                    x -= 1;
                } else if (direction == south) {
                    y -= 1;
                } else if (direction == east) {
                    x += 1;
                }
            } else if (operation == 'm') {
                if (z < -1) {
                    throw new RuntimeException("No se puede liberar la cápsula debajo del primer nivel");
                }
            }
        }
        return this;
    }*/

    public Point getPosition() {return position;}
    public Cardinal getDirection() {return direction;}
}
