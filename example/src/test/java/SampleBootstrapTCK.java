import junit.framework.Test;
import org.atinject.tck.Tck;
import org.atinject.tck.auto.Car;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

//import javax.enterprise.inject.se.SeContainer;
//import javax.enterprise.inject.se.SeContainerInitializer;

public class SampleBootstrapTCK {

    public static Test suite() {

        /*
        SeContainer container = SeContainerInitializer.newInstance().initialize();
        //container.getBeanManager().createAnnotatedType()

        Car tckCar = container.select(Car.class).get();

         */
        Weld weld = new Weld();

        WeldContainer container = weld.initialize();

        Car tckCar = container.select(Car.class).get();

        return Tck.testsFor(tckCar, false, true);
    }
}
