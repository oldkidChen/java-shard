package com.shard.lambda;

/**
 * @author chen_jinglong
 * @description: 函数式接口
 * @date 2023/8/1 17:07
 */

interface CustomInterface{
    public Integer sum(Integer a, Integer b);
}
public class FunctionInterfaceExample {

    public static void main(String[] args) {
        // 传统调用接口方法
        CustomInterface customInterface = new CustomInterface() {
            @Override
            public Integer sum(Integer a, Integer b) {
                return a + b;
            }
        };
        Integer result = customInterface.sum(1,2);
        System.out.println(result);


        //lambda调用接口方法
        //lambda表达式适用于只有一个抽象方法的接口，被称为函数式接口
        CustomInterface custom1 = (a, b) -> a + b;
        Integer result1 = custom1.sum(1,2);
        System.out.println(result1);

        // ===实用比如创建线程===
        // 传统写法
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        thread1.start();

        // lambda写法
        Thread thread2 = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        thread2.start();
    }
}
