package com.mini.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mini.project.enums.MessageType;
import com.mini.project.enums.StatusType;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ResponseDto {
    private StatusType status;
    private Object data;
    private MessageType messageType;

    @JsonIgnore
    public ResponseEntity<ResponseDto> getResponseEntity(){
        HttpStatus httpStatus = null;
        if(status.equals(StatusType.SUCCESS)){
            httpStatus = HttpStatus.OK;
        }else if(status.equals(StatusType.ERROR) || status.equals(StatusType.INVALID)){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        else{
            httpStatus = HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<>(this, httpStatus);
    }

}
