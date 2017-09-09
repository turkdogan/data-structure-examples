package io.github.turkdogan.datastructures;

import org.junit.Test;
import org.junit.Assert;

import java.util.Optional;

/**
 * Unit test for TStack.
 */
public class StackTest {

    @Test
    public void testCreate() {
        TStack<Integer> stack = new TStack<>();
        Assert.assertEquals(0, stack.size());
    }

    @Test
    public void testPush() {
        TStack<Integer> stack = new TStack<>();
        stack.push(10);
        stack.push(11);
        stack.push(12);
        stack.push(13);

        Assert.assertEquals(4, stack.size());
    }

    @Test
    public void testPop() {
        TStack<Integer> stack = new TStack<>();
        stack.push(10);
        stack.push(11);
        stack.push(12);
        stack.push(13);

        Assert.assertEquals(4, stack.size());
        Optional<Integer> value = stack.pop();
        Assert.assertEquals(3, stack.size());
        Assert.assertTrue(value.isPresent());
        Assert.assertEquals(Integer.valueOf(13), value.get());

        value = stack.pop();
        Assert.assertEquals(2, stack.size());
        Assert.assertTrue(value.isPresent());
        Assert.assertEquals(Integer.valueOf(12), value.get());

        value = stack.pop();
        Assert.assertEquals(1, stack.size());
        Assert.assertTrue(value.isPresent());
        Assert.assertEquals(Integer.valueOf(11), value.get());

        value = stack.pop();
        Assert.assertEquals(0, stack.size());
        Assert.assertTrue(value.isPresent());
        Assert.assertEquals(Integer.valueOf(10), value.get());

        value = stack.pop();
        Assert.assertEquals(0, stack.size());
        Assert.assertFalse(value.isPresent());
    }

    @Test
    public void testPeek() {
        TStack<Integer> stack = new TStack<>();
        stack.push(10);

        Assert.assertEquals(1, stack.size());
        Optional<Integer> value = stack.peek();
        Assert.assertEquals(1, stack.size());
        Assert.assertTrue(value.isPresent());
        Assert.assertEquals(Integer.valueOf(10), value.get());
    }
}
