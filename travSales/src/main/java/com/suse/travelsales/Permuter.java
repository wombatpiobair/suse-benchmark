package com.suse.travelsales;

import java.util.ArrayList;
import java.util.List;

public class Permuter<T> {

    public List<List<T>> permute(List<T> originalList) {
        List<List<T>> list = new ArrayList<>();
        permuteHelper(list, new ArrayList<T>(), originalList);
        return list;
    }


    private void permuteHelper(List<List<T>> list, ArrayList<T> resultList, List<T> orig) {
        // Base case
        if (resultList.size() == orig.size()) {
            list.add(new ArrayList<>(resultList));
        } else {
            for (int i = 0; i < orig.size(); i++) {

                if (resultList.contains(orig.get(i))) {
                    // If element already exists in the list then skip
                    continue;
                }
                // Choose element
                resultList.add(orig.get(i));
                // Explore
                permuteHelper(list, resultList, orig);
                // Unchoose element
                resultList.remove(resultList.size() - 1);
            }
        }
    }
}


