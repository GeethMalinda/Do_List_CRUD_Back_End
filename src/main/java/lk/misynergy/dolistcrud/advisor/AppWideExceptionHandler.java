package lk.misynergy.dolistcrud.advisor;

import lk.misynergy.dolistcrud.util.StandradResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class AppWideExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity handleException(RuntimeException ex) {
        StandradResponse response = new StandradResponse(500, "Error", ex.getMessage());
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
