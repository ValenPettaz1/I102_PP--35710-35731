public abstract class Box {

    static Box filledBox(Object cargo) {
        return new FilledBox(cargo);
    }
    static Box emptyBox() {
        return new EmptyBox();
    }

    public abstract Object openBox ();

}

