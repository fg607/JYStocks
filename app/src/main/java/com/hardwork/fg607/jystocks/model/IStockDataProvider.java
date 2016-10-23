package com.hardwork.fg607.jystocks.model;



import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by fg607 on 16-9-9.
 */
public interface IStockDataProvider {

    @GET("shcData")
    Observable<List<SHCData>> getSHCData();
}
