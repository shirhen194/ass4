import java.util.List;
import java.util.Map;
//208254912
/**
 * @author Shir Hanono
 * @version 1.0 11/05/2021
 */

/**
 * This interface generates an Expression.
 * <p>
 */
public interface Expression {

    /**
     * this method evaluates the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment the assignment map
     * @return the Boolean evaluate of the expression
     * @throws Exception "evaluate failed"
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * this method is a convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return the Boolean evaluate of the expression
     * @throws Exception "evaluate failed"
     */
    Boolean evaluate() throws Exception;

    /**
     * This method checks which variables are in the expression.
     * and returns a list of the variables in the expression.
     *
     * @return a list of the variables in the expression
     */
    List<String> getVariables();

    /**
     * this method returns a nice string representation of the expression.
     *
     * @return a nice string representation of the expression.
     */
    String toString();

    /**
     * this method returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        the string that represent the variable to give a value to
     * @param expression the string that represent the value to give the variable
     * @return the assigned expression
     */
    Expression assign(String var, Expression expression);

    /**
     * this method returns the expression tree,
     * resulting from converting all the operations to the logical Nand operation.
     *
     * @return the same expression only with Nand operator
     */
    Expression nandify();

    /**
     * this method returns the expression tree,
     * resulting from converting all the operations to the logical Nor operation.
     *
     * @return the same expression only with Nor operator
     */
    Expression norify();

    /**
     * this method returns a simplified version of the current expression.
     *
     * @return simplified version of the current expression.
     */
    Expression simplify();
}