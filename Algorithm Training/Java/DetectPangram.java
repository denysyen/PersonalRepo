import java.util.*;
import java.util.regex.Pattern;

public class DetectPangram {
    public boolean check(String sentence){
        int numberToReach = 26;
    
        List<String> charList = new ArrayList<String>();
        String[] sentenceArray = sentence.split("");
        String whiteSpaceCleaning ="";
        for(String character : sentenceArray){
          if(!character.equals(" ")){
            whiteSpaceCleaning += character;
          }
        }
    
        boolean result = false;
        if(whiteSpaceCleaning.length() < numberToReach) {
          result = false;
        } else {
           for(String character : whiteSpaceCleaning.split("")) {
              if(charList.contains(character) == false && Pattern.matches("[a-zA-Z]+", character)) {
                 charList.add(character);
              }
              if(charList.size() == numberToReach) {
                  result = true;            
              }
           }
        }
        return result;
      }
}
