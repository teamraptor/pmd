/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.rule;

import java.util.List;

import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.AbstractAntlrVisitor;
import net.sourceforge.pmd.lang.ast.AntlrBaseNode;
import net.sourceforge.pmd.lang.ast.Node;

public class AntlrRuleChainVisitor extends AbstractRuleChainVisitor {

    @Override
    protected void visit(final Rule rule, final Node node, final RuleContext ctx) {
        if (rule instanceof AbstractAntlrVisitor) {
            ((AntlrBaseNode) node).accept((AbstractAntlrVisitor) rule);
        } else {
            ((XPathRule) rule).evaluate(node, ctx);
        }
    }

    @Override
    protected void indexNodes(final List<Node> nodes, final RuleContext ctx) {
        final AbstractAntlrVisitor antlrVisitor = new AbstractAntlrVisitor<Object>() {
            // Perform a visitation of the AST to index nodes which need
            // visiting by type
            @Override
            public Object visit(final AntlrBaseNode node) {
                indexNode(node);
                return super.visit(node);
            }
        };

        for (final Node node : nodes) {
            antlrVisitor.visit((AntlrBaseNode) node);
        }
    }
}
