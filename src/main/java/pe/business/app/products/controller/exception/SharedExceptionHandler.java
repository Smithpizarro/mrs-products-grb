package pe.business.app.products.controller.exception;


import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.business.app.products.model.ErrorMessageT;
import pe.business.app.products.model.TransactionRs;
import pe.business.app.products.util.ErrorMessageBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class SharedExceptionHandler {

    public static final String MESSAGE_BAD_REQUEST="Los datos ingresados son invalidos.";

    public static final String MESSAGE_GENERIC_SERVER="Lo sentimos, estamos solucionando el problema.";

    @Autowired
    private ErrorMessageBuilder errorMessageBuilder;

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<TransactionRs<Object>> handleIllegalArgumentException(final IllegalArgumentException ex)throws IOException {
        TransactionRs<Object> response = new TransactionRs<>();
        response.setDescripcion(MESSAGE_BAD_REQUEST);

        ErrorMessageT errorMessageDef = ErrorMessageT.builder()
                .code("01")
                .messages(Arrays.asList(ex.getMessage())).build();
        response.setRespuesta(errorMessageDef);
        return new ResponseEntity<TransactionRs<Object>>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<TransactionRs<Object>> handleAppException(ConstraintViolationException ex)throws IOException {
        TransactionRs<Object> response = new TransactionRs<>();
        response.setDescripcion(MESSAGE_BAD_REQUEST);
        String errorMessage = new ArrayList<>(ex.getConstraintViolations()).get(0).getMessage();

        List<String> errors =(ex.getConstraintViolations()).stream()
                .map(err ->{
                    return err.getMessage();
                }).collect(Collectors.toList());
        ErrorMessageT errorMessageDef = ErrorMessageT.builder()
                .code("01")
                .messages(errors).build();
        response.setRespuesta(errorMessageDef);
        return new ResponseEntity<TransactionRs<Object>>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<TransactionRs<String>> handleGeneralException(HttpServletRequest req, Exception ex) {

        TransactionRs<String> response = new TransactionRs<>();
        //String msj = errorMessageBuilder.buildMessageByCode(Constants.GENERAL_ERROR_CODE);
        response.setDescripcion(MESSAGE_GENERIC_SERVER);
        response.setRespuesta(null);
        return new ResponseEntity<TransactionRs<String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseEntity<TransactionRs<String>> handleServiceException(HttpServletRequest req, ServiceException ex) {

        String code = ex.getMessage();
        TransactionRs<String> response = new TransactionRs<>();
        if(code!=null){
            String	msj = errorMessageBuilder.buildMessageByCode(code);
            response.setDescripcion(msj);
            response.setRespuesta(null);
        }else{
            String msj = errorMessageBuilder.buildMessageByCode("7002");
            response.setDescripcion(msj);
            response.setRespuesta(null);
        }
        return new ResponseEntity<TransactionRs<String>>(response,  HttpStatus.CONFLICT);
    }

}
