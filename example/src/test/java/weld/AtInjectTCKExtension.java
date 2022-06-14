/*
 * Copyright 2021, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package weld;

import org.atinject.tck.auto.Convertible;
import org.atinject.tck.auto.Drivers;
import org.atinject.tck.auto.DriversSeat;
import org.atinject.tck.auto.accessories.SpareTire;
import org.jboss.weld.literal.NamedLiteral;

import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.Extension;
import jakarta.enterprise.inject.spi.ProcessAnnotatedType;
import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AtInjectTCKExtension implements Extension {
    public void convertible(@Observes ProcessAnnotatedType<Convertible> pat) {
        pat.configureAnnotatedType()
                .filterFields(field -> "spareTire".equals(field.getJavaMember().getName()))
                // add @Spare to the injection point, because the bean doesn't have @Default; see also below
                .forEach(field -> {field.add(SpareLiteral.INSTANCE); System.out.printf("Added @Spare to %s\n", field.getAnnotated());});
    }

    public void driversSeat(@Observes ProcessAnnotatedType<DriversSeat> pat) {
        pat.configureAnnotatedType()
                .add(DriversLiteral.INSTANCE);
        System.out.printf("Added @Drivers to: %s\n", pat.getAnnotatedType());
    }

    public void spareTire(@Observes ProcessAnnotatedType<SpareTire> pat) {
        pat.configureAnnotatedType()
                .add(new NamedLiteral("spare"))
                // add @Spare to prevent adding @Default, otherwise there would be 2 beans for the Tire type
                .add(SpareLiteral.INSTANCE);
        System.out.printf("Added @Named('spare') to: %s\n", pat.getAnnotatedType());
    }

    static final class DriversLiteral extends AnnotationLiteral<Drivers> implements Drivers {
        static final DriversLiteral INSTANCE = new DriversLiteral();

        private DriversLiteral() {
        }
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @interface Spare {
    }

    static final class SpareLiteral extends AnnotationLiteral<Spare> implements Spare {
        static final SpareLiteral INSTANCE = new SpareLiteral();

        private SpareLiteral() {
        }
    }
}
