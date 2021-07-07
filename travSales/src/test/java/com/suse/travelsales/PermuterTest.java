package com.suse.travelsales;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermuterTest {
    @Test
    public void testPermuter() {
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);

        Permuter perm = new Permuter<Integer>();

        List<List<Integer>> permutations = perm.permute(intList);


        System.out.println("Permuations of array are:");
        System.out.println("=========================================");
        for(List<Integer> pm:permutations) {
            System.out.println(pm);
        }
    }
}
