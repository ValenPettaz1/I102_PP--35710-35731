public class Surface extends Depth{

    @Override
    public Point ascendMe(Nemo nemo) {
        return nemo.getPosition();
    }

    @Override
    public Point descendMe(Nemo nemo) {
        nemo.add(new FirstLevel());
        return nemo.getPosition().add(new Point(0, 0, -1));
    }

    @Override
    public void dropCapsule(){
    }


}
