import java.util.ArrayList;
import java.util.List;
//208254912
/**
 * @author Shir Hanono
 * @version 1.0 11/05/2021
 */

/**
 * This class generates a BinaryExpression object with two expressions.
 * <p>
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression ex1;
    private Expression ex2;

    /**
     * The BinaryExpression constructor.
     * <p>
     * This method adds all the given expressions to the BinaryExpression properties.
     * <p>
     *
     * @param e1 the first expression
     * @param e2 the second expression
     */
    public BinaryExpression(Expression e1, Expression e2) {
        this.ex1 = e1;
        this.ex2 = e2;
    }

    /**
     * this method returns the BinaryExpression first expression.
     *
     * @return the BinaryExpression first expression.
     */
    public Expression getE1() {
        return this.ex1;
    }

    /**
     * this method returns the BinaryExpression second expression.
     *
     * @return the BinaryExpression second expression.
     */
    public Expression getE2() {
        return this.ex2;
    }

    @Override
    /**
     * This method adapts the toString method of the BinaryExpression:
     * (ex1 operator ex2).
     * @return the String adaptation
     */
    public String toString() {
        return "(" + this.ex1.toString() + super.getOperator() + this.ex2.toString() + ")";
    }

    /**
     * This method evaluates the Expression if there are no vars in it.
     *
     * @return the evaluated Val
     */
    public Val evaluateIfNoVars() {
        /**
         * check if there are no variables left,
         * if so, return evaluation
         */
        if (this.getE1().getVariables().isEmpty() && this.getE2().getVariables().isEmpty()) {
            try {
                return new Val(this.evaluate());
            } catch (Exception e) {
                System.out.println("failed simplify");
            }
        }
        return null;
    }

    /**
     * This method checks wich varaiables are in the expressions.
     * Lists and add them to a joint list, and returns the variables lists.
     *
     * @return variables list
     */
    public List<String> getVariables() {
        //create a list to add the variables to
        List<String> vars = new ArrayList<>();
        //get the variables from the expressions
        List<String> e1Vars = this.getE1().getVariables();
        List<String> e2Vars = this.getE2().getVariables();

        /*
        check if there are vars in ex1, if there are add them to the total vars.
         */
        if (e1Vars != null) {
            vars.addAll(e1Vars);
        }
        /*
        check if there are vars in ex2, if there are add them to the total vars.
         */
        if (e2Vars != null) {
            //if some of the vars in e2  are already there
            vars.removeAll(e2Vars);
            vars.addAll(e2Vars);
        }
        return vars;
    }
}
