package org.george.jylishop.db;


import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

/**
 * Created by Yulya on 31.07.2016.
 */
public class Factorial {


    public long factorialCalc(int n) {

        long result = 1;
        for (int i = 1; i <= n; ++i) result *= i;
        return result;

    }

    public int inputQuery() {
        System.out.print("Ведіть число,факторіал якого ви хочете обчислити: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        return i;
    }


    public static void main(String[] args) {

        System.out.println(recursive(11));


//        while (true) {
//            Factorial factorial=new Factorial();
//            int i=factorial.inputQuery();
//            System.out.println();
//            System.out.println("Факторіал числа " + i + " складає " + factorial.factorialCalc(i));
//            System.out.println();
//            System.out.println(factorial.hashCode());
//
//
//        }


    }

    public static long recursive(long n) {
        if (n == 0) {
            return 1;
        }

        return recursive(n - 1) * n;
    }

}
