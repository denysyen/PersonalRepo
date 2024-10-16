public class CountIpAddress {
    public static long ipsBetween(String start, String end) {
        return fromIntToLong(end) - fromIntToLong(start);
      }
      private static long fromIntToLong(String ipAddress) {
        String[] parts = ipAddress.split("\\.");
        int[] octets = new int[4];
        long result = 0;
    
        for (int i = 0; i < 4; i++) {
            octets[i] = Integer.parseInt(parts[i]);
        }
    
        for (int i = 0; i < octets.length; i++) {
            result = (result << 8) + octets[i];
        }
    
        return result;
      }
}
