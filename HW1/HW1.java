/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author MINHPV64
 */
class HW1 {

    /**
     * @param args the command line arguments
     */
    static long M1(long[] arr) {     
        long sum = 0;
        for (long i : arr) sum += i;
        return sum;
    }
    
    static ArrayList<Character> M2(String str) {
        char tmp = ' ';
        ArrayList<Character> rs = new ArrayList<>();
        Map<Character, Integer> dict = new HashMap<>();
        int maxOccur = 0;
        
        for (int i=0; i< str.length(); ++i) {
            char ch = str.charAt(i);
            
            if (dict.containsKey(ch)) {
                dict.put(ch, dict.get(ch) + 1);
            } else dict.put(ch, 1);
            
            if (dict.get(ch) > maxOccur) maxOccur = dict.get(ch);
        }
        
        for (Map.Entry<Character, Integer> i : dict.entrySet()) {
            if (i.getValue() == maxOccur) {
                rs.add(i.getKey());
            }
        }
        
        return rs;
    }
    
    static long[] M3(long[] arr) {
        Arrays.sort(arr);
        return arr;
    }
    
    static boolean M4(long num) {
        if (num <= 1) return false;
        else if (num == 2) return true;
        for (long i=2; i <= Math.sqrt(num); ++i) {
            if (num%i==0) return false;
        }
        return true;
    }
    
    static void M5(long a, long b, long c) {
        double p = (a+b+c)/2;
        
        try {
            if (p*(p-a)*(p-b)*(p-c) <= 0) throw new Exception();
            System.out.println("Triangle is valid and its area : " + Math.sqrt(p*(p-a)*(p-b)*(p-c)));
        } catch (Exception e) {
            System.out.println("Triangle is invalid !");
        }
    }
    
    static void M6(long radius) {
        double dis;
        for (long row = 0; row <= 2*radius; ++row) {
            for (long col = 0; col <= 2*radius; ++col) {
                dis = Math.sqrt((row-radius)*(row-radius) + (col-radius)*(col-radius));
                if (dis < radius + 0.5 && dis > radius - 0.5) System.out.print("*");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) throws IOException{
        
        HW1 hw1 = new HW1();
        
        String[] options = {"1. Sum Array",
                            "2. Char Frequency",
                            "3. Array Sorting",
                            "4. Prime Checking",
                            "5. Triangle Area",
                            "6. Draw Circle",
                            "7. Exit"
        };
        
        Scanner scanner = new Scanner(System.in);
        int opt = 1; 
        while (opt!=7) {
            System.out.println("***************** HW Panel *****************");
            for (String i:options) System.out.println(i);
            System.out.println("********************************************");
            System.out.print("Choose your option : ");
            opt = scanner.nextInt();
            
            while (opt < 1 || opt > 7) {
                System.out.println("Please enter an integer value between 1 and 7 !");
                opt = scanner.nextInt();
            }
            
            switch (opt){
                case 1:
                    System.out.println("Type your array (separated by space) :");
                    BufferedReader bi = new BufferedReader( new InputStreamReader(System.in));
                    String[] strNums = bi.readLine().split(" ");
                    long arr[] = new long[strNums.length];
                    
                    for (int i = 0; i < strNums.length; i++) {
                        arr[i] = Long.parseLong(strNums[i]);
                    }
                    
                    System.out.println("Sum of elements :");
                    System.out.println(M1(arr));
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.println("Type your string :");
                    String str = scanner.nextLine();
                    System.out.println("Most freq char : ");
//                    System.out.println(M2(str));
                    for (char i:M2(str)) System.out.print(i + " ");
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Type your array (separated by space) :");
                    BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
                    String[] strNums1 = br.readLine().split(" ");
                    long arr1[] = new long[strNums1.length];
                    
                    for (int i = 0; i < strNums1.length; i++) {
                        arr1[i] = Long.parseLong(strNums1[i]);
                    }
                    
                    System.out.println("Sorted Array :");
                    long rs[] = M3(arr1);
                    for (long i : rs) System.out.print( i + " ");
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("Type your number : ");
                    long num = scanner.nextLong();
                    System.out.println("Is prime number? ");
                    System.out.println(M4(num));
                    break;
                case 5:
                    System.out.println("Type your edge length : ");
                    System.out.print("a = ");
                    long a = scanner.nextLong();
                    System.out.print("b = ");
                    long b = scanner.nextLong();
                    System.out.print("c = ");
                    long c = scanner.nextLong();
//                    System.out.print("Area of your triangle : ");
                    M5(a, b, c);
                    break;
                case 6:
                    System.out.println("Type your radius :");
                    long radius = scanner.nextInt();
                    System.out.println("Your Circle :");
                    M6(radius);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    exit(0);
            }
        }
    }
}
