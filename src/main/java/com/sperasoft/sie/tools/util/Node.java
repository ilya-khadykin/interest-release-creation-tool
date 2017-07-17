package com.sperasoft.sie.tools.util;

/**
 * Created by Ilya K on 7/16/2017.
 */

//TODO: should probably rename to TreeLeaf or something similar
public class Node<T> {
    private T data;
    private Tree<T> parent;


    public Node(T data, Tree<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    public T getData() {
        return data;
    }

    public Tree<T> getParent() {
        return parent;
    }
}
