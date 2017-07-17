package com.sperasoft.sie.tools.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by Ilya K on 7/16/2017.
 */
public class Tree<T> {
    private T data;
    private List<Tree<T>> childTrees;
    private List<Node<T>> childNodes;
    private Tree<T> parent;

    public Tree(T data) {
        this.data = data;
        this.childTrees = new ArrayList<>();
        this.childNodes = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public List<Tree<T>> getChildTrees() {
        return childTrees;
    }

    public List<Node<T>> getChildNodes() {
        return childNodes;
    }

    public Tree<T> getParent() {
        return parent;
    }

    private void setParent(Tree<T> parent) {
        this.parent = parent;
    }

    public void addChildTree(Tree<T> treeToAdd) {
        treeToAdd.setParent(this);
        this.childTrees.add(treeToAdd);
    }

    public void addChildNode(Node<T> nodeToAdd) {
        this.childNodes.add(nodeToAdd);
    }

    public boolean hasChildTrees() {
        return  !this.getChildTrees().isEmpty();
    }

    public boolean hasChildNodes() {
        return !this.getChildNodes().isEmpty();
    }

    public boolean hasParent() {
        return Objects.nonNull(this.getParent());
    }

    public void visitChildTrees(Consumer<Tree<T>> treeConsumer, Consumer<Node<T>> nodeConsumer) {
        while (this.hasChildTrees()) {
            this.getChildTrees().forEach(tTree -> tTree.visitChildTrees(treeConsumer, nodeConsumer));
        }
        this.visitChildNodes(nodeConsumer);
    }

    public void visitChildNodes(Consumer<Node<T>> nodeConsumer) {
        if (this.hasChildNodes()) {
            this.getChildNodes().forEach(nodeConsumer);
        }
    }

    public Tree<T> getTreeByValue(T value) {
        if (Objects.isNull(this.getParent()) && this.getData().equals(value) ) {
            return this;
        }
        return this.getChildTrees().stream()
                .filter(tTree -> tTree.getData().equals(value))
                .findFirst()
                .orElse(null);
    }

    public void traverse(Consumer<Node<T>> nodeConsumer, Consumer<Tree<T>> treeConsumer) {
        this.visitChildNodes(nodeConsumer);
        this.getChildTrees().forEach(tTree -> tTree.visitChildTrees(treeConsumer, nodeConsumer));
    }
}
