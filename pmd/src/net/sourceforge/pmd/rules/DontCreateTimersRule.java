package net.sourceforge.pmd.rules;

import net.sourceforge.pmd.ast.JavaParserVisitorAdapter;
import net.sourceforge.pmd.ast.ASTAllocationExpression;
import net.sourceforge.pmd.ast.ASTName;
import net.sourceforge.pmd.*;

import java.util.*;

public class DontCreateTimersRule extends AbstractRule implements Rule {

    public String getDescription() {return "Don't create java.util.Timers, use the Cougaar alarm service instead";}

    public Object visit(ASTAllocationExpression node, Object data){
        if ((node.jjtGetChild(0) instanceof ASTName)  // this avoids "new <primitive-type>", i.e., "new int[]"
                && ((ASTName)node.jjtGetChild(0)).getImage().equals("Timer")) {
            (((RuleContext)data).getReport()).addRuleViolation(new RuleViolation(this, node.getBeginLine()));
        }
        return super.visit(node, data);
    }
}
