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

package com.w00tmast3r.reqn.skript;

import com.w00tmast3r.reqn.HttpResponse;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import java.util.Arrays;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprResponseSpecificValues extends SimpleExpression<String> {
  
  static {
    PropertyExpression.register(ExprResponseSpecificValues.class, String.class,
        "%string% [response] header[ value][s]", "httpresponses");
  }
  
  private Expression<String> key;
  private Expression<HttpResponse> responses;
  
  @Override
  protected String[] get(Event e) {
    String key = this.key.getSingle(e);
  
    if (key == null) {
      return null;
    }
    
    return Arrays.stream(responses.getAll(e))
        .map(HttpResponse::getHeaders)
        .map(h -> h.get(key))
        .toArray(String[]::new);
  }
  
  @Override
  public boolean isSingle() {
    return responses.isSingle();
  }
  
  @Override
  public Class<? extends String> getReturnType() {
    return String.class;
  }
  
  @Override
  public String toString(@Nullable Event e, boolean debug) {
    return "specific header values";
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed,
                      SkriptParser.ParseResult parseResult) {
    switch (matchedPattern) {
      case 0:
        key = (Expression<String>) exprs[0];
        responses = (Expression<HttpResponse>) exprs[1];
        break;
      case 1:
        responses = (Expression<HttpResponse>) exprs[0];
        key = (Expression<String>) exprs[1];
        break;
    }
    return true;
  }
}
