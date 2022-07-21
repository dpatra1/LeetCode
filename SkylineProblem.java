package com.chegg.boots.appl.user.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Time : O(NlogN)
 * Space : O(N)
 * N: Output value to keep track of the result
 */
public class SkylineProblem {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();

        //1. Mark the start and end point of each building
        List<int[]> buildStartAndEnd = new ArrayList<>();
        for(int[] building: buildings){
            buildStartAndEnd.add(new int[]{building[0], -building[2]}); // Start of the building
            buildStartAndEnd.add(new int[]{building[1], building[2]}); // End of the building
        }

        // 2. Mark all consecutive buildings. Sort them by start point
        Collections.sort(buildStartAndEnd, (a,b)->{
            if(a[0] != b[0]){
                return a[0] - b[0];
            }else{
                return a[1] - b[1]; // This is when starting point for both of the buildings are same So we have to sort them by height
            }
        });

        // 3. We need to get max Y value for each X axis value
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)-> (b-a));
        pq.offer(0);

        int prevHeight = 0;
        // 4. Everytime there is a change in height, we have an answer
        for(int[] building :buildStartAndEnd){
            if(building[1] < 0){
                pq.offer(-building[1]);
            }else{
                pq.remove(building[1]);
            }

            int currHeight = building[1];
            if(currHeight != prevHeight){
                List<Integer> tempResult = new ArrayList<>();
                tempResult.add(building[0]); // X Value
                tempResult.add(currHeight); // Y Value
                result.add(tempResult);

                prevHeight = currHeight;
            }
        }
        return result;
    }
}
