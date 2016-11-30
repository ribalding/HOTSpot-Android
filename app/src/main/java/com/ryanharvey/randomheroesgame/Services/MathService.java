package com.ryanharvey.randomheroesgame.Services;

import java.util.Random;

/**
 * Created by Ryan on 11/27/2016.
 */

public class MathService {

    //Generate Random Number Between 0 and a provided int
    public static int generateRandomNumber(int max){
        Random rand = new Random();
        return rand.nextInt(max);
    }
}
