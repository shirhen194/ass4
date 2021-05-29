//208254912
/**
 * @author Shir Hanono
 * @version 1.0 11/05/2021
 */

/**
 * This class generates an Or object with two expressions, and Or operator.
 * <p>
 */
public class Or extends BinaryExpression implements Expression {

    /**
     * The Or constructor.
     * <p>
     * This method adds all the given values, and Or operator, to the Or parent classes.
     * <p>
     *
     * @param ex1 the first expression
     * @param ex2 the second expression
     */
    public Or(Expression ex1, Expression ex2) {
        super(ex1, ex2);
        this.setOperator(" | ");
    }

    @Override
    /**
     * This method evaluates it's expressions using the adaptive operator.
     */
    public Boolean evaluate() throws Exception {
        Boolean or;
        try {
            or = this.getE1().evaluate() || this.getE2().evaluate();
        } catch (Exception e) {
            System.out.println("evaluate failed in or" + e);
            throw e;
        }
        return or;
    }

    @Override
    /**
     * this method assigns recursively values to the vars that create the expression.
     * @param var the string that represent the variable to give a value to
     * @param expression the string that represent the value to give the variable
     * @return the assigned expression
     */
    public Expression assign(String var, Expression expression) {
        return new Or(this.getE1().assign(var, expression), this.getE2().assign(var, expression));
    }

    @Override
    /**
     * this method converts the or to a nand expression
     */
    public Expression nandify() {
        // Q = A OR B = ( A NAND A ) NAND ( B NAND B )
        Expression nand1 = new Nand(this.getE1(), this.getE1());
        Expression nand2 = new Nand(this.getE2(), this.getE2());
        return new Nand(nand1.nandify(), nand2.nandify());
    }

    @Override
    /**
     *this method changes the expression to remain in the same logic,
     * but only with nor operator.
     */
    public Expression norify() {
        //Q = A OR B = ( A NOR B ) NOR ( A NOR B )
        Expression nor1 = new Nor(this.getE1(), this.getE2());
        Expression nor2 = new Nor(this.getE1(), this.getE2());
        return new Nor(nor1.norify(), nor2.norify());
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

        // x | 1 = 1 or x | 1 = 1
        if (expression1.toString().equals("T") || expression2.toString().equals("T")) {
            return new Val(true);
        }
        // x | 0 = x or x | 0 = x
        if (expression1.toString().equals("F")) {
            return expression2;
        }
        // x | 0 = x or x | 0 = x
        if (expression2.toString().equals("F")) {
            return expression1;
        }
        // x | x = x
        if (expression2.toString().equals(expression1.toString())) {
            return expression2;
        }

        return new Or(expression1, expression2);
    }
}
