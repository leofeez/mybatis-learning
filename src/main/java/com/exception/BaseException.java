package com.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author leofee
 * @Date: 2019/1/11
 */
@Setter
@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException{

    private String message;
}
