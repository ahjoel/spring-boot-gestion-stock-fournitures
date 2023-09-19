package com.gestock.fourniture.model.entities;

public class ErrorResponse {
        private String errorCode;
        private String message;

        // Constructors, getters, and setters

        public ErrorResponse() {
        }

        public ErrorResponse(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }

        // Getter and Setter methods
        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
}

