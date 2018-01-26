package com.android.ahivaran.utils;


import com.android.ahivaran.R;

import java.util.Random;

public class RandomColor {
    public static int getRandomColor() {
        int randomColorArray[] = {R.color.first_color, R.color.second_color,
                R.color.third_color, R.color.floor_random_color};
        return randomColorArray[new Random().nextInt(randomColorArray.length - 1)];
    }
}
