package utils;

import java.util.Random;

public class RandomNumbers {

    public static int generate(int bound) {
        return new Random().nextInt(bound);
    }
}