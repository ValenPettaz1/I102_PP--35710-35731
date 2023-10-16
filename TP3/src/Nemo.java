
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Nemo {

    private Point position;
    private Cardinal direction;
    private List<Depth> depth = new ArrayList<>(Arrays.asList(new Surface()));


    public Nemo(int xCoord, int yCoord, Cardinal direction) {
        this.position = new Point(xCoord, yCoord, 0);
        this.direction = direction;
    }

    /*public Nemo operate(String operations) {
        operations.chars()
                .mapToObj(c -> (char) c)
                .forEach(this::funcion());
    }
    private void funcion(Character character) {}*/

    public void operate(){}

    public void operate(Character character) {
        this.operate(character.toString());
    }

    public Nemo operate(String operations) {

        for (int i = 0; i < operations.length(); i++) {

            char operation = operations.charAt(i);

            if (operation == 'u') {
                position = depth.get(0).ascendMe(this);



            } else if (operation == 'd') {
                position = depth.get(depth.size()-1).descendMe(this);

            }

            else if (operation == 'r') {
                direction = direction.turnRight();
            }
            else if (operation == 'l') {
                direction = direction.turnLeft();
            }

            else if (operation == 'f') {
                if (Objects.equals(direction, new North())) {
                   position = position.add(new Point(0, 1, 0));
                } else if (Objects.equals(direction, new West())) {
                    position = position.add(new Point(-1, 0, 0));
                } else if (Objects.equals(direction, new South())) {
                    position = position.add(new Point(0, -1, 0));
                } else if (Objects.equals(direction, new East())) {
                    position = position.add(new Point(1, 0, 0));
                }
            } else if (operation == 'm') {
                if (position.getZ() < -1) {
                    throw new RuntimeException("No se puede liberar la cápsula debajo del primer nivel");
                }
            }
        }
        return this;
    }

    public void add(Depth level) { depth.add(depth.size()-1, level);}
    public void remove() { depth.remove(0);}


    public Point getPosition() {return position;}
    public Cardinal getDirection() {return direction;}
}
