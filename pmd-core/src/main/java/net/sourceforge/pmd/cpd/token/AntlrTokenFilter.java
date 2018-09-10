/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.cpd.token;

import net.sourceforge.pmd.lang.TokenManager;

/**
 * A generic filter for Antlr-based languages allowing comments to enable/disable CPD analysis.
 */
public class AntlrTokenFilter extends AbstractTokenFilter {

    /**
     * Creates a new AbstractTokenFilter
     *
     * @param tokenManager The token manager from which to retrieve tokens to be filtered
     */
    public AntlrTokenFilter(TokenManager tokenManager) {
        super(tokenManager);
    }
}
