//208254912
/**
 * @author Shir Hanono
 * @version 1.0 11/05/2021
 */

/**
 * This class generates a Xnor object with two expressions, and Xnor operator.
 * <p>
 */
public class Xnor extends BinaryExpression implements Expression {

    /**
     * The Xnor constructor.
     * <p>
     * This method adds all the given values, and Xnor operator, to the Xnor parent classes.
     * <p>
     *
     * @param ex1 the first expression
     * @param ex2 the second expression
     */
    public Xnor(Expression ex1, Expression ex2) {
        super(ex1, ex2);
        this.setOperator(" # ");
    }

    @Override
    /**
     * This method evaluates it's expressions using the adaptive operator.
     */
    public Boolean evaluate() throws Exception {
        Boolean and;
        try {
            and = this.getE1().evaluate() == this.getE2().evaluate();
        } catch (Exception e) {
            System.out.println("evaluate failed in Xnor" + e);
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
        return new Xnor(this.getE1().assign(var, expression), this.getE2().assign(var, expression));
    }

    @Override
    /**
     *this method changes the expression to remain in the same logic,
     * but only with nand operator.
     */
    public Expression nandify() {
        //Q = A XNOR B = [ ( A NAND A ) NAND ( B NAND B ) ] NAND
        //( A NAND B )
        Expression nand = new Nand(this.getE1(), this.getE2());
        Expression nandA = new Nand(this.getE1(), this.getE1());
        Expression nandB = new Nand(this.getE2(), this.getE2());
        Expression nandAB = new Nand(nandA, nandB);
        return new Nand(nandAB.nandify(), nand.nandify());
    }

    @Override
    /**
     *this method changes the expression to remain in the same logic,
     * but only with nor operator.
     */
    public Expression norify() {
        //Q = A XNOR B = [ A NOR ( A NOR B ) ] NOR
        //[ B NOR ( A NOR B ) ]
        Expression nor = new Nor(this.getE1(), this.getE2());
        Expression norA = new Nor(this.getE1(), nor);
        Expression norB = new Nor(this.getE2(), nor);
        return new Nor(norA.norify(), norB.norify());
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

        // x # x = 1
        if (expression2.toString().equals(expression1.toString())) {
            return new Val(true);
        }
        return new Xnor(expression1, expression2);
    }
}
