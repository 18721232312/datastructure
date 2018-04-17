package com.albk.datastructure.algorithm.recursion;

/**
 * @author BK
 * @version V2.0
 * @description:  泊松分酒 穷举算法
 * @team: ALBK
 * @date 2018/4/18 0:02
 */
public class ShareWine {
    //大瓶子容量
    private int largeBottle;
    //中间瓶子容量
    private int middleBottle;
    //小瓶子容量
    private int smallBottle;
    //需要分出的目标容量
    private int target;

    public ShareWine(int largeBottle, int middleBottle, int smallBottle, int target) {
        this.largeBottle = largeBottle;
        this.middleBottle = middleBottle;
        this.smallBottle = smallBottle;
        this.target = target;
    }

    private void tryDoIt(int largeRemain, int middleRemain, int smallRemain) {
        System.out.println("largeBottle=" + largeRemain + ", middleBottle=" + middleRemain + ", smallBottle=" + smallRemain);
        if (largeRemain == target || middleRemain == target || smallRemain == target) {
            return;
        }
        //如果中间的瓶子有酒 ，并且小瓶子不满，尝试着去往小瓶子放酒
        if (middleRemain != 0 && smallRemain != smallBottle) {
            //中间瓶子的酒都放到小瓶子，会溢出
            if (middleRemain + smallRemain >= smallBottle) {
                tryDoIt(largeRemain, middleRemain - (smallBottle - smallRemain), smallBottle);
            } else {
                //不会溢出
                tryDoIt(largeRemain, 0, smallRemain + middleRemain);
            }
        } else if (largeRemain != 0 && smallRemain == smallBottle) { //大瓶子有酒，并且小瓶子满了，开始往大瓶子放酒
            //如果小瓶子的酒都放到大瓶子，会溢出 ，则尝试着把大瓶子装满，小瓶子剩余一部分酒
            if (largeRemain + smallRemain >= largeBottle) {
                tryDoIt(largeBottle, middleRemain, smallRemain - (largeBottle - largeRemain));
            } else {
                //如果小瓶子放到大瓶子不会溢出 ，则把小瓶子清空，把酒都放到大瓶子里面
                tryDoIt(largeRemain + smallRemain, middleRemain, 0);
            }
        } else if (middleRemain != middleBottle && largeBottle == largeRemain) {//如果中间瓶子没满，大瓶子满了，则把大瓶子的酒往中间瓶子装
            //如果大瓶子酒放到中间瓶子，会溢出，则把中间瓶子装满，大瓶子剩余部分酒，再继续尝试分酒
            if (middleRemain + largeRemain >= middleBottle) {
                tryDoIt(largeRemain - (middleBottle - middleRemain), middleBottle, smallRemain);
            } else {
                //如果大瓶子酒放到中间瓶子，不会溢出，则把大瓶子清空，把酒都装到中间瓶子
                tryDoIt(0, middleRemain + largeRemain, smallRemain);
            }
        }
    }

    public static void main(String[] args) {
        ShareWine wine = new ShareWine(12, 8, 5, 5);
        wine.tryDoIt(12, 0, 0);
    }

}
