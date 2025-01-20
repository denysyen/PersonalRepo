public class KataSolution {
    public static long factorial(int n) {
      long prod = 1;
      for (int i = 2; i <= n; i++) {
          prod = prod * i;
      }
      return prod;
    }
    public static long binamialCoef(int n, int k) {
      int diff = n - k;
      
      return factorial(n) / (factorial(k) * factorial(diff));
    }
    public static String createSeriesTerm(int n, int k, String firstTerm, String secondTerm) {
      int diff = n - k;
      double firstTermNum = 0.0;
      double secondTermNum = 0.0;
      if(firstTerm.split("").length == 1) {
        firstTermNum = 1.0;
      } else {
        if(firstTerm.split("").length == 2 && firstTerm.split("")[0].equals("-")) {
           firstTermNum = -1.0;
        } else {
           firstTermNum = Double.valueOf(firstTerm.substring(0,firstTerm.split("").length - 1));
        }
       
      }
      if(secondTerm.isEmpty() != true) {
        secondTermNum = Double.valueOf(Integer.valueOf(secondTerm));
      }
      long numerical = binamialCoef(n,k);
      double powerProd = Math.pow(firstTermNum, diff) * Math.pow(secondTermNum,k);
      numerical = numerical * (long)powerProd;
      String sign = numerical > 0  && k > 0 ? "+" : "";
      String coef = numerical == 1 && n != k ? "" : String.valueOf(numerical);
      
      String letters = firstTerm.split("").length == 1 ? firstTerm.split("")[0] : firstTerm.split("")[firstTerm.split("").length - 1];
      letters = letters  + "^" + String.valueOf(diff); 
      if(numerical == 0) {
         return "";
      }
      return sign + coef + letters;
    }
      public static String expand(String expr) {
      String regex = "[+-]";
      String expansion ="";
      int indexExp =expr.indexOf("^");
      int exponent = indexExp != -1 ? Integer.valueOf(expr.substring(indexExp + 1,expr.split("").length)) : 0;
      if(exponent == 0) {
        return "1";
      }
      int indexMinus = expr.indexOf("-");
      String firstTerm = "";
      if(indexMinus == 1) {
        firstTerm = expr.split("\\(")[1];
        boolean isSecondMinus = firstTerm.split("\\-").length > 2;
        if(isSecondMinus == true) {
          firstTerm = firstTerm.split("\\-")[1].split("\\-")[0];      
        } else {
          firstTerm = firstTerm.split("\\-")[1].split("\\+")[0];
        }
        firstTerm = "-" + firstTerm;
      } else {
        firstTerm = expr.split(regex)[0].split("\\(")[1];
      }
    
       String secondTerm = expr.split("\\(")[1].split("\\)")[0];
      System.out.println("firstTerm ===" + firstTerm);
       if(secondTerm.split("\\-").length == 1) {
         secondTerm = secondTerm.split("\\+")[1];
       }  else if(secondTerm.split("\\-").length == 2) {
         if (indexMinus == 1) {
           secondTerm = secondTerm.split("\\+")[1];
         } else {
           secondTerm = "-" + secondTerm.split("\\-")[1];
         }
       } else {
         secondTerm = "-" + secondTerm.split("\\-")[2];
       }
        if(Math.abs(Integer.valueOf(secondTerm)) == 0) {
          secondTerm = "";
        }
      for(int i = 0; i < exponent+1; i ++) {
        String expansionTerm = createSeriesTerm(exponent,i,firstTerm,secondTerm);
        indexExp = expansionTerm.indexOf("^");
        if(expansionTerm.isEmpty() == false &&  Long.valueOf(expansionTerm.split("")[indexExp + 1]) == 0) {
          expansionTerm = expansionTerm.substring(0,indexExp - 1);
        } else if (expansionTerm.isEmpty() == false && Long.valueOf(expansionTerm.substring(indexExp + 1, expansionTerm.split("").length)) == 1) {
           expansionTerm = expansionTerm.substring(0,indexExp);
        } else if(expansionTerm.split("")[0].equals("-") && Long.valueOf(expansionTerm.substring(0,indexExp-1)) == -1) {
          expansionTerm = "-"+ expansionTerm.substring(2,expansionTerm.split("").length);
        }
        expansion += expansionTerm;
      }
          return expansion;
      }
  }
