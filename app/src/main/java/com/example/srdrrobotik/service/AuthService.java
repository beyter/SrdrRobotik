package com.example.srdrrobotik.service;

import com.example.srdrrobotik.model.UserAuthInfo;
import com.google.firebase.auth.FirebaseAuth;

public class AuthService {
    private FirebaseAuth firebaseAuth;

    public AuthService() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public boolean signInWithEmailPassword(UserAuthInfo userInfo){

        return false;
    }
}
