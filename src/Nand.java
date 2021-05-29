//208254912
/**
 * @author Shir Hanono
 * @version 1.0 11/05/2021
 */

/**
 * This class generates a Nand object with two expressions, and Nand operator.
 * <p>
 */
public class Nand extends BinaryExpression implements Expression {

    /**
     * The Nand constructor.
     * <p>
     * This method adds all the given values, and Nand operator, to the Nand parent classes.
     * <p>
     *
     * @param ex1 the first expression
     * @param ex2 the second expression
     */
    public Nand(Expression ex1, Expression ex2) {
        super(ex1, ex2);
        this.setOperator(" A ");
    }

    @Override
    /**
     * This method evaluates it's expressions using the adaptive operator.
     */
    public Boolean evaluate() throws Exception {
        Boolean and;
        try {
            and = !(this.getE1().evaluate() && this.getE2().evaluate());
        } catch (Exception e) {
            System.out.println("evaluate failed in Nand" + e);
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
        return new Nand(this.getE1().assign(var, expression), this.getE2().assign(var, expression));
    }

    @Override
    /**
     *this method changes the expression to remain in the same logic,
     * but only with nand operator.
     */
    public Expression nandify() {
        return new Nand(this.getE1().nandify(), this.getE2().nandify());
    }

    @Override
    /**
     *this method changes the expression to remain in the same logic,
     * but only with nor operator.
     */
    public Expression norify() {
        //Q = A NAND B = [ ( A NOR A ) NOR ( B NOR B ) ] NOR
        //[ ( A NOR A ) NOR ( B NOR B ) ]
        Expression and = new And(this.getE1(), this.getE2());
        Expression norAnd = and.norify();
        return new Nor(norAnd.norify(), norAnd.norify());
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
            // x ↑ 1 = ~(x)
            if (((Val) expression1).getVal()) {
                Expression newEx = new Not(expression2);
                return newEx.simplify();
            }
            // x ↑ 0 = 1
            if (!((Val) expression1).getVal()) {
                return new Val(true);
            }
        }

        if (expression2.getVariables().isEmpty()) {
            // x ↑ 1 = ~(x)
            if (((Val) expression2).getVal()) {
                Expression newEx = new Not(expression1);
                return newEx.simplify();
            }
            // x ↑ 0 = 1
            if (!((Val) expression2).getVal()) {
                return new Val(true);
            }
        }
        // x ↑ x = ~(x)
        if (expression2.toString().equals(expression1.toString())) {
            Expression newEx = new Not(expression2);
            return newEx.simplify();
        }
        return new Nand(expression1, expression2);
    }
}
