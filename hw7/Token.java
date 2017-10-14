
public interface Token {
    default public double  evaluate(double a, double b) {
        throw new UnsupportedOperationException();
    }

    default public double  value() {
        throw new UnsupportedOperationException();
    }

    default public int     precedence() {
        throw new UnsupportedOperationException();
    }

    default public boolean isOperand()         { return false; }
    default public boolean isOperator()        { return true;  }
    default public boolean isLeftAssociative() { return true;  }
    default public boolean isLeftBracket()     { return false; }
    default public boolean isRightBracket()    { return false; }

    public static Token fromString(String s, boolean expectNum) {
        // we need expectNum since a number might be negative and start with '-'
        // also a bracket can happen at any time, whether we expect a number or operator
        Token result = null;
        switch(s.charAt(0)) {
            case '{': result =  new LeftBracket();
                      break;
            case '}': result =  new RightBracket();
                      break;
            default: 

                if (expectNum) {
                    try {
                        double num = Double.parseDouble(s);
                        result = new Operand(num);
                    } 
                    catch (NumberFormatException e)  {
                        throw new LookAtMrAlgebraOverHereException();
                    }
                }
                else {
                    switch(s.charAt(0)) {
                        case '+': result = new AddOperator();
                                  break;
                        case '-': result = new SubtractOperator();
                                  break;
                        case 'x': result = new MultiplyOperator();
                                  break;
                        case '/': result = new DivideOperator();
                                  break;
                        case '%': result = new ModuloOperator();
                                  break;
                        case '^': result = new ExponentOperator();
                                  break;
                        case '(': result = new LeftBracket();
                                  break;
                        case ')': result = new RightBracket();
                                  break;
                        default:  
                                  throw new IllegalOperationException();
                    }
                }
        }       
        return result;
    }

}

class AddOperator implements Token {
    @Override
    public double evaluate(double a, double b) { return a + b; }
    @Override
    public int precedence() { return 2; }
    @Override
    public String toString() { return "+"; }
}

class SubtractOperator implements Token {
    @Override
    public double evaluate(double a, double b) { return a - b; }
    @Override
    public int precedence() { return 2; }
    @Override
    public String toString() { return "-"; }
}

class MultiplyOperator implements Token {
    @Override
    public double evaluate(double a, double b) { return a * b; }
    @Override
    public int precedence() { return 3; }
    @Override
    public String toString() { return "*"; }
}

class DivideOperator implements Token {
    @Override
    public double evaluate(double a, double b) { return a / b; }
    @Override
    public int precedence() { return 3; }
    @Override
    public String toString() { return "/"; }
}

class ModuloOperator implements Token {
    @Override
    public double evaluate(double a, double b) { return a % b; }
    @Override
    public int precedence() { return 3; }
    @Override
    public String toString() { return "%"; }
}

class ExponentOperator implements Token {
    @Override
    public double evaluate(double a, double b) { return Math.pow(a, b); }
    @Override
    public int precedence() { return 4; }
    @Override
    public boolean isLeftAssociative() { return false; }
    @Override
    public String toString() { return "^"; }
}

class LeftBracket implements Token {
    @Override
    public boolean isLeftBracket() { return true; }
    @Override
    public boolean isOperator()     { return false; }
    @Override
    public int precedence()     { return 1; }
    @Override
    public String toString() { return "("; }
}

class RightBracket implements Token {
    @Override
    public boolean isRightBracket() { return true; }
    @Override
    public boolean isOperator()     { return false; }
    @Override
    public String toString() { return ")"; }
}

class Operand implements Token {
    private double val;
    public Operand(double val) { this.val = val; }
    @Override
    public boolean isOperator() { return false; }
    @Override
    public boolean isOperand()  { return true;  }
    @Override
    public double value() { return this.val; }
    @Override
    public String toString() { return Double.toString(this.val); }
}
