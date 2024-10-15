package com.tiennguyen.osahaneat.repository;

import com.tiennguyen.osahaneat.entity.OrderItem;
import com.tiennguyen.osahaneat.entity.keys.KeyOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, KeyOrderItem> {
}
