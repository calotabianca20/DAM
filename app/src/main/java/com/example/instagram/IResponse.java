package com.example.instagram;

import java.util.List;

public interface IResponse {
    void onSuccess(List<Postare> postari);
    void onError(String mesaj);
}
