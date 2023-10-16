public class LowerLevels extends Depth{

    @Override
    public Point ascendMe(Nemo nemo) {
        nemo.remove();
        return nemo.getPosition().add(new Point(0, 0, 1));

    }

    @Override
    public Point descendMe(Nemo nemo) {
        nemo.add(new LowerLevels());
        return nemo.getPosition().add(new Point(0, 0, -1));
    }

    @Override
    public Object dropCapsule(){
        return null;
    }
}
