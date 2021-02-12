package org.dark0ghost.math_generator_example.random_object;

import java.util.Objects;

public class ObjectRandom implements CustomRandom {

    private int getAddress(){
        return Objects.hash(new Object());
    }

    @Override
    public int randomNumber(int begin, int end){
        int hash = getAddress();
        int difference = end - begin;
        return begin + (hash % difference);
    }

    public long randomNumber(long begin, long  end){
        int  hash = getAddress();
        long  difference = end - begin;
        return begin + (hash % difference);
    }

    public float randomNumber(float begin, float  end){
        int  hash = getAddress();
        float  difference = end - begin;
        return begin + (hash % difference);
    }

    public double randomNumber(double  begin, double   end){
        int hash = getAddress();
        double  difference =  (end - begin);
        return begin + (hash % difference);
    }
}
