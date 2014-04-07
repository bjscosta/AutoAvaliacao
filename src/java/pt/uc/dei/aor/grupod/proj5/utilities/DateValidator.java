/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.aor.grupod.proj5.utilities;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author User
 */
@FacesValidator("dateRangeValidator")
public class DateValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {

            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Precisa de preencher todos os campos", null));
        }

        UIInput startDateComponent = (UIInput) component.getAttributes().get("startDateComponent");

        if (!startDateComponent.isValid()) {
            startDateComponent.setValid(false);
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Precisa de preencher todos os campos", null));
        }

        Date startDate = (Date) startDateComponent.getValue();

        if (startDate == null) {
            startDateComponent.setValid(false);
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Precisa de preencher todos os campos", null));
        }

        Date endDate = (Date) value;

        if (startDate.after(endDate)) {
            startDateComponent.setValid(false);
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Data de inicio não pode ser depois da data de fim", null));
        }
    }

}
