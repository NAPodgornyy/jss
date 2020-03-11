//package hw.acm;

import java.io.*;
import java.util.*;

public class DevMax {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        int value, n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = in.nextInt();
        for (int j = 0; j < 2; j++) {
            value = Arrays.stream(arr)
                    .max()
                    .getAsInt();
            for (int i = 0; i < arr.length; i++)
                if (arr[i] == value) {
                    arr[i] /= (int) 2;
                }
        }
        out.println(arrPrint(arr));
        out.flush();
    }

    public static String arrPrint(int [] arr){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < arr.length; i++){
            res.append(arr[i]).append(" ");
        }
        return res.toString();
    }
}