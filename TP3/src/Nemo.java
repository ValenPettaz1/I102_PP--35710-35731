public class Nemo {
    public static North north = new North();
    public static East east = new East();
    public static South south = new South();
    public static West west = new West();

    private int x;
    private int y;
    private int z;
    private Cardinal direction;
    private boolean capsule;

    public Nemo(int x, int y) {
        this.x = x;
        this.y = y;
        this.z = 0;
        this.direction = north;
        this.capsule = true;
    }

    public Nemo operate(String operations) {

        for (int i = 0; i < operations.length(); i++) {

            String operation = operations.substring(i, i + 1);

            if (operation == "u") {
                if (z != 0) {
                    z += 1;
                }
            } else if (operation == "d") {
                z -= 1;
            } else if (operation == "r") {
                direction = direction.turnRight();
            } else if (operation == "l") {
                direction = direction.turnLeft();
            } else if (operation == "f") {
                if (direction == north) {
                    x += 1;
                } else if (direction == west) {
                    y += 1;
                } else if (direction == south) {
                    x -= 1;
                } else if (direction == east) {
                    y -= 1;
                }
            } else if (operation == "m") {
                if (!this.capsule) {
                    throw new RuntimeException("La capsula ya fue lanzada");
                } else if (z < -1) {
                    throw new RuntimeException("No se puede liberar la cápsula debajo del primer nivel");
                }
                capsule = false;
            }
        }
        return this;
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public int getZ() {return z;}
    public Cardinal getDirection() {return direction;}
    public boolean hasCapsule() {return capsule;}
}
