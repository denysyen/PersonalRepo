import java.util.*;
public class Kata {
  public static void swapping(Integer[] digits, int i, int j) { 
      Integer tmp = digits[i]; 
      digits[i] = digits[j]; 
      digits[j] = tmp; 
  } 
  public static Long formatingNumber(Integer[] digits) {
    String result = "";
    for(int i = 0; i < digits.length; i++) {
      result += String.valueOf(digits[i]);
    }
    return Long.valueOf(result);
  }
   public static Integer[] getIntArray(String[] stringArray) {
     int length = stringArray.length;
     Integer[] intArray = new Integer[length]; 
     for(int i = 0; i < length; i++) {
      intArray[i] = Integer.valueOf(stringArray[i]);
    }
     return intArray;
   }
  public static Integer[] getReverseSorted(Integer[] intArray) {
    Arrays.sort(intArray);
    int length = intArray.length;
    Integer[] reverseArray =  new Integer[length];
    for(int i = 0; i < length; i++) {
      reverseArray[i] = intArray[length - 1 - i];
    }
    return reverseArray;
  }
  public static long nextSmaller(long n) {
    String[] stringArray = Long.toString(n).split("");
    int length = stringArray.length;
    int index = length - 1;
    int minimun = -1;
    int nextMax = 0;
    Integer[] intArray = getIntArray(stringArray);
    Integer[] trial = intArray;
    Arrays.sort(trial);
    intArray = getIntArray(stringArray);

    if(length == 1 || intArray[0] == 0 || Arrays.equals(trial, intArray)) {      
      return -1;
    }
    for( int j = length - 1; j > 0; j--) { 
      if(intArray[j] < intArray[j - 1]) {
        index -= 1;
        break;
      }
      index -= 1;
    } 
    
    for(int i = index; i < length; i++) {
      if(intArray[i] < intArray[index] && intArray[i] > minimun) {
        minimun = intArray[i];
        nextMax = i;
      }
    }
    swapping(intArray, index, nextMax);
    Integer[] subArrayInt = Arrays.copyOfRange(intArray, index + 1, length);
    trial = subArrayInt;
    Arrays.sort(trial);
    subArrayInt = Arrays.copyOfRange(intArray, index + 1, length);
    if(Arrays.equals(trial, subArrayInt) && intArray[0] == 0) {
      return -1;
    }
    subArrayInt = getReverseSorted(subArrayInt);
    for(int i = 0; i < subArrayInt.length; i++) {
      intArray[i + index + 1] = subArrayInt[i];
    }
      return formatingNumber(intArray);

  }
}
