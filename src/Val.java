import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//208254912
/**
 * @author Shir Hanono
 * @version 1.0 11/05/2021
 */

/**
 * This class generates a Val object with Boolean one value.
 * <p>
 */
public class Val implements Expression {
    private Boolean val;

    /**
     * The Or constructor.
     * <p>
     * This method adds the given Boolean value, as val.
     * <p>
     *
     * @param value the Boolean value
     */
    public Val(Boolean value) {
        this.val = value;
    }

    @Override
    /**
     * This method returns it's value.
     * @param assignment the assignment map
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return this.val;
    }

    @Override
    /**
     * This method returns it's value.
     */
    public Boolean evaluate() throws Exception {
        return this.val;
    }

    @Override
    /**
     * since there are no variables in the Val, this method return null.
     */
    public List<String> getVariables() {
        List<String> vars = new ArrayList<String>();
        return vars;
    }

    @Override
    /**
     * This method adapts the toString method of val to print:
     * T for true, and F for false.
     */
    public String toString() {
        if (this.val) {
            return "T";
        }
        return "F";
    }

    @Override
    /**
     * since the val is already assigned, it returns this val expression.
     * @param var the string that represent the variable to give a value to
     * @param expression the string that represent the value to give the variable
     * @return this val expression
     */
    public Expression assign(String var, Expression expression) {
        return this;
    }

    /**
     * this method gets and returns the boolean value of this val expression.
     *
     * @return the boolean value of this val expression.
     */
    public Boolean getVal() {
        return this.val;
    }

    @Override
    /**
     * this method changes the expression to remain in the same logic,
     * but only with nand operator. Which means to just return the val.
     * Since it has no operator to work on.
     */
    public Expression nandify() {
        return this;
    }

    @Override
    /**
     * this method changes the expression to remain in the same logic,
     * but only with nor operator. Which means to just return the val.
     * Since it has no operator to work on.
     */
    public Expression norify() {
        return this;
    }

    @Override
    /**
     * this method changes the expression to remain in the same logic,
     * but make it shorter. Which means to just return the val.
     * Since it has no operator to work on.
     */
    public Expression simplify() {
        return this;
    }
}
