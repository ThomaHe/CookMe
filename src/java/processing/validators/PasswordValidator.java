/**
 *
 * @author Thomas
 */
package processing.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="validators.password")
public class PasswordValidator implements Validator {

@Override
public void validate(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {
    // Obtain the client ID of the first field from f:attribute.
    System.out.println(component.getFamily());
    UIInput password = (UIInput) component.getAttributes().get("pass");

    // Find the actual JSF component for the client ID.
    
    if (password == null)
        throw new IllegalArgumentException(String.format("pas de mot de passe",password));
    // Get its value, the entered text of the first field.
    String field1 = (String) password.getSubmittedValue();

    // Cast the value of the entered text of the second field back to String.
    String confirm = (String) value;

    // Check if the first text is actually entered and compare it with second text.
    if (field1 != null && field1.length() != 0 && !field1.equals(confirm)) {
        throw new ValidatorException(new FacesMessage("mot de passe différents."));
    }
}
}

