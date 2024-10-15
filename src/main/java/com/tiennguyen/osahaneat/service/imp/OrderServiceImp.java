package com.tiennguyen.osahaneat.service.imp;

import com.tiennguyen.osahaneat.payload.request.OrderRequest;

public interface OrderServiceImp {
    boolean insertOrder(OrderRequest orderRequest);
}
