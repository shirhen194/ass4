import java.util.ArrayList;
import java.util.List;
//208254912
/**
 * @author Shir Hanono
 * @version 1.0 11/05/2021
 */

/**
 * This class generates a Not object with one expression, and Not operator.
 * <p>
 */
public class Not extends UnaryExpression implements Expression {

    /**
     * The Not constructor
     * <p>
     * This method adds all the given values, and Not operator, to the Not parant classes.
     * <p>
     *
     * @param ex1 the exepression
     */
    public Not(Expression ex1) {
        super(ex1);
        this.setOperator("~");
    }


    @Override
    /**
     * This method evaluates it's expression using the adaptive operator.
     */
    public Boolean evaluate() throws Exception {
        Boolean not;
        try {
            not = !this.getEx().evaluate();
        } catch (Exception e) {
            System.out.println("evaluate failed in Not" + e);
            throw e;
        }
        return not;
    }

    /**
     * This method checks which variables are in the expressions.
     * Lists and add them to a joint list, and returns the variables lists.
     *
     * @return the variables lists
     */
    public List<String> getVariables() {
        List<String> vars = new ArrayList<String>();
        List<String> e1Vars = this.getEx().getVariables();

        if (e1Vars != null) {
            vars.addAll(e1Vars);
        }
        return vars;
    }

    @Override
    /**
     * this method assigns recursively values to the vars that create the expression.
     * @param var the string that represent the variable to give a value to
     * @param expression the string that represent the value to give the variable
     * @return the assigned expression
     */
    public Expression assign(String var, Expression expression) {
        return new Not(this.getEx().assign(var, expression));
    }

    @Override
    /**
     *this method changes the expression to remain in the same logic,
     * but only with nand operator.
     */
    public Expression nandify() {
        // Q = NOT( A ) = A NAND A
        return new Nand(this.getEx().nandify(), this.getEx().nandify());
    }

    @Override
    /**
     *this method changes the expression to remain in the same logic,
     * but only with nor operator.
     */
    public Expression norify() {
        //Q = NOT( A ) = A NOR A
        return new Nor(this.getEx().norify(), this.getEx().norify());
    }

    @Override
    /**
     *this method changes the expression to remain in the same logic,
     * but make it shorter.
     */
    public Expression simplify() {
        Expression simEx = this.getEx().simplify();
        if (simEx.getVariables().isEmpty()) {
            try {
                Boolean evEx = !simEx.evaluate();
                Val simpVal = new Val(evEx);
                return simpVal;
            } catch (Exception e) {
                System.out.println("failed simplify");
                return this;
            }
        } else {
            return new Not(simEx);
        }
    }
}
