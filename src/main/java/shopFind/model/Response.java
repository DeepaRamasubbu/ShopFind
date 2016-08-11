package shopFind.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Response {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Boolean errorCode) {
        this.errorCode = errorCode;
    }

    private Boolean errorCode;

}
