public class Surface extends Depth{

    @Override
    public Point ascendMe(Nemo nemo) {
        return nemo.getPosition();
    }

    @Override
    public Point descendMe(Nemo nemo) {
        nemo.addDepthLevel(new FirstLevel());
        return nemo.getPosition().add(Point.Down);
    }

    @Override
    public void dropCapsule(){}
}