/*
 * MIT License
 *
 * Copyright (c) 2016 Bryan Terce
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.btk5h.reqn.skript;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import java.util.Arrays;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprJSONEncode extends SimpleExpression<String> {

  static {
    Skript.registerExpression(ExprJSONEncode.class, String.class, ExpressionType.SIMPLE,
        "json (safe|encoded|escaped) %strings%");
  }

  private Expression<String> str;

  private static String encode(String s) {
    StringBuilder sb = new StringBuilder(s.length());
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      switch (c) {
        case '\\':
        case '"':
          sb.append('\\');
          sb.append(c);
          break;
        case '\b':
          sb.append("\\b");
          break;
        case '\t':
          sb.append("\\t");
          break;
        case '\n':
          sb.append("\\n");
          break;
        case '\f':
          sb.append("\\f");
          break;
        case '\r':
          sb.append("\\r");
          break;
        default:
          if (c < ' ') {
            String t = "000" + Integer.toHexString(c);
            sb.append("\\u");
            sb.append(t.substring(t.length() - 4));
          } else {
            sb.append(c);
          }
          break;
      }
    }
    return sb.toString();
  }

  @Override
  protected String[] get(Event e) {
    return Arrays.stream(str.getAll(e))
        .map(ExprJSONEncode::encode)
        .toArray(String[]::new);
  }

  @Override
  public boolean isSingle() {
    return str.isSingle();
  }

  @Override
  public Class<? extends String> getReturnType() {
    return String.class;
  }

  @Override
  public String toString(@Nullable Event e, boolean debug) {
    return "url encoded text";
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed,
                      SkriptParser.ParseResult parseResult) {
    str = (Expression<String>) exprs[0];
    return true;
  }


}
