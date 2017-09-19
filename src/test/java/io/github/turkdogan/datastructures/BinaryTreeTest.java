package io.github.turkdogan.datastructures;

import org.junit.Test;
import org.junit.Assert;

import java.util.List;

/**
 * Unit test for TBinaryTree.
 */
public class BinaryTreeTest {

    @Test
    public void testCreate() {
        TBinaryTree<Integer> tree = new TBinaryTree<>();
        Assert.assertEquals(0, tree.size());
    }

    @Test
    public void testInsert() {
        TBinaryTree<Integer> tree = new TBinaryTree<>();
        tree.insert(10);
        Assert.assertEquals(1, tree.size());
        tree.insert(20);
        Assert.assertEquals(2, tree.size());
        tree.insert(4);
        Assert.assertEquals(3, tree.size());
    }

    @Test
    public void testSearch() {
        TBinaryTree<Integer> tree = new TBinaryTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(4);

        Assert.assertTrue(tree.search(10));
        Assert.assertTrue(tree.search(20));
        Assert.assertTrue(tree.search(4));
        Assert.assertFalse(tree.search(5));
    }

    @Test
    public void testInOrder() {
        TBinaryTree<Integer> tree = new TBinaryTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(4);
        final List<Integer> inOrder = tree.inOrder();
        Assert.assertEquals(3, inOrder.size());
        Assert.assertEquals(4, (int)inOrder.get(0));
        Assert.assertEquals(10, (int)inOrder.get(1));
        Assert.assertEquals(20, (int)inOrder.get(2));
    }

    @Test
    public void testPreOrder() {
        TBinaryTree<Integer> tree = new TBinaryTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(4);
        final List<Integer> preOrder = tree.preOrder();
        Assert.assertEquals(3, preOrder.size());
        Assert.assertEquals(10, (int)preOrder.get(0));
        Assert.assertEquals(4, (int)preOrder.get(1));
        Assert.assertEquals(20, (int)preOrder.get(2));
    }

    @Test
    public void testPostOrder() {
        TBinaryTree<Integer> tree = new TBinaryTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(4);
        final List<Integer> postOrder = tree.postOrder();
        Assert.assertEquals(3, postOrder.size());
        Assert.assertEquals(10, (int)postOrder.get(0));
        Assert.assertEquals(4, (int)postOrder.get(1));
        Assert.assertEquals(20, (int)postOrder.get(2));
    }

    @Test
    public void testDeleteEmptyTree() {
        TBinaryTree<Integer> tree = new TBinaryTree<>();
        Assert.assertFalse(tree.delete(10));
        Assert.assertEquals(0, tree.size());
    }

    @Test
    public void testDeleteSingleElement() {
        TBinaryTree<Integer> tree = new TBinaryTree<>();
        tree.insert(10);
        Assert.assertTrue(tree.delete(10));
        Assert.assertFalse(tree.delete(20));
        Assert.assertEquals(0, tree.size());
    }

    @Test
    public void testDeleteRootWithSingleChild() {
        TBinaryTree<Integer> tree = new TBinaryTree<>();
        tree.insert(10);
        tree.insert(20);
        Assert.assertTrue(tree.delete(10));
        Assert.assertEquals(1, tree.size());
        Assert.assertTrue(tree.delete(20));
        Assert.assertEquals(0, tree.size());
    }

    @Test
    public void testDeleteLeafElement() {
        TBinaryTree<Integer> tree = new TBinaryTree<>();
        tree.insert(10);
        tree.insert(20);
        Assert.assertTrue(tree.delete(20));
        Assert.assertEquals(1, tree.size());

        tree.insert(5);
        Assert.assertTrue(tree.delete(5));
        Assert.assertEquals(1, tree.size());
    }

    @Test
    public void testDelete() {
        TBinaryTree<Integer> tree = new TBinaryTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(4);
        tree.insert(12);
        tree.insert(18);

        Assert.assertTrue(tree.delete(10));
        Assert.assertEquals(4, tree.size());

        List<Integer> inOrder = tree.inOrder();
        Assert.assertEquals(4, (int)inOrder.get(0));
        Assert.assertEquals(12, (int)inOrder.get(1));
        Assert.assertEquals(18, (int)inOrder.get(2));
        Assert.assertEquals(20, (int)inOrder.get(3));

        Assert.assertTrue(tree.delete(20));
        Assert.assertEquals(3, tree.size());
        inOrder = tree.inOrder();
        Assert.assertEquals(4, (int)inOrder.get(0));
        Assert.assertEquals(12, (int)inOrder.get(1));
        Assert.assertEquals(18, (int)inOrder.get(2));

        Assert.assertTrue(tree.delete(4));
        Assert.assertEquals(2, tree.size());
        inOrder = tree.inOrder();
        Assert.assertEquals(12, (int)inOrder.get(0));
        Assert.assertEquals(18, (int)inOrder.get(1));

        Assert.assertTrue(tree.delete(12));
        Assert.assertEquals(1, tree.size());
        inOrder = tree.inOrder();
        Assert.assertEquals(18, (int)inOrder.get(0));

        Assert.assertTrue(tree.delete(18));
        Assert.assertEquals(0, tree.size());
        inOrder = tree.inOrder();
        Assert.assertEquals(0, inOrder.size());

        Assert.assertFalse(tree.delete(5));
    }
}
