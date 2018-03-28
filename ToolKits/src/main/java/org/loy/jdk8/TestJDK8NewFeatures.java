package org.loy.jdk8;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;
import org.loy.jdk8.beans.Shop;

public class TestJDK8NewFeatures {

    @Test
    public void testStreamFlatMap() {
        List<List<String>> lists = new ArrayList<>();
        lists.add(Arrays.asList("apple", "click"));
        lists.add(Arrays.asList("boss", "dig", "qq", "vivo"));
        lists.add(Arrays.asList("c#", "biezhi"));

        long shopCount = lists.stream().flatMap(Collection::stream).filter(str -> str.length() > 2).count();

        System.out.println("Worlds longger than 2 chars are " + shopCount);
        assertTrue("test", Boolean.TRUE);
    }

    @Test
    public void testStream() {
        Shop p1 = new Shop("Chicken", 1000, 500, 2);
        Shop p2 = new Shop("Dunplin", 2300, 1500, 3);
        Shop p3 = new Shop("BeanDrink", 580, 3000, 1);
        Shop p4 = new Shop("Kendky", 6000, 200, 4);
        List<Shop> properties = Arrays.asList(p1, p2, p3, p4);
        Collections.sort(properties, (x, y) -> x.distance.compareTo(y.distance));
        String name = properties.get(0).name;
        System.out.println("Traditional approach result is : " + name);

        String name2 = properties.stream().sorted(Comparator.comparingInt(x -> x.distance)).findFirst().get().name;
        System.out.println("Using stream result is : " + name2);

        long salesCount = properties.stream().filter(p -> p.sales > 1000).count();
        System.out.println("Sales more than 1000 count is : " + salesCount);
    }

}
