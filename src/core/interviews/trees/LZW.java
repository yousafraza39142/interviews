package interviews.trees;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * LZW encoding.
 * Represent variable-length symbols with fixed-length codes.
 * @author Francois Rousseau
 */
public class LZW {
  public static final int R = 128;  // number of input chars
  public static final int L = 256;  // 2^W, number of codewords
  public static final int W = 8;    // codeword width

  /**
   * Decode the input String using LZW.
   * @throws IOException 
   */
  public static void decode() throws IOException {
    Scanner stdin = new Scanner(System.in);
    PrintWriter stdout = new PrintWriter(System.out);

    String[] st = new String[L];
    int i;  // next available codeword value

    for(i = 0; i < R; i++) {  // initialize symbol table with all single character strings
      st[i] = "" + (char) i;
    }
    st[i++] = "";  // (unused) lookahead for EOF

    byte[] input = stdin.next().getBytes();
    int j = 0;  // pointer in input

    int codeword = input[j++];
    String val = st[codeword];

    while(true) {
      stdout.write(val);
      codeword = (char) (input[j++] & 0xff);      // unsigned byte
      if(codeword == R) break;
      String s = st[codeword];
      if(i == codeword) s = val + val.charAt(0);  // special case hack, s not yet in the st
      if(i < L) st[i++] = val + s.charAt(0);
      val = s;
    }
    stdout.flush();
  }

  /**
   * Encode the input String using LZW.
   * @throws IOException 
   */
  public static void encode() throws IOException {
    Scanner stdin = new Scanner(System.in);
    DataOutputStream stdout = new DataOutputStream(System.out);

    // read the input
    String input = stdin.next();
    TrieMap<Integer> st = new TrieMap<Integer>();

    for(int i = 0; i < R; i++) {                  // codewords for single character, radix R keys 
      st.put("" + (char) i, i);
    }
    int code = R + 1;

    while(input.length() > 0) {
      String s = st.longestPrefix(input);         // find longest prefix match s
      stdout.writeByte(st.get(s));
      int t = s.length();
      if(t < input.length() && code < L) {
        st.put(input.substring(0, t+1), code++);  // add new codeword 
      }
      input = input.substring(t);                 // scan past s in input
    }
    stdout.writeByte(R);                          // write "stop" codeword 
    stdout.flush();
  }
}
