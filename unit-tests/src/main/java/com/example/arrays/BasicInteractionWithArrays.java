package com.example.arrays;

import java.util.ArrayList;

public class BasicInteractionWithArrays {

    public static Double getMaxValueFromArray(ArrayList<Double> arrayList) {
        Double max = arrayList.get(0);
        for (Double element : arrayList) {
            if (element > max) max = element;
        }
        return max;
    }

    public static Double getMeanValueFromArray(ArrayList<Double> arrayList) {
        int N = arrayList.size();
        Double sum = 0.0;
        for (Double element : arrayList) {
            sum += element;
        }
        return sum/N;
    }

    public static ArrayList<Double> copyOneArrayToAnother(ArrayList<Double> arrayList) {
        ArrayList<Double> arrayListCopy = new ArrayList<>();
        for (Double element : arrayList) {
            arrayListCopy.add(element);
        }
        return arrayListCopy;
    }

    public static ArrayList<Double> getReverseOrderedArray(ArrayList<Double> arrayList) {
        int N = arrayList.size();
        for(int i=0; i<N/2; i++) {
            Double temp = arrayList.get(i);
            arrayList.set(i, arrayList.get(N-1-i));
            arrayList.set(N-i-1, temp);
        }
        return arrayList;
    }
}
