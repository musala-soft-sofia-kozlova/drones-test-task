package musala.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.ServletException;
import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionsHandler {
	public static final String ERROR_MESSAGES_DELIMITER = ";";

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<String> handlerMethodArgument(MethodArgumentNotValidException e) {
		List<ObjectError> errors = e.getAllErrors();
		String body = errors.stream().map(err -> err.getDefaultMessage())
				.collect(Collectors.joining(ERROR_MESSAGES_DELIMITER));
		return errorResponse(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BindException.class)
	ResponseEntity<String>  handleConstraintViolationException(BindException e) {
		String message = e.getMessage();
		return errorResponse(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ IllegalStateException.class, IllegalArgumentException.class, HttpMessageNotReadableException.class })
	ResponseEntity<String> badRequest(Exception e) {
		String message = e.getMessage();
		return errorResponse(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ NotFoundException.class })
	ResponseEntity<String> notFound(NotFoundException e) {
		String message = e.getMessage();
		return errorResponse(message, HttpStatus.NOT_FOUND);
	}

	private ResponseEntity<String> errorResponse(String body, HttpStatus status) {
		log.error(body);
		return new ResponseEntity<>(body, status);
	}

	    @ExceptionHandler({NullPointerException.class})
	    public ResponseEntity<String> handleNullPointerException(NullPointerException e){
	    	String message = e.getMessage();
			return errorResponse(message, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler({ServletException.class})
	    public ResponseEntity<String> handleMethodArgumentNotValidException(ServletException e){
	    	String message = e.getMessage();
			return errorResponse(message, HttpStatus.NOT_FOUND);
	    }


	    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
	    public ResponseEntity<String> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e){
	    	String message = e.getMessage();
			return errorResponse(message, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler( {UnexpectedTypeException.class})
	    public ResponseEntity<String> handleHttpMediaTypeNotSupportedException( UnexpectedTypeException e){
	    	String message = e.getMessage();
			return errorResponse(message, HttpStatus.NOT_FOUND);
	    }

}
