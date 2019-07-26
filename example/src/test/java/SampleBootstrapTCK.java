import junit.framework.Test;
import org.atinject.tck.Tck;
import org.atinject.tck.auto.Car;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class SampleBootstrapTCK {

    public static Test suite() {

        SeContainer container = SeContainerInitializer.newInstance().initialize();
        Car tckCar = container.select(Car.class).get();
        return Tck.testsFor(tckCar, false, true);
    }
}
