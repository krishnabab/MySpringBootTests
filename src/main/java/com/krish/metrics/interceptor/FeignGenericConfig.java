package com.krish.metrics.interceptor;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignGenericConfig implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
 
        switch (response.status()){
            case 400:
                return new Exception("400 error");
            case 404:
                return new Exception("not found");
            default:
                return new Exception("Generic error");
        }
    }

}