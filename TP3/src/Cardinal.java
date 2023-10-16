public abstract class Cardinal {

    public abstract Cardinal turnRight();
    public abstract Cardinal turnLeft();
    public abstract Point getFront();

    public boolean equals( Object obj ) {
        return this == obj ||
                (obj != null &&
                getClass() == obj.getClass());
    }
}
