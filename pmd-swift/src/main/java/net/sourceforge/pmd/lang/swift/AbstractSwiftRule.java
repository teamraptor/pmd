package net.sourceforge.pmd.lang.swift;

import net.sourceforge.pmd.lang.LanguageRegistry;
import net.sourceforge.pmd.lang.swift.antlr4.SwiftBaseVisitor;

public abstract class AbstractSwiftRule extends SwiftBaseVisitor {
    public AbstractSwiftRule() {
        super.setLanguage(LanguageRegistry.getLanguage(SwiftLanguageModule.NAME));
    }
}
