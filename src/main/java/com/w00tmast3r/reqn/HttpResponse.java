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

package com.w00tmast3r.reqn;

import java.util.Map;

import static java.util.stream.Collectors.*;

public class HttpResponse {
  
  private final int code;
  private final String message;
  private final String statusLine;
  private final Map<String, String> headers;
  private final String body;
  
  public HttpResponse(int code, String message, String statusLine, Map<String, String> headers,
                      String body) {
    this.code = code;
    this.message = message;
    this.statusLine = statusLine;
    this.headers = headers;
    this.body = body;
  }
  
  public int getCode() {
    return code;
  }
  
  public String getMessage() {
    return message;
  }
  
  public String getStatusLine() {
    return statusLine;
  }
  
  public Map<String, String> getHeaders() {
    return headers;
  }
  
  public String getBody() {
    return body;
  }
  
  @Override
  public String toString() {
    return statusLine + "\n" +
        headers.entrySet().stream()
            .map(e -> e.getKey() + ": " + e.getValue())
            .collect(joining("\n", "", "\n\n"))
        + body;
  }
}
