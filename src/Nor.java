//208254912
/**
 * @author Shir Hanono
 * @version 1.0 11/05/2021
 */

/**
 * This class generates a Nor object with two expressions, and Nor operator.
 * <p>
 */
public class Nor extends BinaryExpression implements Expression {

    /**
     * The Nor constructor
     * <p>
     * This method adds all the given values, and Nor operator, to the Nor parant classes.
     * <p>
     *
     * @param ex1 the first exepression
     * @param ex2 the seceond exepression
     */
    public Nor(Expression ex1, Expression ex2) {
        super(ex1, ex2);
        this.setOperator(" V ");
    }

    @Override
    /**
     * This method evaluates it's expressions using the adaptive operator.
     */
    public Boolean evaluate() throws Exception {
        Boolean and;
        try {
            and = !(this.getE1().evaluate() || this.getE2().evaluate());
        } catch (Exception e) {
            System.out.println("evaluate failed in Nor" + e);
            throw e;
        }
        return and;
    }

    @Override
    /**
     *this method assigns recursively values to the vars that create the expression.
     * @param var the string that represent the variable to give a value to
     * @param expression the string that represent the value to give the variable
     * @return the assigned expression
     */
    public Expression assign(String var, Expression expression) {
        return new Nor(this.getE1().assign(var, expression), this.getE2().assign(var, expression));
    }

    @Override
    /**
     * this method converts the nor to a nand expression
     * using the nandify logic for the or gate
     */
    public Expression nandify() {
        // Q = A NOR B = [ ( A NAND A ) NAND ( B NAND B ) ] NAND
        //[ ( A NAND A ) NAND ( B NAND B ) ]
        //initializing the or
        Expression or = new Or(this.getE1(), this.getE2());
        // or.nandify will return ( A NAND A ) NAND ( B NAND B ), so we need to nand on this
        return new Nand(or.nandify(), or.nandify());
    }

    @Override
    /**
     *this method changes the expression to remain in the same logic,
     * but only with nor operator.
     */
    public Expression norify() {
        return new Nor(this.getE1().norify(), this.getE2().norify());
    }

    @Override
    /**
     *this method changes the expression to remain in the same logic,
     * but make it shorter.
     */
    public Expression simplify() {

        /**
         * check if there are no variables left,
         * if so, return evaluation
         */
        Val simpleEvaluate = super.evaluateIfNoVars();
        if (simpleEvaluate != null) {
            return simpleEvaluate;
        }

        //recursively simplify the expressions
        Expression expression1 = this.getE1().simplify();
        Expression expression2 = this.getE2().simplify();

        if (expression1.getVariables().isEmpty()) {
            //x ↓ 1 = 0
            if (((Val) expression1).getVal()) {
                return new Val(false);
            }
            //x ↓ 0 = ~(x)
            if (!((Val) expression1).getVal()) {
                Expression newEx = new Not(expression2);
                return newEx.simplify();
            }
        }
        if (expression2.getVariables().isEmpty()) {
            //x ↓ 1 = 0
            if (((Val) expression2).getVal()) {
                return new Val(false);
            }
            //x ↓ 0 = ~(x)
            if (!((Val) expression2).getVal()) {
                Expression newEx = new Not(expression1);
                return newEx.simplify();
            }
        }
        //x ↓ x = ~(x)
        if (expression2.toString().equals(expression1.toString())) {
            Expression newEx = new Not(expression2);
            return newEx.simplify();
        }
        return new Nor(expression1, expression2);
    }
}
