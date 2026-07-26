package io.github.lucasrznd.icompras.logistica.utils;

import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

public abstract class TrackingUtil {

    public static String generateTrackingCode() {
        RandomGenerator random = RandomGenerator.getDefault();

        String letters = random.ints(2, 'A', 'Z' + 1)
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());

        int number = random.nextInt(100_000_000, 1_000_000_000);
        return letters + number + "BR";
    }
}
