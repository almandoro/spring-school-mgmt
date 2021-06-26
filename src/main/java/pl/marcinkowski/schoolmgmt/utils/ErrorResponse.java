package pl.marcinkowski.schoolmgmt.utils;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponse {
    static public ResponseEntity error(String errorMsg){
        ResponseEntity res = new ResponseEntity(new ErrorMessage(errorMsg),HttpStatus.BAD_REQUEST);
        return res;
    }
}
