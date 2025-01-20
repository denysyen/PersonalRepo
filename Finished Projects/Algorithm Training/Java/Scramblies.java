import org.apache.commons.lang3.StringUtils;
public class Scramblies {
    public static boolean checkIfAllLetters(String str1, String str2) {
      String[] str2Array = str2.split("");
      int counter = 0;
      for(String letter : str2Array) {
        if(str1.contains(letter)) {
          counter++;
        }
      }
      if(counter == str2Array.length) return true;
      
      return false;
    }
  
    public static boolean scramble(String str1, String str2) { 
      String[] str2Array = str2.split("");
      int length = str2Array.length;
      int matchCounter = 0;
      if(checkIfAllLetters(str1,str2) == true ) {
        int j = 0;
        while(j < length && StringUtils.countMatches(str1,str2Array[j]) >= StringUtils.countMatches(str2,str2Array[j])) {
            matchCounter++;
            j++;
          }
      }
      
      if(matchCounter == str2Array.length) return true;
      return false;
    }
}