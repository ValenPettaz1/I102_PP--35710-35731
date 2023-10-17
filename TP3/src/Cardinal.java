public abstract class Cardinal {

    public abstract Cardinal getRight();
    public abstract Cardinal getLeft();
    public abstract Point getFront();

    public boolean equals( Object obj ) {
        return this == obj ||
                (obj != null &&
                getClass() == obj.getClass());
    }
}
