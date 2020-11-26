package com.albk.datastructure.BK20201011.base;

/**
 * @author BK
 * @description: 异或运算
 * 0^x = x   , x ^ x = 0 , a^b = b^a
 * <p>
 * 或操作可以用来在0值中赋值
 * @date 2020-11-26 23:25
 */
public class XOR {

    public static void main(String[] args) {
        //        int[] array = new int[]{1, 2, 3, 4, 5};
        //        swapArray(array , 0, 1);
        //        swap( 4 , 5 );
        //        int[] array = new int[]{1, 2, 3, 2, 3, 2, 3, 2, 3, 1, 1, 1, 88};
        //        System.out.println(findOnlyOccursOddNum(array));
        //        System.out.println(test(8));
        //        int[] array = new int[]{1, 2, 3, 2, 3, 2, 3, 2, 3, 1, 1, 88};
        //        test1(array);
        int[] array = new int[]{8, 3, 1, 3, 3, 1, 1, 8};
        test2(array, 2, 3);
    }

    /**
     * 异或运算在交换
     *
     * @param i
     * @param j
     */
    public static void swap(int i, int j) {
        i = i ^ j;
        j = i ^ j;
        i = i ^ j;
        System.out.println(" i = " + i + " ,j = " + j);
    }

    /**
     * 异或运算在交换 ，当在交换数组时，一定要不能是同一个位置，同一个位置会导致值被刷成0
     *
     * @param i
     * @param j
     */
    public static void swapArray(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
        System.out.println(" array[i] = " + array[i] + " , array[j] = " + array[j]);
    }

    /**
     * 数据中有一种数出现了奇数次， 其它数都出现了偶数次，找出这个出现奇数次的数是谁?
     * 解题思路：将所有数进行异或，相同数偶数次的异或结果为0 ,利用 0 ^x = x
     *
     * @param array
     * @return
     */
    public static int findOnlyOccursOddNum(int[] array) {
        int eor = 0;
        for (int i : array) {
            eor ^= i;
        }
        return eor;
    }

    /**
     * 将一个数，其它位置0 保留最右侧的一个1
     * 用途，可以通过这种方法，将一个数组中的数分成两类，一类是最右侧那位为0 的，另一位是最右侧那为1的
     * 在test1中会使用到这种方法
     * a & ( (~a ) + 1 ) = a & (-a)  ；  一个数取反+1 就是他的负数
     *
     * @param num
     * @return
     */

    public static int test(int num) {
        return num & (~num + 1);
        //        return num & (-num);
    }

    /**
     * 在一个数组中，有2种数出现了奇数次，其它数都出现了偶数次，找出出现了奇数次的2个数a, b , 可推出a一定不等于b
     * 解题思路：
     * 1.把所有数都异或一遍结果eor一定是a^b
     * 2.将eor中找一位不为1的，说明a和b在这一位一定是不一样的，（n我们就取最右侧一位不为1的，利用test方法）
     * 3.将数组分成两类：第n位是1的数的集合A1， 第n位是0的数的集合A2  这种情况，一定会把a,b两个数分到两个区间中
     * 4. 在A1集合中，除了出a出现奇数次，其它数一定出现偶数次 ， 进行异或之后结果就是a
     * 5. 在A2集合中，除了数b出现奇数次，其它数一定出现偶数次 ， 进行异或之后结果就是b
     */
    public static void test1(int[] array) {
        int eor = 0;// a^b
        for (int i : array) {
            eor ^= i;
        }
        //a 和 b 是两种数 所以eor不为0
        //取出eor最右侧为1的数
        //if : eor = 1001010100
        // rightOne= 0000000100
        final int rightOne = eor & (-eor);
        int otherEor = 0;
        for (int i : array) {
            if ((i & rightOne) == 1) {
                otherEor ^= i;
            }
        }
        // otherEor 结果是a 或者 b
        //  a ^ b ^ a => b  ; a^ b ^a => a  ;   eor ^ otherEor => a /b
        int otherOne = eor ^ otherEor;
        System.out.println(otherEor + "  ----  " + otherOne);

    }

    /**
     * 在一个数组中，有一种数出现了K次，其它数都出现了M次， m>1 & k<m
     * 找出出现了k次的数，要求额外空间复杂度为O(n),额外空间复杂度O(1)
     * 解题思路：将所有数的二进制存入一个整型 长度为32的数组tmp，相同位进行累加（如果数组给出来的是整型，最大32位就可以））
     * 对tmp数组进行遍历，逐位与M取余，余数为1的位置X,说明出现K次的数第X位为1（异或））
     */
    public static void test2(int[] array, int k, int m) {
        //[                    ]
        // 31 30 .......3,2,1,0
        int[] t = new int[32];
        for (int num : array) {
            //把int数组转换成 int数组形式的方法
            for (int i = 0; i <= 31; i++) {
                //一个数右移N位与1做与运算，结果为1说明这个数的第N位是1 ，结果为0说明第N位是0
                t[i] += (num >> i) & 1;
                //                if (((num >> i ) & 1 )!=0) {
                //                    t[i]++ ;
                //                }
            }
        }
        int result = 0;
        for (int i = 0; i <= 31; i++) {
            if (t[i] % m != 0) {
                result |= (1 << i);
            }
        }
        System.out.println(result);
    }

}
