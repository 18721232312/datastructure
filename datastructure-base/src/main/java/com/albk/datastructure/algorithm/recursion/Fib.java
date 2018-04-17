package com.albk.datastructure.algorithm.recursion;

/**
 * @author BK
 * @version V2.0
 * @description:  fib
 * @team: ALBK
 * @date 2018/4/17 23:58
 */
public class Fib {

    private long fib(int n){
        if(n ==1){
            return n ;
        }
        return n*fib(n-1);
    }

    public static void main(String[] args) {
        Fib fib = new Fib();
        System.out.println(fib.fib(4));
    }

    /**
     * @author BK
     * @version V2.0
     * @description: 求最大公约数   欧几里德算法
     *  (m>n) m和n 的最大公约数 = n和m%n的最大公约数
     *  36 ，24 -> 24 ,12-> 12 , 0   n=0  return 12
     *
     * @team: ALBK
     * @date 2018/4/16 23:58
     */
    public static class Gcd {

        public int gcd(int m, int n) {
            if (n == 0) {
                return m;
            }
            return gcd(n, m % n);
        }

        public static void main(String[] args) {
            Gcd g = new Gcd();
            System.out.println(g.gcd(55,44));
            System.out.println(g.gcd(36,24));
        }
    }
}
