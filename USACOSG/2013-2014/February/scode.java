import java.util.*;
import java.io.*;

public class scode{
    public static HashMap<String, Integer> map;
    public static void main(String[] args) throws Exception {
        BufferedReader f = new BufferedReader(new FileReader("scode.in"));
        String s = f.readLine();
        map = new HashMap<String, Integer>();
        
        PrintWriter out = new PrintWriter(new File("scode.out"));
        out.println((count(s) - 1 + 2014) % 2014);
        out.close();
    }
    public static int count(String s) {
      if(map.containsKey(s)) {
          return map.get(s);
      }
      int ans = 1;
      for(int i = 1; i * 2 < s.length(); i++) {
          if(s.substring(0, i).equals(s.substring(s.length() - i, s.length()))){
              ans += count(s.substring(i, s.length()));
          }
          if(s.substring(0, i).equals(s.substring(i, 2 * i))){
              ans += count(s.substring(i, s.length()));
          }
          if(s.substring(0, i).equals(s.substring(s.length() - i, s.length()))) {
              ans += count(s.substring(0, s.length() - i));
          }
          if(s.substring(s.length() - i * 2, i + s.length() - i * 2).equals(s.substring(s.length() - i, s.length()))) {
              ans += count(s.substring(0, s.length() - i));
          }
      }
      
      ans %= 2014;
      map.put(s, ans);
      return ans;
    }
}
