package org.dark0ghost.math_generator_example.random_object;

import java.util.Objects;

public class ObjectRandom implements CustomRandom {

    private int getAddress(){
        return Objects.hash(new Object());
    }

    @Override
    public int randomNumber(int begin, int end) {
        int hash = getAddress();
        int difference = end - begin;
        return begin + (hash % difference);
    }
}
