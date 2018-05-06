package com.akezhanmussa.test;

import com.akezhanmussa.adt.HashTableSet;
import com.akezhanmussa.adt.Set;
import com.akezhanmussa.impl.BSTSet;
import com.akezhanmussa.impl.BSTSetHashTableSet;
import com.akezhanmussa.impl.LLQHashTableSet;
import com.akezhanmussa.impl.TreeNode;

import java.util.Random;




public class Main {


    public static boolean someFunction(int value, int[] placeForFind){

        for(int i = 0; i<placeForFind.length; i++){

            if (value == placeForFind[i]){
                return true;
            }

        }

        return false;
    }

    public static int finder(TreeNode<Integer> root){
        if (root.getLeft() == null && root.getRight() == null){
            return root.getValue();
        } else if (root.getRight() != null && root.getLeft() == null){
            int val = root.getValue();
            int val2 = finder(root.getRight());

            if (val >= val2){
                return val;
            } else
                return val2;

        } else {
            int val = root.getValue();
            int val1 = finder(root.getLeft());

            if (val >= val1){
                return val;
            } else
                return val1;

        }
    }



    public static void main(String[] args) {
//
//        int[] array = {1,2,3,4,5};
//        boolean checker = false;
//
//        int x = 5;
//
//        checker = someFunction(x, array);
//        System.out.println(checker);
//
//
//
//        HashTableSet<Integer> llqHashTable = new LLQHashTableSet<>(10);
//
//        System.out.println("Starting timing tests...");
//        long time1, time2, duration;
//        Random randomGenerator = new Random();
//        int randomNumber = randomGenerator.nextInt();
//
//        time1 = System.currentTimeMillis();
//
//        for (int i = 0; i < 50000; i++) {
//            llqHashTable.add(randomNumber);
//            randomNumber = randomGenerator.nextInt();
//        }
//
//        time2 = System.currentTimeMillis();
//        duration = time2 - time1;
//
//        System.out.println("Time taken in ms: " + duration);
//        System.out.println("The standard deviation is " + llqHashTable.getBucketSizeStandardDev());
//        System.out.println("The load factor is " + llqHashTable.getLoadFactor());
//        System.out.println();
//
//       HashTableSet<Integer> bstHashTable = new BSTSetHashTableSet(10);
//
//        System.out.println("Starting timing tests...");
//        time1 = System.currentTimeMillis();
//
//        for (int i = 0; i < 50000; i++) {
//            bstHashTable.add(randomNumber);
//            randomNumber = randomGenerator.nextInt();
//        }
//
//        time2 = System.currentTimeMillis();
//        duration = time2 - time1;
//
//        System.out.println("Time taken in ms: " + duration);
//        System.out.println("The standard deviation is " + bstHashTable.getBucketSizeStandardDev());
//        System.out.println("The load factor is " + bstHashTable.getLoadFactor());
////        System.out.println(bstHashTable.bucketsToString());
////        System.out.println(llqHashTable.bucketsToString());
//
//
//        HashTableSet<Integer> some = new BSTSetHashTableSet(100);
//        System.out.println(some.getSize());
//        some.add(2);
//        some.add(3);
//        some.add(23);
//
//        System.out.println(some.getSize());

        BSTSet<Integer> someSet = new BSTSet<>();
        someSet.add(23);
        someSet.add(12);
        someSet.add(89);
        someSet.add(131);
        someSet.add(212);

        System.out.println(finder(someSet.root));



    }
}
