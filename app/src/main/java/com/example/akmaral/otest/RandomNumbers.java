package com.example.akmaral.otest;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class RandomNumbers {

    public static ArrayList<Integer> getRandomData(int size, int kol) {

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random randomGenerator = new Random(1);
        while (numbers.size() < kol) {

            int random = randomGenerator.nextInt(101);
            if (!numbers.contains(random)) {
                numbers.add(random);
                Log.e("random", random + "");
            }


        }
        return numbers;

    }
}
