import me.bush.eventbus.bus.EventBus;
import me.bush.eventbus.handler.handlers.ReflectHandler;

/**
 * Started: 12/2/2021
 *
 * @author bush
 */
public class Main {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        TestClass testClass = new TestClass();
        TestEvent event = new TestEvent(":)");

        // Post event before subscribing (does nothing)
        eventBus.post(event);

        eventBus.subscribe(testClass);
        System.out.println("Subscribed object.");

        // Post event (only object is subscribed, so only instance methods will be invoked)
        eventBus.post(event);

        eventBus.subscribe(TestClass.class);
        System.out.println("Subscribed class.");

        // Post event (class is subscribed, so static methods will be invoked too)
        eventBus.post(event);

        event.setCancelled(true);
        System.out.println("Cancelled event.");

        // Post cancelled event (only "RecieveCancelled" should be invoked)
        eventBus.post(event);

        eventBus.getInfo();

        eventBus.setHandlerType(ReflectHandler.class);
        System.out.println("Changed listener type.");

        eventBus.getInfo();
    }
}
