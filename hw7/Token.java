
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
    public double evaluate(double a, double b) { return a + b; }
    public int    precedence()                 { return 2;     }
    public String toString()                   { return "+";   }
}

class SubtractOperator implements Token {
    public double evaluate(double a, double b) { return a - b; }
    public int    precedence()                 { return 2;     }
    public String toString()                   { return "-";   }
}

class MultiplyOperator implements Token {
    public double evaluate(double a, double b) { return a * b; }
    public int    precedence()                 { return 3;     }
    public String toString()                   { return "*";   }
}

class DivideOperator implements Token {
    public double evaluate(double a, double b) { return a / b; }
    public int    precedence()                 { return 3;     }
    public String toString()                   { return "/";   }
}

class ModuloOperator implements Token {
    public double evaluate(double a, double b) { return a % b; }
    public int    precedence()                 { return 3;     }
    public String toString()                   { return "%";   }
}

class ExponentOperator implements Token {
    public double  evaluate(double a, double b) { return Math.pow(a, b); }
    public int     precedence()                 { return 7;              }
    public boolean isLeftAssociative()          { return false;          }
    public String  toString()                   { return "^";            }
}

class FactorialOperator implements Token {
    public double evaluate(double a, double b) { 
        long i = (long) Math.round(a);
        long result = 1;
        for (long j = 1; j <= i; j++)
            result *= j;
        return (double)result;
    }
    public boolean isUnary()    { return true; }
    public int     precedence() { return 4;    }
    public String  toString()   { return "!";  }
} 

class SineOperator implements Token {
    public double  evaluate(double a, double b) { return Math.sin(a); }
    public boolean isUnary()                    { return true;        }
    public int     precedence()                 { return 6;           }
    public String  toString()                   { return "sin";       }
}

class CosineOperator implements Token {
    public double  evaluate(double a, double b) { return Math.cos(a); }
    public boolean isUnary()                    { return true;        }
    public int     precedence()                 { return 6;           }
    public String  toString()                   { return "cos";       }
}

class TangentOperator implements Token {
    public double  evaluate(double a, double b) { return Math.tan(a); }
    public boolean isUnary()                    { return true;        }
    public int     precedence()                 { return 6;           }
    public String  toString()                   { return "tan";       }
}

class LogOperator implements Token {
    public double  evaluate(double a, double b) { return Math.log(a); }
    public boolean isUnary()                    { return true;        }
    public int     precedence()                 { return 6;           }
    public String  toString()                   { return "log";       }
}

class LeftBracket implements Token {
    public boolean isLeftBracket() { return true;  }
    public boolean isOperator()    { return false; }
    public int     precedence()    { return 1;     }
    public String  toString()      { return "(";   }
}

class RightBracket implements Token {
    public boolean isRightBracket() { return true;  }
    public boolean isOperator()     { return false; }
    public String  toString()       { return ")";   }
}

class Operand implements Token {
    private double val;
    public Operand(double val)  { this.val = val;                   }
    public boolean isOperator() { return false;                     }
    public boolean isOperand()  { return true;                      }
    public double  value()      { return this.val;                  }
    public String  toString()   { return Double.toString(this.val); }
}
