//208254912
/**
 * @author Shir Hanono
 * @version 1.0 11/05/2021
 */

/**
 * This class generates an And object with two expressions, and And operator.
 * <p>
 */
public class And extends BinaryExpression implements Expression {

    /**
     * The And constructor
     * <p>
     * This method adds all the given values, and And operator, to the And parent classes.
     * <p>
     *
     * @param ex1 the first expression
     * @param ex2 the second expression
     */
    public And(Expression ex1, Expression ex2) {
        super(ex1, ex2);
        this.setOperator(" & ");
    }

    @Override
    /**
     * This method evaluates it's expressions using the adaptive operator.
     */
    public Boolean evaluate() throws Exception {
        Boolean and;
        try {
            and = this.getE1().evaluate() && this.getE2().evaluate();
        } catch (Exception e) {
            System.out.println("evaluate failed in And" + e);
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
        return new And(this.getE1().assign(var, expression), this.getE2().assign(var, expression));
    }

    @Override
    /**
     *this method changes the expression to remain in the same logic,
     * but only with nand operator.
     */
    public Expression nandify() {
        // Q = A AND B = ( A NAND B ) NAND ( A NAND B )
        Expression nand = new Nand(this.getE1(), this.getE2());
        return new Nand(nand.nandify(), nand.nandify());
    }

    @Override
    /**
     *this method changes the expression to remain in the same logic,
     * but only with nor operator.
     */
    public Expression norify() {
        //Q = A AND B = ( A NOR A ) NOR ( B NOR B )
        Expression nor1 = new Nor(this.getE1(), this.getE1());
        Expression nor2 = new Nor(this.getE2(), this.getE2());
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

        if (expression1.getVariables().isEmpty()) {
            // x & 1 = x
            if (((Val) expression1).getVal()) {
                return expression2;
            }
            // x & 0 = 0
            if (!((Val) expression1).getVal()) {
                return new Val(false);
            }
        }

        if (expression2.getVariables().isEmpty()) {
            // x & 1 = x
            if (((Val) expression2).getVal()) {
                return expression1;
            }
            // x & 0 = 0
            if (!((Val) expression2).getVal()) {
                return new Val(false);
            }
        }
        // x & x = x
        if (expression2.toString().equals(expression1.toString())) {
            return expression2;
        }
        return new And(expression1, expression2);
    }
}
