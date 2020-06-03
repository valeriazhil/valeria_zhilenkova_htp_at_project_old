package utilities.required_values;

import utilities.web_service.User;

import java.util.List;

public class RequiredValues {
    public String code;
    public List<User> data;

    public RequiredValues(String code, List<User> data) {
        this.code = code;
        this.data = data;
    }
}
