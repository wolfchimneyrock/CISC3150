
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
    default public boolean isUnary()           { return false; }

    public static Token fromString(String s, boolean expectNum) {
        // we need expectNum since a number might be negative and start with '-'
        // also a bracket can happen at any time, whether we expect a number or operator
        Token result = null;
        switch(s) {
            case "{":   result = new LeftBracket();
                        break;
            case "}":   result = new RightBracket();
                        break;
            case "sin": result = new SineOperator();
                        break;
            case "cos": result = new CosineOperator();
                        break;
            case "tan": result = new TangentOperator();
                        break;
            case "pi":  result = new Operand(Math.PI);
                        break;
            case "e":   result = new Operand(Math.E);
                        break;
            case "log": result = new LogOperator();
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
                        case '!': result = new FactorialOperator();
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
    public int precedence() { return 7; }
    @Override
    public boolean isLeftAssociative() { return false; }
    @Override
    public String toString() { return "^"; }
}

class FactorialOperator implements Token {
    @Override
    public double evaluate(double a, double b) { 
        long i = (long) Math.round(a);
        long result = 1;
        for (long j = 1; j <= i; j++)
            result *= j;
        return (double)result;
    }
    @Override
    public boolean isUnary() { return true; }
    @Override
    public int precedence() { return 4; }
    @Override
    public String toString() { return "!"; }
} 

class SineOperator implements Token {
    @Override
    public double evaluate(double a, double b) { return Math.sin(a); }
    @Override
    public boolean isUnary() { return true; }
    @Override
    public int precedence() { return 6; }
    @Override
    public String toString() { return "sin"; }
}

class CosineOperator implements Token {
    @Override
    public double evaluate(double a, double b) { return Math.cos(a); }
    @Override
    public boolean isUnary() { return true; }
    @Override
    public int precedence() { return 6; }
    @Override
    public String toString() { return "cos"; }
}

class TangentOperator implements Token {
    @Override
    public double evaluate(double a, double b) { return Math.tan(a); }
    @Override
    public boolean isUnary() { return true; }
    @Override
    public int precedence() { return 6; }
    @Override
    public String toString() { return "tan"; }
}

class LogOperator implements Token {
    @Override
    public double evaluate(double a, double b) { return Math.log(a); }
    @Override
    public boolean isUnary() { return true; }
    @Override
    public int precedence() { return 6; }
    @Override
    public String toString() { return "log"; }
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
