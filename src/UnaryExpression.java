//208254912
/**
 * @author Shir Hanono
 * @version 1.0 11/05/2021
 */

/**
 * This class generates a UnaryExpression object with one expression.
 * <p>
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression ex;

    /**
     * The BinaryExpression constructor.
     * <p>
     * This method adds all the given expressions to the BinaryExpression properties.
     * <p>
     *
     * @param e the expression
     */
    public UnaryExpression(Expression e) {
        this.ex = e;
    }

    /**
     * this method returns the UnaryExpression expression.
     *
     * @return the UnaryExpression expression.
     */
    public Expression getEx() {
        return this.ex;
    }

    /**
     * This method adapts the toString method of the UnaryExpression:
     * operator(ex).
     *
     * @return the String adaptation
     */
    public String toString() {
        return super.getOperator() + "(" + this.ex.toString() + ")";
    }
}
