Spring Framework provides several methods for exception handling. In this project 3 methods will shown:

**Exception annotated with @ResponseStatus:**
All unhandled exception thrown during request processing causes HTTP 500 response.
Our custom exception (see `OrderNotFoundException.java`) can be annotated with @ResponseStatus annotation which supports all defined HTTP statuses. When it will be thrown and not handled, the server will automatically return http response with defined status code.

**Controller based exception handling using @ExceptionHandler annotation:**
Another type of exception handling is using controller-based methods annotated with @ExceptionHandler annotation.
For example see `OrderController.java`, method `handle()`. Execption hadler methods are very flexible and developer can pass objects such as HttpServletRequest, Exception, etc.

**Global exception handling using @ControllerAdvice:**
Controller advice is used for exception handling and applied to the whole application.
For example see `GlobalExceptionHandler.java` and `SessionController.java`. `find()` endpoint in session controller can throw SessionNotFoundException and UserNotFoundException.
In case of SessionNotFoundException `handleException()` method of `GlobalExceptionHandler` object will be invoked and then response with 400 http status code will be returned.

**What to use? When to use?**
1. Add `@ResponseStatus` annotation to your exception;
2. Use `@ControllerAdvice` class for all other exceptions;
3. Use `@ExceptionHandler` annotated method for controller-specific exceptions.

Be careful to use several technics in the same application. You may get undefined behaviour if the same exception will be handled in more than one way.