package library.avenir.test.dto.error;

import lombok.Data;

@Data
public class ErrorDto {
    private Integer code;
    private String message;

    public void setMessage(String localizedMessage) {
    }

    public void setCode(int value) {
    }
}
