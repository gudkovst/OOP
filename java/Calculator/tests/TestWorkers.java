package tests;

import exceptions.ParserException;
import exceptions.WorkerException;

import org.junit.Test;
import parsers.Parser;
import workers.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

import static org.junit.Assert.*;

public class TestWorkers {
    Stack<Double> stack = new Stack<>();
    HashMap<String, Double> varBase = new HashMap<>();
    Parser parser = new Parser();
    Factory factory = new Factory();

    public TestWorkers() throws IOException {
    }

    private void init(Stack<Double> stack){
        stack.push(25.0);
        stack.push(15.0);
    }

    @Test
    public void StackTest() throws ParserException, WorkerException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Worker poper = factory.createWorker(parser.parse("POP"));
        Worker pusherNum = factory.createWorker(parser.parse("PUSH 16"));
        Worker definer = factory.createWorker(parser.parse("DEFINE d 9"));
        Worker pusherVar = factory.createWorker(parser.parse("PUSH d"));
        Worker nuller = factory.createWorker(parser.parse("# comment"));
        Worker printer = factory.createWorker(parser.parse("PRINT"));

        assertTrue(nuller instanceof NullWorker);
        assertTrue(poper instanceof PopWorker);
        assertTrue(pusherNum instanceof PushWorker);
        assertTrue(pusherVar instanceof PushWorker);
        assertTrue(definer instanceof DefineWorker);
        assertTrue(printer instanceof PrintWorker);

        nuller.execute(stack, varBase);
        assertTrue(stack.isEmpty());
        assertTrue(varBase.isEmpty());

        definer.execute(stack, varBase);
        assertTrue(stack.isEmpty());
        assertFalse(varBase.isEmpty());
        assertTrue(varBase.containsKey("d"));

        pusherVar.execute(stack, varBase);
        assertFalse(stack.isEmpty());
        assertTrue(stack.contains(9.0));
        assertEquals(1, stack.size());

        pusherNum.execute(stack, varBase);
        assertTrue(stack.contains(16.0));
        assertEquals(2, stack.size());

        stack.push(5.0);
        poper.execute(stack, varBase);
        assertEquals(2, stack.size());
        assertFalse(stack.contains(5.0));

        printer.execute(stack, varBase);
        assertEquals(2, stack.size());
    }

    @Test
    public void DivTest() throws ParserException, WorkerException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Stack<Double> localStack = new Stack<>();
        Worker div = factory.createWorker(parser.parse("/"));
        assertTrue(div instanceof DivWorker);
        init(localStack);
        div.execute(localStack, varBase);
        assertEquals(1, localStack.size());
        assertTrue(localStack.contains(0.6));
    }

    @Test
    public void MinusTest() throws ParserException, WorkerException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Stack<Double> localStack = new Stack<>();
        Worker minus = factory.createWorker(parser.parse("-"));
        assertTrue(minus instanceof MinusWorker);
        init(localStack);
        minus.execute(localStack, varBase);
        assertEquals(1, localStack.size());
        assertTrue(localStack.contains(-10.0));
    }

    @Test
    public void MulTest() throws ParserException, WorkerException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Stack<Double> localStack = new Stack<>();
        Worker mul = factory.createWorker(parser.parse("*"));
        assertTrue(mul instanceof MulWorker);
        init(localStack);
        mul.execute(localStack, varBase);
        assertEquals(1, localStack.size());
        assertTrue(localStack.contains(375.0));
    }

    @Test
    public void PlusTest() throws ParserException, WorkerException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Stack<Double> localStack = new Stack<>();
        Worker plus = factory.createWorker(parser.parse("+"));
        assertTrue(plus instanceof PlusWorker);
        init(localStack);
        plus.execute(localStack, varBase);
        assertEquals(1, localStack.size());
        assertTrue(localStack.contains(40.0));
    }

    @Test
    public void SqrtTest() throws ParserException, WorkerException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Stack<Double> localStack = new Stack<>();
        Worker sqrt = factory.createWorker(parser.parse("SQRT"));
        assertTrue(sqrt instanceof SqrtWorker);
        init(localStack);
        localStack.pop();
        sqrt.execute(localStack, varBase);
        assertEquals(1, localStack.size());
        assertTrue(localStack.contains(5.0));
    }
}
