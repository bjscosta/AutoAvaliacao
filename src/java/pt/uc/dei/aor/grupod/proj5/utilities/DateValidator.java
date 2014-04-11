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
 * @author Bruno Costa
 * @author Pedro Pamplona
 */
@FacesValidator("dateRangeValidator")
public class DateValidator implements Validator {

    /**
     * this method validates the dates that were chosen by the user
     *
     * @param context
     * @param component
     * @param value
     * @throws ValidatorException
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {

            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Precisa de preencher todos os campos", null));
        }

        UIInput startDateComponent = (UIInput) component.getAttributes().get("startDateComponent");
        UIInput editStartDateComponent = (UIInput) component.getAttributes().get("editStartDateComponent");

        if (startDateComponent != null) {
            validateStart(context, startDateComponent, value);
        }

        if (editStartDateComponent != null) {
            validateEditStart(context, editStartDateComponent, value);
        }

    }

    /**
     * this method validates the dates that were chosen by the user
     *
     * @param context
     * @param startDateComponent
     * @param value
     */
    public void validateStart(FacesContext context, UIInput startDateComponent, Object value) {
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

    /**
     * this method validates the dates that were chosen by the user
     *
     * @param context
     * @param editStartDateComponent
     * @param value
     */
    public void validateEditStart(FacesContext context, UIInput editStartDateComponent, Object value) {
        if (!editStartDateComponent.isValid()) {
            editStartDateComponent.setValid(false);
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Precisa de preencher todos os campos", null));
        }

        Date startDate = (Date) editStartDateComponent.getValue();

        if (startDate == null) {
            editStartDateComponent.setValid(false);
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Precisa de preencher todos os campos", null));
        }

        Date endDate = (Date) value;

        if (startDate.after(endDate)) {
            editStartDateComponent.setValid(false);
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Data de inicio não pode ser depois da data de fim", null));
        }
    }

}
