package models.lombok;

import lombok.Data;

@Data
public class LoginBodyLombokModel {
    //String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
    String email, password;

}
