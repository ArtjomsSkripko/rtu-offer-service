package offer.authorization;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Error response")
public class ErrorResponse  {

    private String name;
    private int code;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
