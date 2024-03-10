import java.util.*;
public class Kata {
  public static String firstToNotRepeat (String string, String[] characters) {
    String first ="";
    boolean isFirst = false;
    boolean market = false;
    List<String> marketList = new ArrayList<String>();
    int length = characters.length;
    for(int i = 0; i < length; i++) {
        String stringPart = string.substring(i+1,length);
        market = marketList.stream().anyMatch(characters[i]::equals); 
        boolean check = stringPart.contains(characters[i].toLowerCase()) || stringPart.contains(characters[i].toUpperCase());
        if(check == true) {
            string = string.replaceAll(characters[i].toLowerCase(), " ");
            string = string.replaceAll(characters[i].toUpperCase(), " ");
            marketList.add(characters[i].toLowerCase());
            marketList.add(characters[i].toUpperCase());
        } 
      
        if(check == false && isFirst == false && !string.isBlank() && market == false) {
          isFirst = true;
          first = characters[i];
        }
    }
    return first;
  }
  public static String firstNonRepeatingLetter(String s){
      if(s.split("").length == 1) {
        return s;
      } 
      return firstToNotRepeat(s, s.split(""));
  }
}