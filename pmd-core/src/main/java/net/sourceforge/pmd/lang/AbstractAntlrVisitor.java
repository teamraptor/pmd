/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang;

import java.util.List;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.ast.AntlrBaseNode;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.rule.AbstractRule;

public abstract class AbstractAntlrVisitor<T> extends AbstractRule implements ParseTreeVisitor<T> {

    protected RuleContext data;

    @Override
    public void start(final RuleContext ctx) {
        data = ctx;
    }

    @Override
    public T visit(final ParseTree tree) {
        return tree.accept(this);
    }

    @Override
    public T visitTerminal(final TerminalNode node) {
        return defaultResult();
    }

    @Override
    public T visitErrorNode(final ErrorNode node) {
        return defaultResult();
    }

    @Override
    public void apply(final List<? extends Node> nodes, final RuleContext ctx) {
        visitAll(nodes);
    }

    /**
     * Begin {@link org.antlr.v4.runtime.tree.AbstractParseTreeVisitor} section
     */

    @Override
    public T visitChildren(final RuleNode node) {
        T result = defaultResult();
        final int n = node.getChildCount();

        for (int i = 0; i < n && shouldVisitNextChild(node, result); ++i) {
            final ParseTree c = node.getChild(i);
            final T childResult = c.accept(this);
            result = aggregateResult(result, childResult);
        }

        return result;
    }

    protected T defaultResult() {
        return null;
    }

    protected T aggregateResult(final T aggregate, final T nextResult) {
        return nextResult;
    }

    protected boolean shouldVisitNextChild(final RuleNode node, final T currentResult) {
        return true;
    }

    /**
     * End {@link org.antlr.v4.runtime.tree.AbstractParseTreeVisitor} section
     */

    protected void visitAll(final List<? extends Node> nodes) {
        for (final Node n : nodes) {
            final AntlrBaseNode node = (AntlrBaseNode) n;
            visit(node);
        }
    }

    public Object visit(final AntlrBaseNode node) {
        return node.accept(this);
    }
}
