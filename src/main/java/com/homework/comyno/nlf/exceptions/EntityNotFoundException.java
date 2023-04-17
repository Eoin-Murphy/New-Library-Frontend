package com.homework.comyno.nlf.exceptions;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(String entityType, String entityId){
    super(String.format("The %s with id %s cannot be found", entityType, entityId));
  }
}
