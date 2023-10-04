public class Nemo {

    private int x;
    private int y;
    private int z;
    private int direction;

    public Nemo(int x, int y) {
        this.x = x;
        this.y = y;
        this.z = 0;
        this.direction = 0;
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public int getZ() {return z;}
    public int getDirection() {return direction;}

    public Nemo operate(String operation) {

        if (operation == "u") {
            if (z != 0) {
                z += 1;
            }
        }
        if (operation == "d") {
            z -= 1;
        }
        if (operation == "r") {
            direction = (direction - 90) % 360;
        }

        if (operation == "l") {
            direction = (direction + 90) % 360;
        }

        if (operation == "f"){
            if (direction == 0) {
                x += 1;
            }
            if (direction == 90) {
                y += 1;
            }
            if (direction == 180) {
                x -= 1;
            }
            if (direction == 270) {
                y -= 1;
            }
        }

        if (operation == "m" && z < -1){
            throw new RuntimeException("No se puede liberar la cápsula");
        }
        return this;
    }
}
