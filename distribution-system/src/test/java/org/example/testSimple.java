package org.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class testSimple {

    @Test
    public void testmap(){
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(6);
        list.add(7);

        List<Integer> collect = list.stream().map(x -> {
                    //x += 10;
                    return x+10;
                })
                .collect(Collectors.toList());

        System.out.println(collect);

    }
}
