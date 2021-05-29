import java.util.Map;
import java.util.TreeMap;
//208254912
/**
 * @author Shir Hanono
 * @version 1.0 11/05/2021
 */

/**
 * This class generates an ExpressionsTest object to test the work of the expression classes.
 * <p>
 */
public class ExpressionsTest {
    /**
     * this main method creates an expression and does the following actions:
     * Create an expression with at least three variables.
     * Print the expression.
     * Print the value of the expression with an assignment to every variable.
     * Print the Nandified version of the expression.
     * Print the Norified version of the expression.
     * Print the simplified version of the expression.
     *
     * @param args holding command line args
     */
    public static void main(String[] args) {
        Var s = new Var("S");
        Var h = new Var("H");
        Var i = new Var("I");
        Var r = new Var("R");
        Map<String, Boolean> assignmentMap = new TreeMap<>();
        //Create an expression with at least three variables.
        Expression ex = new And(new Not(new And(s, h)), new Nand(new Xor(i, r), new Nor(r, r)));
        //add expression to assignment map
        assignmentMap.put("S", true);
        assignmentMap.put("H", false);
        assignmentMap.put("I", true);
        assignmentMap.put("R", true);
        //Print the expression.
        System.out.println(ex);
        Expression exAssigned = ex;

        /**
         * create a set out of assignment and loop over it,
         * to create a new expression based on it
         */
        for (Map.Entry<String, Boolean> entry : assignmentMap.entrySet()) {
            //Print the value of the expression with an assignment to every variable.
            exAssigned = exAssigned.assign(entry.getKey(), new Val(entry.getValue()));
        }

        System.out.println(exAssigned);
        //Print the Nandified version of the expression.
        Expression nandified = ex.nandify();
        System.out.println(nandified);
        //Print the Norified version of the expression.
        Expression norfied = ex.norify();
        System.out.println(norfied);
        //Print the simplified version of the expression.
        System.out.println(ex.simplify());
    }
}
