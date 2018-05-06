package com.akezhanmussa.impl;

import com.akezhanmussa.adt.Set;

/**
 * Created by talgat on 08.03.18.
 */
public class BSTSet<T extends Comparable> implements Set<T>{

    public TreeNode<T> root;
    private int size;

    public BSTSet() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (size == 0){
            root = new TreeNode(value);
            size++;
        } else {
            addHelper(root, value);
        }
    }

    public void addHelper(TreeNode<T> node, T value){

        if (node.getValue().compareTo(value) < 0){
            if (node.getRight() == null){
                node.setRight(new TreeNode<>(value));
                size++;
            } else {
                addHelper(node.getRight(), value);
            }
        } else if (node.getValue().compareTo(value) > 0){
            if (node.getLeft() == null){
                node.setLeft(new TreeNode<>(value));
                size++;
            } else {
                addHelper(node.getLeft(), value);
            }
        }
    }

    @Override
    public boolean contains(T value) {

        return containsHelper(root, value);
    }

    @Override
    public boolean remove(T value) {

        return removeHelper(root, root, false, value);

    }

    private boolean removeHelper(TreeNode<T> node, TreeNode<T> preNode, boolean isRight,T value){

        if (node == null){
            return false;
        }

        if (value.equals(node.getValue())){
            if (node.getLeft() == null && node.getRight() == null){
                if (size == 1){
                    root = null;
                } else {
                    if (isRight){
                        preNode.setRight(null);
                    } else {
                        preNode.setLeft(null);
                    }
                }
            } else if (node.getLeft() == null && node.getRight() != null){
                if (node == root){
                    root = node.getRight();
                } else {
                    if(isRight){
                        preNode.setRight(node.getRight());
                    } else {
                        preNode.setLeft(node.getRight());
                    }
                }

            } else if (node.getLeft() != null && node.getRight() == null){
                if (node == root){
                    root = node.getLeft();
                } else {
                    if(isRight){
                        preNode.setRight(node.getLeft());
                    } else {
                        preNode.setLeft(node.getLeft());
                    }
                }
            } else {
                TreeNode<T> minimumLeaf = minimumLeafFinder(node.getRight(), node);

                if (node == root){
                    root.setValue(minimumLeaf.getValue());
                }else {
                    node.setValue(minimumLeaf.getValue());
                }
            }

            size --;

            return  true;
        } else {

            return removeHelper(node.getRight(), node, true, value) || removeHelper(node.getLeft(), node, false, value);
        }
    }

    private TreeNode<T> minimumLeafFinder(TreeNode<T> node, TreeNode<T> prevNode){

        if (node.getLeft() == null){

            if (node.getRight() != null){
                if (prevNode == root) {
                    prevNode.setValue(node.getValue());
                    prevNode.setRight(node.getRight());
                } else {
                    prevNode.setLeft(node.getRight());
                }
            } else {
                if (prevNode == root){
                    prevNode.setValue(node.getValue());
                    prevNode.setRight(null);
                } else {
                    prevNode.setLeft(null);
                }
            }

            return node;
        }

        return minimumLeafFinder(node.getLeft(), node);
    }

    @Override
    public T removeAny() throws Exception {

        //alternative method
//        if (size == 0){
//            throw new Exception("The set is Empty!");
//        }

//       T rootValue = root.getValue();
//        remove(rootValue);
//        size --;

        TreeNode<T> deletedNode = new TreeNode(null);

        if (size == 0){
            throw new Exception("The Set is Empty Bro!");
        } else {
            deletedNode = minimumLeafFinder(root,root);
        }

        size --;

        return deletedNode.getValue();
    }


    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void clear() {
        this.root = null;
        size = 0;
    }

    private boolean containsHelper(TreeNode<T> node, T value){
        if (node == null){
            return false;
        }

        if (value.compareTo(node.getValue()) > 0){
            return containsHelper(node.getRight(),value);
        } else if (value.compareTo(node.getValue()) < 0){
            return containsHelper(node.getLeft(), value);
        } else {
            return true;
        }
//The worse alternative method: ineffective, always checks the whole tree
//        return value.equals(node.getValue())
//                || (containsHelper(node.getRight(), value) && value.compareTo(node.getValue()) > 0)
//                || (containsHelper(node.getLeft(), value)&& value.compareTo(node.getValue()) < 0);
    }

    public String toString(){
        return toStringHelper(root);
    }

    private String toStringHelper(TreeNode<T> node){
        if (node == null){
            return " ";
        }

        return toStringHelper(node.getLeft()) +
                node.getValue() +
                toStringHelper(node.getRight());
    }
}
