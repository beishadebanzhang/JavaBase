package com.example.javabase.util.optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionTest {

    /**
     * Optional可用于简化判空操作, 减少代码深度
     * Optional不应该作为参数传递 --> optional本身可能为null
     * Optional不应该作为结果的属性传递 --> optional未实现序列化接口, jdk序列化会报错
     */
    @Test
    public void test() {
        Result result = new Result();
        // result = null;
        String str = Optional.ofNullable(result) // 若result为空, Optional.ofNullable返回内置empty向下执行
                // .filter() 过滤器
                // .flatMap() 用在返回值不是普通对象，是 Optional 包裹的对象的场景
                .map(Result::getAddress) // 此时拿到的如果是empty, 经过ifPresent()判断, 继续返回empty
                .map(Address::getProvince) // 同上
                .map(Province::getName) // 同上
                // .orElse("123") // 若为empty, 则返回指定值
                // .orElseThrow(() -> new ArithmeticException("111")) // 若为empty, 抛出指定异常
                .orElseGet(() -> "123") // 若为empty, 执行指定lambda表达式, orElse和orElseGet无论是否为empty都执行, 判断option是否为empty
                ;
        System.out.println(str);
    }

    @Test
    public void testOf() {
        String str = null;
        // null则返回内部的empty, 一个包装了null的optional对象
        Optional<String> optional = Optional.ofNullable(str);
        // 内部调用构造函数 --> Objects.requireNonNull --> null报空指针
        optional = Optional.of(str);
    }

    @Test
    public void testPresent() {
        Optional<String> optional = Optional.ofNullable("123");
        // 判断optional内的值是否不为空
        System.out.println(optional.isPresent());
        // 不为空则执行lambda表达式
        optional.ifPresent(value -> System.out.println(value));
    }

    @Test
    public void testGet() {
        Optional<String> optional = Optional.ofNullable("123");
        // value为null报空指针
        System.out.println(optional.get());
        // value为null返回指定值
        System.out.println(optional.orElse("值为null"));
        // value为null执行lambda表达式
        System.out.println(optional.orElseGet(() -> "值为null"));
    }

}
