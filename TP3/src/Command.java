public abstract class Command {
    /*public Point moveFoward (Nemo nemo) {
        return Point.add(nemo.getDirection().getFront());
    }*/
    public abstract Nemo operateMe(Nemo nemo);
}
