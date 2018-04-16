package com.albk.datastructure.algorithm;

/**
 * @author BK
 * @version V2.0
 * @description: 汉诺塔
 * @team: ALBK
 * @date 2018/4/16 22:01
 */
public class Hanota {
    private int i = 1;
    public void hanota(int n, String from, String dependOn, String to) {
        if (n == 1) {
            move(n, from, to);
        } else {
            hanota(n - 1, from, to, dependOn);
            move(n, from, to);
            hanota(n - 1, dependOn, from, to);
        }
    }

    private void move(int n, String from, String to) {
        System.out.println("第" + i++ + "步，从" + from + "->>>>>>>" + to);
    }

    public static void main(String[] args) {
        Hanota hanota = new Hanota();
        hanota.hanota(3, "a", "b", "c");
    }

}
