package com.izzan.eyediseasesdiagnosismobile;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aizen on 4 Jul 2017.
 */

public class CorrelationMeasurement {
    public int[][] metaData1 = {
            {0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1}
    };

    public int[][] metaData2 = {
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1}
    };

    public CorrelationMeasurement() {
    }

    public int[] compute1(int[] input) {
        if (input.length != metaData1[0].length) {
            return new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        }

        int[] result1 = new int[metaData1.length];

        //inner-product

        for(int d = 0; d < metaData1.length; d++){
            int sum = 0;
            for(int x = 0; x < metaData1[0].length; x++){
                sum = sum + (input[x] * metaData1[d][x]);
            }
            result1[d] = sum;
        }

        return result1;
    }

    public int[] compute2(int[] result1){
        int[] result2 = new int[metaData2.length];

        for(int s = 0; s < metaData2.length; s++){
            int sum = 0;
            for(int d = 0; d < metaData2[0].length; d++){
                sum = sum + (result1[d] * metaData2[s][d]);
            }
            result2[s] = sum;
        }

        return result2;
    }

    public int getHighestResult(int[] result){
        int highest = result[0];

        for(int i = 1; i < result.length; i++){
            if(result[i] > highest){
                highest = result[i];
            }
        }

        return highest;
    }

    public List getHighestResult1Index(int[] result1){
        int highest = getHighestResult(result1);
        List<Long> indexList = new ArrayList<>();

        Log.i("HIGHEST_RESULT_1", String.valueOf(highest));

        for(int i = 0; i < result1.length; i++){
            if(result1[i] == highest){
                Log.i("HIGHEST_RESULT_1_INDEX", String.valueOf(i));
                indexList.add((long) i);
            }
        }

        return indexList;
    }


    public long getHighestResult2Index(int[] result2){
        int highest = getHighestResult(result2);
        long index = 0;

        Log.i("HIGHEST_RESULT_2", String.valueOf(highest));

        for(int i = 0; i < result2.length; i++){
            if(result2[i] == highest){
                Log.i("HIGHEST_RESULT_2_INDEX", String.valueOf(i));
                index = (long) i;
            }
        }
        Log.i("HIGHEST_RESULT_2_INDEX", String.valueOf(index));
        return index;
    }


}
