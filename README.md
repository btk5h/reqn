# Reqn
> Pronounced `/ˈɹɛkən/`, like "reckon".

Reqn is an HTTP request library for Skript 2.X.

## Syntax

### Effect `Send Web Request`

Submits an HTTP request to the given URL. This

#### Syntax

`send [a[n]] [http] [%method%] [web] request to [the] [url] %url% [with ([the] header[s]
%headers% [and [the] body %body%]|[the] body %body% [and [the] header[s] %headers%])]`

*The last part of the syntax simply allows the header and body to be written in either order
(e.g. `with the headers and the body` or `with the body and the headers`)*

#### Parameters

- `method` (type `text`) - The HTTP method to use to send the request.
If omitted, defaults to `"get"`.
- `url` (type `text`) - The URL to send the request to.
- `headers` (type `texts`) - One or more headers in the format `"Header-Name: Value"` to be sent 
in the request header.
- `body` (type `texts`) - Additional data to be sent in the body of the request. Multiple texts 
are joined with new lines. This cannot be sent if the request uses the `"get"` method.

---

### Expression `Last Response` => `httpresponse`

Returns a custom `httpresponse` object that contains information from the last completed http 
request.

#### Syntax

`[the] [last[ly]] [received] [http] [web] response`

---

### Expression `Response Code` => `number`

Returns the status codes of one or more `httpresponses`.

#### Syntax

`[the] [response] [status] code[s] of %httpresponses%` 
or
`%httpresponses%'[s] [response] [status] code[s]`

---

### Expression `Response Message` => `text`

Returns the status messages of one or more `httpresponses`.

#### Syntax

`[the] [response] [status] (message|reason)[s] of %httpresponses%` 
or
`%httpresponses%'[s] [response] [status] (message|reason)[s]`

---

### Expression `Response Status` => `text`

Returns the status lines of one or more `httpresponses`.

#### Syntax

`[the] [response] status[(es| line[s])] of %httpresponses%` 
or
`%httpresponses%'[s] [response] status[(es| line[s])]`

---

### Expression `Response Headers` => `texts`

Returns the headers of one or more `httpresponses` in the format `"Header-Name: Value"`.

Multiple `httpresponses` may be passed to this expression, but the resulting headers will be 
combined into a single list with no way to associate each header with its original `httpresponse`.

#### Syntax

`[the] [response] header[s] of %httpresponses%` 
or
`%httpresponses%'[s] [response] header[s]`

---

### Expression `Response Header Keys` => `texts`

Returns the header keys (names) of one or more `httpresponses`. This expression is best used in 
conjunction with `Response Header Values`

Multiple `httpresponses` may be passed to this expression, but the resulting keys will be 
combined into a single list with no way to associate each key with its original `httpresponse`.

#### Syntax

`[the] [response] header key[s] of %httpresponses%` 
or
`%httpresponses%'[s] [response] header key[s]`

---

### Expression `Response Header Values` => `texts`

Returns the header values of one or more `httpresponses`. This expression is best used in 
conjunction with `Response Header Keys`

Multiple `httpresponses` may be passed to this expression, but the resulting values will be 
combined into a single list with no way to associate each value with its original `httpresponse`.

#### Syntax

`[the] [response] header key[s] of %httpresponses%` 
or
`%httpresponses%'[s] [response] header key[s]`

---

### Expression `Specific Response Header Value` => `text`

Returns the values of a specific header for one or more `httpresponses`.

Multiple `httpresponses` may be passed to this expression, but the resulting values will be 
combined into a single list with no way to associate each value with its original `httpresponse`.

#### Syntax

`[the] %header% [response] header[ value][s] of %httpresponses%` 
or
`%httpresponses%'[s] %header% [response] header[ value][s]`

#### Parameters

- `header` (type `text`) - The name of the header to return.

---

### Expression `Response Body` => `text`

Returns the bodies of one or more `httpresponses`.

#### Syntax

`[the] [response] bod(y|ies) of %httpresponses%` 
or
`%httpresponses%'[s] [response] bod(y|ies)`

---

### Expression `URL Safe Text` => `text`

Converts a text into a text safe for usage in URLs. This can be useful for injecting user input 
into a URL (e.g. when using a search api).

#### Syntax

`(http|ur(i|l)) (safe|encoded|escaped) %input%`

#### Parameters

- `input` (type `texts`) - One or more input texts to encode. 

---

### Expression `JSON Safe Text` => `text`

Converts a text into a text safe for usage in JSON strings. This can be useful for injecting user 
input into a JSON payload (e.g. when using a search api).

#### Syntax

`json (safe|encoded|escaped) %input%`

#### Parameters

- `input` (type `texts`) - One or more input texts to encode. 

---

## Contributing

Feel free to submit pull requests, just make sure your changes are consistent with 
[Google's Java code style](https://google.github.io/styleguide/javaguide.html)!

