package cw;
public class Interval {
    public static int sumIntervals(int[][] intervals) {
        int length = intervals.length;
        int diffSum = 0;
        int[] intervalMin = new int[length];
        int[] intervalMax = new int[length];
        for(int i = 0; i < length; i++) {
          intervalMin[i] = intervals[i][0];
          intervalMax[i] = intervals[i][1];
        }     
        for (int i = 0; i <= length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
              if (intervalMin[j] >= intervalMin[i] && intervalMin[j] < intervalMax[i] && intervalMax[i] < intervalMax[j]) {
                    intervalMin[j] = intervalMax[i];
               } else if (intervalMin[i] <= intervalMin[j] && intervalMin[j] < intervalMax[i]) {
                  if( intervalMin[i] < intervalMax[j] && intervalMax[j] <= intervalMax[i]) {
                     intervalMax[j] = intervalMin[j];
                  }
               } else if(intervalMin[i] <= intervalMax[j] && intervalMax[j] < intervalMax[i]) {
                 if(intervalMin[j] < intervalMin[i]) {
                    intervalMax[j] = intervalMin[i];
                 }
               } else if(intervalMin[j] <= intervalMin[i] && intervalMax[i] <= intervalMax[j]) {
                   intervalMax[i] = intervalMin[i];
               }                
            }
          
        }

      for (int i = 0; i < length; i++) {
        diffSum += intervalMax[i] - intervalMin[i];
      }        
      return diffSum;
    }
}