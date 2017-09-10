package io.github.turkdogan.datastructures;

import org.junit.Test;
import org.junit.Assert;

import java.util.Optional;

/**
 * Unit test for TQueue.
 */
public class QueueTest {

    @Test
    public void testCreate() {
        TQueue<Integer> stack = new TQueue<>();
        Assert.assertEquals(0, stack.size());
    }

    @Test
    public void testEnqueue() {
        TQueue<Integer> stack = new TQueue<>();
        stack.enqueue(10);
        stack.enqueue(11);
        stack.enqueue(12);
        stack.enqueue(13);

        Assert.assertEquals(4, stack.size());
    }

    @Test
    public void testDequeue() {
        TQueue<Integer> stack = new TQueue<>();
        stack.enqueue(10);
        stack.enqueue(11);
        stack.enqueue(12);
        stack.enqueue(13);

        Assert.assertEquals(4, stack.size());
        Optional<Integer> value = stack.dequeue();
        Assert.assertEquals(3, stack.size());
        Assert.assertTrue(value.isPresent());
        Assert.assertEquals(Integer.valueOf(10), value.get());

        value = stack.dequeue();
        Assert.assertEquals(2, stack.size());
        Assert.assertTrue(value.isPresent());
        Assert.assertEquals(Integer.valueOf(11), value.get());

        value = stack.dequeue();
        Assert.assertEquals(1, stack.size());
        Assert.assertTrue(value.isPresent());
        Assert.assertEquals(Integer.valueOf(12), value.get());

        value = stack.dequeue();
        Assert.assertEquals(0, stack.size());
        Assert.assertTrue(value.isPresent());
        Assert.assertEquals(Integer.valueOf(13), value.get());

        value = stack.dequeue();
        Assert.assertEquals(0, stack.size());
        Assert.assertFalse(value.isPresent());
    }

    @Test
    public void testPeek() {
        TQueue<Integer> stack = new TQueue<>();
        stack.enqueue(10);

        Assert.assertEquals(1, stack.size());
        Optional<Integer> value = stack.peek();
        Assert.assertEquals(1, stack.size());
        Assert.assertTrue(value.isPresent());
        Assert.assertEquals(Integer.valueOf(10), value.get());
    }
}
