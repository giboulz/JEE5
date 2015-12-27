package com.gbz.petstore.web;



import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.gbz.yaps.petstore.utils.Constants;
import com.gbz.yaps.petstore.utils.ExceptionUtils;

import java.util.Map;
import java.util.logging.Logger;


/**
 * @author Antonio Goncalves
 */
public abstract class Controller {

    // ======================================
    // =             Attributes             =
    // ======================================
    protected Logger logger = Logger.getLogger(Constants.LOGGER_JSF);
    private final String cname = this.getClass().getName();

    // ======================================
    // =             Constants              =
    // ======================================

    // ======================================
    // =            Constructors            =
    // ======================================

    // ======================================
    // =          Business methods          =
    // ======================================

    // ======================================
    // =          Protected methods         =
    // ======================================

    protected void addMessage(String sourceClass, String sourceMethod, Throwable throwable) {
        Throwable cause = ExceptionUtils.getRootCause(throwable);
        if (ExceptionUtils.isApplicationException(cause)) {
            addWarningMessage(cause.getMessage());
        } else {
            addErrorMessage(throwable.getMessage());
            logger.throwing(sourceClass, sourceMethod, throwable);
        }
    }

    protected void addWarningMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
    }

    protected void addErrorMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    protected String getParam(String param) {
        final String mname = "getParam";
        logger.entering(cname, mname);

        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        String result = map.get(param);

        logger.exiting(cname, mname, result);
        return result;
    }

    protected Long getParamId(String param) {
        final String mname = "getParamId";
        logger.entering(cname, mname);

        Long result = Long.valueOf(getParam(param));

        logger.exiting(cname, mname, result);
        return result;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    // ======================================
    // =         hash, equals, toString     =
    // ======================================
}