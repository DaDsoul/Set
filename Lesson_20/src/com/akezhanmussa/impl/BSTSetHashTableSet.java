package com.akezhanmussa.impl;

import com.akezhanmussa.adt.HashTableSet;

public class BSTSetHashTableSet<T extends Comparable> implements HashTableSet<T>{

    private BSTSet<T>[] buckets;
    private int size;
    private int bucketSize;

    public BSTSetHashTableSet(int bucketSize){
        this.bucketSize = bucketSize;
        buckets = new BSTSet[bucketSize];
        size = 0;
    }

    private int hashFunction(T object){

        int value = object.hashCode();

        value = Math.abs(value) % bucketSize;

        return value;
    }

    @Override
    public void add(T value) {

        int index = hashFunction(value);

        if (buckets[index] == null){
            buckets[index] = new BSTSet();
            buckets[index].add(value);
            size++;
        } else {
            if (!buckets[index].contains(value)){
                buckets[index].add(value);
                size ++;
            }
        }

    }

    @Override
    public boolean contains(T value) {

        int index = hashFunction(value);

        if (buckets[index] == null){
            return false;
        }

        if (buckets[index].contains(value)){
            return true;
        }

        return false;
    }

    @Override
    public boolean remove(T value) {

        int index = hashFunction(value);

        if (buckets[index] == null){
            return false;
        }

        if (buckets[index].remove(value)){
            size =- 1;
            return true;
        }

        return false;
    }

    @Override
    public T removeAny() throws Exception {

        if (size == 0){
            throw new Exception("The set is empty");
        }

        T object = null;

        for(int i = 0; i<bucketSize; i++){
            if (buckets[i] != null){
                object = buckets[i].removeAny();
                this.size --;
                break;
            }
        }

        return object;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void clear() {
        buckets = new BSTSet[bucketSize];
        size = 0;
    }

    @Override
    public String toString() {
        String result = "[\n";

        for(int i = 0; i<bucketSize;i++){

            if (buckets[i] != null){
                    result += buckets[i].toString();
                    result += '\n';
                }
            }


        return result + "]";
    }


    /*
     The methods of the HashTableStats interface
     */

    @Override
    public int getNumberOfBuckets() {

        return bucketSize;
    }

    public int getBucketSize(int index) throws Exception {

        if (index < 0 || index >= bucketSize){
            throw new Exception("The buckets with these indexes do not exist!!!");
        }

        return buckets[index].getSize();
    }

    @Override
    public double getLoadFactor() {

        return this.size/this.bucketSize;
    }

    @Override
    public double getBucketSizeStandardDev() {
        double[] bucketArray = new double[bucketSize];
        double mean = size/bucketSize;
        double stdv = 0;

        for(int i = 0; i<bucketSize; i++){
            if (buckets[i] == null || buckets[i].getSize() == 0){
                bucketArray[i] = 0;
            } else {
                bucketArray[i] = buckets[i].getSize();
            }
        }

        for(int i = 0; i<bucketSize; i++){
            stdv += Math.pow(bucketArray[i] - mean, 2);
        }

        stdv /= bucketSize;
        stdv = Math.pow(stdv, 0.5);

        return stdv;
    }

    @Override
    public String bucketsToString() {
        String result = "[\n";

        for(int i = 0; i<bucketSize;i++){
            if (buckets[i] != null){
                result += " The bucket of index " + i + ": ";
                result += buckets[i].toString();
            }else {
                result += " The bucket of index " + i + ": is empty ";
            }
            result += '\n';
        }

        return result + "]";
    }
}
