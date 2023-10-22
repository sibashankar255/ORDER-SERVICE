package com.myCode.order.service;


import com.myCode.order.dto.OrderDTO;
import com.myCode.order.dto.OrderDTOFromFE;
import com.myCode.order.dto.UserDTO;
import com.myCode.order.entity.Order;
import com.myCode.order.mapper.OrderMapper;
import com.myCode.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    RestTemplate restTemplate;



//    public OrderDTO saveOrderInDb(OrderDTOFromFE orderDetails) {
//        Integer newOrderID = sequenceGenerator.generateNextOrderId();
//        UserDTO userDTO = null; //fetchUserDetailsFromUserId(orderDetails.getUserId());
//        Order orderToBeSaved = new Order(newOrderID, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(), userDTO );
//        orderRepo.save(orderToBeSaved);
//        return OrderMapper.INSTANCE.mapOrderToOrderDTO(orderToBeSaved);
//    }

//    public Order saveOrderInDb(OrderDTOFromFE orderDetails) {
//        Integer newOrderID = sequenceGenerator.generateNextOrderId();
//        UserDTO userDTO = null; //fetchUserDetailsFromUserId(orderDetails.getUserId());
//        Order orderToBeSaved = new Order(newOrderID, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(), userDTO );
//        orderRepo.save(orderToBeSaved);
//        return OrderMapper.INSTANCE.mapOrderToOrderDTO(orderToBeSaved);
//    }

    public Order saveOrderInDb(OrderDTOFromFE orderDetails) {
        Integer newOrderID = sequenceGenerator.generateNextOrderId();
        UserDTO userDTO = fetchUserDetailsFromUserId(orderDetails.getUserId());
        Order orderToBeSaved = new Order(newOrderID, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(), userDTO );
        orderRepo.save(orderToBeSaved);
        return orderToBeSaved;
    }

    private UserDTO fetchUserDetailsFromUserId(Integer userId) {
        return restTemplate.getForObject("http://USER-SERVICE/user/fetchUserById/" + userId, UserDTO.class);
    }
}
