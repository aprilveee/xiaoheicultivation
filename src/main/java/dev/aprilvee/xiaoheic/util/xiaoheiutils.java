package dev.aprilvee.xiaoheic.util;

public class xiaoheiutils {


    public static int arrayMax(int[] array){
        int max = array[0];
        for(int i = 1; i<array.length;i++){
            max = Math.max(max,array[i]);
        }
        return max;
    }

    public static int sumArray(int[] array){
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }

    public static float arrayMaxf(float[] array){
        float max = array[0];
        for(int i = 1; i<array.length;i++){
            max = Math.max(max,array[i]);
        }
        return max;
    }

    public static float sumArrayf(float[] array){
        float sum = 0;
        for (float value : array) {
            sum += value;
        }
        return sum;
    }


}
