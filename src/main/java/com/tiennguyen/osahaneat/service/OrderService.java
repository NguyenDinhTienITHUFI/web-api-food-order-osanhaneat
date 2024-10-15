package com.tiennguyen.osahaneat.service;

import com.tiennguyen.osahaneat.entity.*;
import com.tiennguyen.osahaneat.entity.keys.KeyOrderItem;
import com.tiennguyen.osahaneat.payload.request.OrderRequest;
import com.tiennguyen.osahaneat.repository.OrderItemRepository;
import com.tiennguyen.osahaneat.repository.OrderRepository;
import com.tiennguyen.osahaneat.service.imp.OrderServiceImp;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService implements OrderServiceImp {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public boolean insertOrder(OrderRequest orderRequest) {
        try {
            Users users=new Users();
            users.setId(orderRequest.getUserID());

            Restaurant restaurant=new Restaurant();
            restaurant.setId(orderRequest.getRestID());

            Orders orders=new Orders();
            orders.setUsers(users);
            orders.setRestaurant(restaurant);
            orderRepository.save(orders);

            List<OrderItem> items=new ArrayList<>();
            for(int idFood:orderRequest.getFoodIDs()){
                Food food=new Food();
                food.setId(idFood);

                OrderItem orderItem=new OrderItem();
                KeyOrderItem keyOrderItem=new KeyOrderItem(orders.getId(), idFood);
                orderItem.setKeyOrderItem(keyOrderItem);


                items.add(orderItem);
            }
            orderItemRepository.saveAll(items);
            return true;
        }
        catch (Exception e){
            System.out.println("Error insert order "+e.getMessage());
            return false;
        }

    }
}
