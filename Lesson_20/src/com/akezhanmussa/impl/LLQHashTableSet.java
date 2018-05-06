package com.akezhanmussa.impl;

import com.akezhanmussa.adt.HashTableSet;

public class LLQHashTableSet<T> implements HashTableSet<T> {

    private LinkedListQueue<T>[] buckets;
    private int size;
    private int bucketSize;

    public LLQHashTableSet(int bucketSize){
        this.bucketSize = bucketSize;
        buckets = new LinkedListQueue[this.bucketSize];
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
            buckets[index] = new LinkedListQueue<>();
            buckets[index].enqueue(value);
            size++;
        } else {
            if (!contains(value)){
                buckets[index].enqueue(value);
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

        int size = buckets[index].getSize();


        if (size == 0){
            return false;
        }

        try{
            for(int i = 0; i<size; i++){

                T object = buckets[index].dequeue();
                buckets[index].enqueue(object);

                if (value.equals(object)){
                    return true;
                }
            }

            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }

        return false;
    }

    @Override
    public boolean remove(T value) {

        int index = hashFunction(value);

        int size = buckets[index].getSize();

        if (buckets[index] == null){
            return false;
        }

        if (size == 0){
            return false;
        }

        try{
            for(int i = 0; i<size; i++){
                T object = buckets[index].dequeue();
                if (value.equals(object)){
                    size --;
                    return true;
                }
                buckets[index].enqueue(object);
            }

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return false;
    }

    @Override
    public T removeAny() throws Exception {

        if (size == 0){
            throw new Exception("The set is empty");
        }

        T object = (T) new Object();

        for(int i = 0; i<bucketSize; i++){
            if (buckets[i] != null){
                if (buckets[i].getSize() != 0){
                    object = buckets[i].dequeue();
                    size--;
                    break;
                }
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
        buckets = new LinkedListQueue[bucketSize];
        size = 0;
    }

    @Override
    public String toString() {
        String result = "[\n";
        try{
            for(int i = 0; i<bucketSize;i++){
                if (buckets[i] != null){
                    if (buckets[i].getSize() != 0){
                        int k = buckets[i].getSize();
                        for(int j = 0; j<k;j++){
                            T object = buckets[i].dequeue();
                            buckets[i].enqueue(object);
                            result += " " + object.toString();
                        }
                    }
                }
                result += '\n';
                }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return result + "]";
    }

    /*
     The methods of the HashTableStats interface
     */


    @Override
    public int getNumberOfBuckets() {

        return this.bucketSize;
    }

    @Override
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
            if (buckets[i] == null){
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
                if (buckets[i].getSize() != 0){
                    result += " The bucket of index " + i + ": ";
                    result += buckets[i].toString();

                      //Alternative method of this realization))
//                        for(int j = 0; j<k;j++){
//                            T object = buckets[i].dequeue();
//                            buckets[i].enqueue(object);
//
//                            result +=  object.toString() + " ";
//
//                        }
                    }
            } else {
                result += " The bucket of index " + i + ": is empty ";
            }

                result += '\n';

            }
        return result + "]";
    }
}
