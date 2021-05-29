import java.util.Map;
//208254912
/**
 * @author Shir Hanono
 * @version 1.0 11/05/2021
 */

/**
 * This class generates a BaseExpression object with an operator.
 * <p>
 */
public abstract class BaseExpression {
    private String operator;

    /**
     * this method sets the BaseExpression operator by given value.
     *
     * @param operatorExpression the BaseExpression operator
     */
    protected void setOperator(String operatorExpression) {
        this.operator = operatorExpression;
    }

    /**
     * this method gets and returns the BaseExpression operator.
     *
     * @return this operator
     */
    String getOperator() {
        return this.operator;
    }

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment the assignment map
     * @return the Boolean evaluate of the expression
     * @throws Exception "evaluate failed in BaseExpression"
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Boolean evaluation = true;
        try {
            Expression baseEx = (Expression) this;

            /**
             * create a set out of assignment and loop over it,
             * to create a new expression based on it
             */
            for (Map.Entry<String, Boolean> entry : assignment.entrySet()) {
                baseEx = baseEx.assign(entry.getKey(), new Val(entry.getValue()));
            }
            evaluation = baseEx.evaluate();
        } catch (Exception e) {
            System.out.println("evaluate failed in BaseExpression");
        }
        return evaluation;
    }

    /**
     * this method assigns recursively values to the vars that create the expression.
     *
     * @param var        the string that represent the variable to give a value to
     * @param expression the string that represent the value to give the variable
     * @return the assigned expression
     */
    public abstract Expression assign(String var, Expression expression);

    /**
     * this method evaluate recursively values to the vars that create the expression.
     *
     * @return the evaluate expression
     * @throws Exception if there is problem with evaluate
     */
    public abstract Boolean evaluate() throws Exception;
}
