package org.nrj.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Error Controller - In Case of fallback to "\error"
 * @author Neeraj Suthar
 *
 */
@RestController
public class ErrorControl implements ErrorController {
	@RequestMapping("/error")
	public Map<String, String> handleError(HttpServletRequest request) {
		Map<String, String> errMap = new LinkedHashMap<>();
		Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String statusMessage = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
		Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		String requestedPath = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

		
		errMap.put("status-code",statusCode+"");
		errMap.put("status-desc",HttpStatus.Series.valueOf(statusCode).toString());
		errMap.put("requested-path",requestedPath);
		errMap.put("status-message",statusMessage);
		errMap.put("error-message",null==exception? "No Exception occured.": 
			exception.getClass() + " - " + exception.getMessage());

		return errMap;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}