public class LowerLevel extends Depth {

    @Override
    public Point ascendMe(Nemo nemo) {
        nemo.remove();
        return nemo.getPosition().add(Point.Up);
    }

    @Override
    public Point descendMe(Nemo nemo) {
        nemo.add(new LowerLevel());
        return nemo.getPosition().add(Point.Down);
    }

    @Override
    public void dropCapsule() {
        throw new RuntimeException("No se puede liberar la cápsula debajo del primer nivel");
    }
}
