/********************************************************
 * Robert Wagner
 * CISC 3150 HW #7
 * 2017-10-17
 *
 * Calculator.java:
 *   In which infix operations are evaluated
 *   by first shunting to postfix with a stack
 *   and then evaluating, again with a stack
 *
 ********************************************************/

import java.util.*;
public class Calculator {
    protected List<Token> output;
    protected Stack<Token> opStack;

    public Calculator() {
        output  = new ArrayList<Token>();
        opStack = new Stack<Token>();
    }

    public void add(Token t) {
        // This is the shunting yard algorithm
        // https://en.wikipedia.org/wiki/Shunting-yard_algorithm

        if (t.isOperand()) {
            output.add(t);
            return;
        }
        if (t.isOperator()) {
            while (!opStack.empty() && 
                   (opStack.peek()).precedence() >= t.precedence() &&
                   t.isLeftAssociative() 
                   ) {
                output.add(opStack.pop());
            }
            opStack.push(t);
        }   
        if (t.isLeftBracket()) {
            opStack.push(t);
        }
        if (t.isRightBracket()) {
            while (!opStack.empty() &&
                   !(opStack.peek()).isLeftBracket()
                  ) {
                output.add(opStack.pop());
            }
            if (opStack.empty()) {
                throw new UserIsADumbassException();
            }
            opStack.pop();
                  
        }
    }

    public void finalize() {
        while (!opStack.empty())
            output.add(opStack.pop());
    }

    public double evaluate() {
        if (!opStack.empty()) 
            this.finalize();
        Stack<Double> s = new Stack<Double>();
        for (Token t : this.output) {
            if (t.isOperand())
                s.push(t.value());
            else {
                try {
                    double b;
                    if (t.isUnary())
                       b = 0;
                    else
                       b = s.pop();
                    double a = s.pop();
                    s.push(t.evaluate(a, b));
                }
                catch (Exception e) {
                    throw new UserIsADumbassException();
                }
            }
        }
        // we should be left with a single value on the stack
        return s.pop();
    }

    public String toString() {
        return output.toString();      
    }

    public static void main(String[] args) {
        boolean expectNumber = true;
        Calculator p = new Calculator();
        for (int i = 0; i < args.length; i++) {
            try {
                Token t = Token.fromString(args[i], expectNumber);
                
                // brackets don't trigger a change in expectation
                // neither do unary operators
                if ((t.isOperand() || t.isOperator()) && !t.isUnary())
                   expectNumber = t.isOperator(); 

                p.add(t);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        p.finalize();
        System.out.println(p);
        try {
            System.out.println("Result: " + p.evaluate());
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }
}




    
