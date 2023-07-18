package com.platzi.pizza.persistence.repositories;

import com.platzi.pizza.persistence.entities.OrderEntity;
import com.platzi.pizza.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends ListCrudRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByDateAfter(LocalDateTime dateTime);
    List<OrderEntity> findAllByMethodIn(List<String> methods);
    @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);
    @Query(value = "SELECT po.id_order AS idOrder, cu.name AS customerName, po.date AS orderDate, " +
                   "po.total AS orderTotal, STRING_AGG(PI.name, ', ') AS pizzaNames " +
                   "FROM pizza_order po " +
                   "    INNER JOIN customer cu ON po.id_customer = cu.id_customer " +
                   "    INNER JOIN order_item oi ON po.id_order = oi.id_order " +
                   "    INNER JOIN pizza PI ON oi.id_pizza = PI.id_pizza " +
                   "WHERE po.id_order = :orderId " +
                   "GROUP BY po.id_order, cu.id_customer, po.date, po.total", nativeQuery = true)
    OrderSummary findSummary(@Param("orderId") Long orderId);
}
