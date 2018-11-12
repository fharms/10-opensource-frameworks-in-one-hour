public class PrimeNumberEvent {
    private final int primeNumber;

    public PrimeNumberEvent(int primeNumber) {
        this.primeNumber = primeNumber;
    }

    public int getPrimeNumber() {
        return primeNumber;
    }

    public static PrimeNumberEvent of(int number) {
        return new PrimeNumberEvent(number);
    }
}
