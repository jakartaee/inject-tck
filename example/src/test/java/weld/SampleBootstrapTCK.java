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

import junit.framework.Test;
import org.atinject.tck.Tck;
import org.atinject.tck.auto.Car;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class SampleBootstrapTCK {

    public static Test suite() {

        SeContainer container = SeContainerInitializer
                .newInstance()
                .addExtensions(AtInjectTCKExtension.class)
                .addPackages(true, Tck.class)
                .initialize();
        Car tckCar = container.select(Car.class).get();
        return Tck.testsFor(tckCar, false, true);
    }
}
