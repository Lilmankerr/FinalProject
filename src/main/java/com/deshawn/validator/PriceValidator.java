package com.deshawn.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator
public class PriceValidator implements Validator {
    private String price = "^\\$?([1-9]{1}[0-9]{0,2}(\\,[0-9]{3})*(\\.[0-9]{0,2})?|[1-9]{1}[0-9]{0,}(\\.[0-9]{0,2})?|0(\\.[0-9]{0,2})?|(\\.[0-9]{1,2})?)$";


    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object name) {
        Matcher matcher = Pattern.compile(price).matcher(name.toString());
        if (!matcher.matches()) {
            FacesMessage msg =
                    new FacesMessage("Price should be entered in 'XXX' or'XXX.XX' format");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
