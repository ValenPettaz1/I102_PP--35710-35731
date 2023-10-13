public abstract class Cardinal {

    private String name;

    public Cardinal(String name) {
        this.name = name;
    }
    public String getDirection() {
        return name;
    }

    public abstract Cardinal getRight();
    public abstract Cardinal getLeft();
    public abstract Point getFront();

    public boolean equals( Object obj ) {
        return this == obj ||
                (obj != null &&
                getClass() == obj.getClass()) &&
                name.equals( ((Cardinal)obj).name );
    }
}
