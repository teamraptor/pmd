package net.sourceforge.pmd.lang.swift.rule.bestpractices;

import java.util.List;

import net.sourceforge.pmd.lang.swift.AbstractSwiftRule;
import net.sourceforge.pmd.lang.swift.antlr4.SwiftParser;

public class TomiRule extends AbstractSwiftRule {

    @Override
    public Object visitFunctionSignature(final SwiftParser.FunctionSignatureContext ctx) {
        final SwiftParser.ParameterClauseContext parameterClauseContext =  ctx.parameterClause();
        final List<SwiftParser.ParameterContext> params = parameterClauseContext.parameterList().parameter();
        params.stream()
                .filter(param -> param.externalParameterName() != null
                    && "_".equals(param.externalParameterName().getText()))
                .forEach(param -> addViolation(data, ctx));
        return null;
    }
}
