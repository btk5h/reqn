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

import com.btk5h.reqn.HttpResponse;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.Map;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.yggdrasil.Fields;

public class Types {
  static {
    Classes.registerClass(new ClassInfo<>(HttpResponse.class, "httpresponse")
        .user("httpresponses?")
        .parser(new Parser<HttpResponse>() {
          @Override
          public HttpResponse parse(String s, ParseContext context) {
            return null;
          }

          @Override
          public boolean canParse(ParseContext context) {
            return false;
          }

          @Override
          public String toString(HttpResponse o, int flags) {
            return o.toString();
          }

          @Override
          public String toVariableNameString(HttpResponse o) {
            return o.toString();
          }

          @Override
          public String getVariableNamePattern() {
            return ".*";
          }
        })
        .serializer(new Serializer<HttpResponse>() {
          @Override
          public Fields serialize(HttpResponse o) throws NotSerializableException {
            Fields fields = new Fields();
            fields.putPrimitive("code", o.getCode());
            fields.putObject("message", o.getMessage());
            fields.putObject("status", o.getStatusLine());
            fields.putObject("headers", o.getHeaders());
            fields.putObject("body", o.getBody());
            return fields;
          }

          @Override
          public void deserialize(HttpResponse o, Fields f) throws StreamCorruptedException,
              NotSerializableException {
            throw new UnsupportedOperationException();
          }

          @SuppressWarnings("unchecked")
          @Override
          protected HttpResponse deserialize(Fields fields) throws StreamCorruptedException,
              NotSerializableException {
            return new HttpResponse(
                fields.getPrimitive("code", int.class),
                fields.getObject("message", String.class),
                fields.getObject("status", String.class),
                fields.getObject("headers", Map.class),
                fields.getObject("body", String.class)
            );
          }

          @Override
          public boolean mustSyncDeserialization() {
            return false;
          }

          @Override
          public boolean canBeInstantiated() {
            return false;
          }
        }));
  }
}
