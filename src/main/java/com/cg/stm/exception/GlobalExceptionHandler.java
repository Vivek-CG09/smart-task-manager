package com.cg.stm.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

/**
 * Global exception handler for the STM (Smart Task Manager) application.
 * This class handles exceptions thrown across all controllers and converts them
 * into standardized RFC 7807 Problem Detail responses.
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles TaskNotFoundException and converts it to a standardized HTTP 404 response.
     *
     * This method creates a comprehensive error response following RFC 7807 Problem Detail
     * specification, including contextual information about the request for debugging
     * and monitoring purposes.
     *
     * @param ex the TaskNotFoundException that was thrown
     * @param request the HttpServletRequest that caused the exception
     * @return ProblemDetail object containing error information and request context
     *
     * @see TaskNotFoundException
     * @see ProblemDetail
     * @see <a href="https://tools.ietf.org/html/rfc7807">RFC 7807 - Problem Details for HTTP APIs</a>
     */
    @ExceptionHandler(TaskNotFoundException.class)
    public ProblemDetail handleTaskNotFoundException(TaskNotFoundException ex,
                                                     HttpServletRequest request) {
        String message = ex.getMessage() != null ? ex.getMessage() : "Task not found";
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, message);

        problemDetail.setTitle("Task Not Found");

//        Unique identifier for request tracing and correlation across logs
        problemDetail.setProperty("requestId", request.getRequestId());

//        ISO-8601 formatted timestamp when the error occurred
        problemDetail.setProperty("timestamp", Instant.now());

//        HTTP method used (GET, POST, PUT, DELETE, etc.) for request analysis
        problemDetail.setProperty("method", request.getMethod());

//        Client application identifier for compatibility and debugging
        problemDetail.setProperty("userAgent", request.getHeader("User-Agent"));

//        Server details
        problemDetail.setProperty("serverName", request.getServerName());
        problemDetail.setProperty("serverPort", request.getServerPort());

//        HTTP protocol version (HTTP/1.1, HTTP/2) for protocol-specific issues
        problemDetail.setProperty("protocol", request.getProtocol());
        return problemDetail;
    }
}
