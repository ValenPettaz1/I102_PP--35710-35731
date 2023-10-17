public class LowerLevel extends Depth {

    @Override
    public Point ascendMe(Nemo nemo) {
        nemo.removeDepthLevel();
        return nemo.getPosition().add(Point.Up);
    }

    @Override
    public Point descendMe(Nemo nemo) {
        nemo.addDepthLevel(new LowerLevel());
        return nemo.getPosition().add(Point.Down);
    }

    @Override
    public void dropCapsule() {
        throw new RuntimeException("No se puede liberar la cápsula debajo del primer nivel");
    }
}
