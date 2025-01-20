import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class PickPeaks {
    public static class Maximus {
        int position;
        int value;
        Maximus(int position, int value) {
            this.position = position;
            this.value = value;
        }
    }

    public static List<Maximus> getAllMaxInfo(int[] arr) {
        List<Maximus> peaks = new ArrayList<>();
        int length = arr.length;
  
        if (length < 3) return peaks;
  
        for (int i = 1; i < length - 1; i++) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                peaks.add(new Maximus(i, arr[i]));
            } else if (arr[i] > arr[i - 1] && arr[i] == arr[i + 1]) {
                int j = i + 1;
                while (j < length && arr[j] == arr[i]) {
                    j++;
                }
                if (j < length && arr[i] > arr[j]) {
                    peaks.add(new Maximus(i, arr[i]));
                }
                i = j - 1;
            }
        }
        return peaks;
      }
      public static HashMap<String,List<Integer>> getPeaks(int[] arr) {
        // Your code here!
        List<Maximus>maximuList = getAllMaxInfo(arr);
        int length = maximuList.size();
        HashMap<String,List<Integer>> maximumMap = new HashMap<String,List<Integer>>(); 
        List<Integer> setPosList = new ArrayList<Integer>();
        List<Integer> setMaxList = new ArrayList<Integer>();
      
        for(int i = 0; i < length; i++) {
          setMaxList.add(maximuList.get(i).value);
          setPosList.add(maximuList.get(i).position);
        }
      
        maximumMap.put("pos",setPosList);
        maximumMap.put("peaks",setMaxList);
      
        return maximumMap;
    }
}
