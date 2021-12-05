package com.example.instagram;

import java.util.List;

public interface IResponse {
    void onSuccess(List<Post> postari);
    void onError(String mesaj);
}
