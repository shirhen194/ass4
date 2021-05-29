import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//208254912
/**
 * @author Shir Hanono
 * @version 1.0 11/05/2021
 */

/**
 * This class generates a Var object with one String value.
 * <p>
 */
public class Var implements Expression {
    private String variable;

    /**
     * The Var constructor.
     * <p>
     * This method adds the given String value, as var.
     * <p>
     *
     * @param value the String value
     */
    public Var(String value) {
        this.variable = value;
    }

    @Override
    /**
     * This method evaluates it's expression using the assignment map.
     * @param assignment the assignment map
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        //iterate on assignment and try to find the var
        for (Map.Entry<String, Boolean> entry : assignment.entrySet()) {
            if (entry.getKey() == this.variable) {
                return entry.getValue();
            }
        }
        throw new Exception("evaluate failed in Var");
    }

    @Override
    /**
     * This method should evaluates it's expression,
     * but since there's no assignment map argument to evaluate by, it returns exception.
     * @param assignment the assignment map
     */
    public Boolean evaluate() throws Exception {
        throw new Exception("evaluate failed in Var");
    }

    @Override
    /**
     * This method returns the variable in a lists.
     * @return the variables lists
     */
    public List<String> getVariables() {
        List<String> var = new ArrayList<>();
        var.add(this.variable);
        return var;
    }

    @Override
    /**
     * This method adapts the toString method of var,
     * to print the string value of var.
     */
    public String toString() {
        return this.variable;
    }

    @Override
    /**
     *this method assigns value to the var that create the expression.
     * @param var the string that represent the variable to give a value to
     * @param expression the string that represent the value to give the variable
     * @return the assigned expression
     * */
    public Expression assign(String var, Expression expression) {

        /**
         * if it's the same as var, return the new expression,
         * otherwise, return this because it's not the right string to assign.
         */
        if (var.equals(this.variable)) {
            return expression;
        }
        return this;
    }

    @Override
    /**
     * this method changes the expression to remain in the same logic,
     * but only with nor operator. Which means to just return the var.
     * Since it has no operator to work on.
     */
    public Expression nandify() {
        return this;
    }

    @Override
    /**
     * this method changes the expression to remain in the same logic,
     * but only with nor operator. Which means to just return the var.
     * Since it has no operator to work on.
     */
    public Expression norify() {
        return this;
    }

    @Override
    /**
     * this method changes the expression to remain in the same logic,
     * but make it shorter. Which means to just return the var.
     * Since it has no operator to work on.
     */
    public Expression simplify() {
        return this;
    }
}
