public abstract class Box {

    static Box filledBox(Object cargo) {
        return new filledBox(cargo);
    }
    static Box emptyBox() {
        return new emptyBox();
    }

    public abstract Object openBox ();

}

