import java.util.Stack;

class MinStack {
    // Stack stores Long to prevent integer overflow during 2 * val - min
    private Stack<Long> stack;
    private long min;

    public MinStack() {
        stack = new Stack<>();
    }
    
    public void push(int val) {
        long value = val;
        // Base case: if stack is empty, this value is the minimum
        if (stack.isEmpty()) {
            min = value;
            stack.push(value);
        } else {
            if (value >= min) {
                // If value is greater than or equal to current min, push normally
                stack.push(value);
            } else {
                // If value is smaller, encode the previous min before updating it
                stack.push(2 * value - min);
                min = value;
            }
        }
    }
    
    public void pop() {
        if (stack.isEmpty()) return;
        
        long popped = stack.pop();
        // If the popped value is less than the current min, it is an encoded value
        if (popped < min) {
            // Decode the previous minimum
            min = 2 * min - popped;
        }
    }
    
    public int top() {
        long topVal = stack.peek();
        // If top value is less than min, the actual top value is the min itself
        if (topVal < min) {
            return (int) min;
        }
        return (int) topVal;
    }
    
    public int getMin() {
        return (int) min;
    }
}
