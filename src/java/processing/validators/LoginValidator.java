/**
 *
 * @author Thomas
 */
package processing.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validators.login")
public class LoginValidator implements Validator {

    private static final String LOGIN_PATTERN = " [a-zA-Z0-9-._]";
    private Pattern pattern;
    private Matcher matcher;

    public LoginValidator() {
        pattern = Pattern.compile(LOGIN_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
    throws ValidatorException {
        matcher = pattern.matcher(value.toString());
        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage("Login incorrect.", "Login Validation failed please follow the contraint " + LOGIN_PATTERN);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
