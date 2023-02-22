package com.example.ats.Interface;

import java.util.List;

public interface IFirebaseLoadDone {
    void onFirebaseLoadUserNameDone(List<String> IstEmail);
    void onFirebaseLoadFailed(String message);

}
