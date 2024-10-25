import java.util.ArrayList;
import java.util.List;

class Solution {

		public static String rangeExtraction(int[] arr) {
    		StringBuilder range = new StringBuilder();
        int lowerEnd = arr[0];
        int upperEnd = lowerEnd;
        int length = arr.length;
      
        for (int i = 1; i < length; i++) {
            if (arr[i] == upperEnd + 1) {
                upperEnd = arr[i];
            } else {
                if (upperEnd == lowerEnd) {
                    range.append(lowerEnd);
                } else if (upperEnd - lowerEnd >= 2) {
                    range.append(lowerEnd).append("-").append(upperEnd);
                } else {
                    range.append(lowerEnd).append(",").append(upperEnd);
                }
                if (i < length - 1) {
                    range.append(",");
                }
                lowerEnd = arr[i];
                upperEnd = lowerEnd;
            }
        }
        if (lowerEnd == upperEnd ) {
            range.append(",").append(lowerEnd);
        } else if (upperEnd - lowerEnd >= 2) {
            range.append(lowerEnd).append("-").append(upperEnd);           
        } else {
            range.append(lowerEnd).append(",").append(upperEnd);
        }

        return range.toString();
    }
}
