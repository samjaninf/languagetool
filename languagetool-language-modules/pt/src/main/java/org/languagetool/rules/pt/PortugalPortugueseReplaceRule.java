/* LanguageTool, a natural language style checker 
 * Copyright (C) 2005 Daniel Naber (http://www.danielnaber.de)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.rules.pt;

import org.languagetool.AnalyzedTokenReadings;
import org.languagetool.Language;
import org.languagetool.rules.AbstractSimpleReplaceRule2;
import org.languagetool.rules.Categories;
import org.languagetool.rules.Example;
import org.languagetool.rules.ITSIssueType;

import java.util.*;

/**
 * A rule that matches words which should not be used and suggests correct ones instead. 
 * Romanian implementations. Loads the list of words from
 * <code>/ro/replace.txt</code>.
 *
 * @author Tiago F. Santos (localized from romanian)
 * @since 3.6
 */
public class PortugalPortugueseReplaceRule extends AbstractSimpleReplaceRule2 {

  public static final String PORTUGAL_PORTUGUESE_SIMPLE_REPLACE_RULE = "PT_PT_SIMPLE_REPLACE";

  private static final Locale PT_LOCALE = new Locale("pt");  // locale used on case-conversion

  private final String path;

  @Override
  public List<String> getFileNames() {
    return Collections.singletonList(path);
  }

  public PortugalPortugueseReplaceRule(ResourceBundle messages, String path, Language language) {
    super(messages, language);
    this.path = Objects.requireNonNull(path);
    setCategory(Categories.STYLE.getCategory(messages));
    setLocQualityIssueType(ITSIssueType.LocaleViolation);
    addExamplePair(Example.wrong("<marker>aeromoça</marker>"),
                   Example.fixed("<marker>hospedeira de bordo</marker>"));
    this.useSubRuleSpecificIds();
  }

  @Override
  public String getId() {
    return PORTUGAL_PORTUGUESE_SIMPLE_REPLACE_RULE;
  }

  @Override
  public String getDescription() {
    return "Brasileirismo: 1. palavras confundidas com as de Portugal";
  }

  @Override
  public String getShort() {
    return "Palavra de português do Brasil";
  }

  @Override
  public String getMessage() {
    return "'$match' é uma expressão brasileira, em português de Portugal utiliza-se: $suggestions";
  }

  @Override
  public String getSuggestionsSeparator() {
    return " ou ";
  }

  @Override
  public Locale getLocale() {
    return PT_LOCALE;
  }
  
  @Override
  protected boolean isTokenException(AnalyzedTokenReadings atr) {
    // proper nouns tagged in multiwords are exceptions
    return atr.hasPosTagStartingWith("NP") || atr.isImmunized();
  }

}
