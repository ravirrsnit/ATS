package com.example.ats.Utils;

import com.example.ats.Model.User;
import com.example.ats.Remote.IFCMService;
import com.example.ats.Remote.RetrofitClient;

public class Common {

    public static final String USER_INFORMATION = "UserInformation";
    public static final String USER_UID_SAVE_KEY = "SaveUid";
    public static final String TOKENS = "Tokens" ;
    public static final String FROM_NAME = "FromName" ;
    public static final String ACCEPT_LIST = "acceptList" ;
    public static final String FROM_UID = "FromUid" ;
    public static final String TO_UID = "ToUid" ;
    public static final String TO_NAME = "ToName" ;
    public static User loggedUser;

    public static IFCMService getFCMService(){
        return RetrofitClient.getClient("http://fcm.googleapis.com/")
                .create(IFCMService.class);
    }
}
