public class CompositeNumberEvent {
    private final int compositeNumber;

    public CompositeNumberEvent(int compositeNumber) {
        this.compositeNumber = compositeNumber;
    }

    public int getCompositeNumber() {
        return compositeNumber;
    }

    public static CompositeNumberEvent of(int number) {
        return new CompositeNumberEvent(number);
    }
}
