import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class EventBusApplication {
    public static void main(String[] args) {

        EventBus bus = new EventBus();
        bus.register(new PrimeNumberListener());
        bus.register(new CompositeNumberListener());

        PrimeGenerator generator = new PrimeGenerator();
        generator.generatePrimes(bus, 1, 1_000_000);
    }

    static class PrimeNumberListener {
        @Subscribe
        void onPrimeNumber(PrimeNumberEvent event) {
            System.out.println("Prime number: " + event.getPrimeNumber());
        }
    }

    static class CompositeNumberListener {
        @Subscribe
        void onCompositeNumber(CompositeNumberEvent event) {
            System.out.println("Composite number: " + event.getCompositeNumber());
        }
    }
}
