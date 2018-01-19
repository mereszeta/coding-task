package com.akamai.utils;

import com.akamai.model.Job;
import com.akamai.model.TimeUnit;

import java.util.ArrayList;
import java.util.List;

public class SchedulerUtils {

    private static int getMeanCostForStartingWithLength(int idx, TimeUnit[] timeUnits, int jobLength) {
        int sum = 0;
        for(int i = idx;i<idx+jobLength;i++){
            sum+=timeUnits[i].getCurrentCost();
        }
        return sum/jobLength;
    }

    public static List<Integer> calculatePossibleStartPositions(Job job) {
        List<Integer> possiblePositions = new ArrayList<>();
        int distance = job.getPeriod() - job.getDuration();
        for (int i = 0; i <= distance; i++) {
            possiblePositions.add(i);
        }
        return possiblePositions;
    }
    public static int getBestStartingPosition(List<Integer> possiblePositions,TimeUnit[] timeUnits,int jobLength){
        int min = Integer.MAX_VALUE;
        int field=0;
        for (Integer position:possiblePositions){
            int tmp= getMeanCostForStartingWithLength(position,timeUnits,jobLength);
            if(min>tmp) {
                field = position;
                min=tmp;
            }
        }
        return field;
    }

    public static void initTable(TimeUnit[] timeUnits) {
        for (int i = 0; i < timeUnits.length; i++) {
            timeUnits[i] = new TimeUnit();
        }
    }
}
