package com.redegal.priceApplicator.domain.exceptions;

public class PriceNotFoundException extends RuntimeException{
    public PriceNotFoundException(String message)
    {
        super(message);
    }
}
