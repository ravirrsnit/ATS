package com.example.ats.Remote;

import com.example.ats.Model.MyResponse;
import com.example.ats.Model.Request;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IFCMService {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAUtBq_-w:APA91bFKp0m9cjp8w7zfvxvvxomEiat4KKMH9ry3EcfzKv3HbdmBtP_1Wh0dZR9jN8KyhGL6uLYBjndZ9ns27yyESkbeqK_Qa5Go0vuzc3hETE0xnAZWI8s5XvcfysvrftAFnbxMyN6p"
    })
    @POST("fcm/send")
    Observable<MyResponse> sendFriendRequestToUser(@Body Request body);
}
