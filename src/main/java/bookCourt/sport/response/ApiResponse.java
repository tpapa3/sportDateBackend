package bookCourt.sport.response;

public class ApiResponse<T>  {
    private String status;
    private T data;
    private String message;

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.status = "success";
        response.data = data;
        response.message = null;
        return response;
    }

    // Constructor for error response
    public static <T> ApiResponse<T> error(String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.status = "error";
        response.message = message;
        response.data = null;
        return response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
