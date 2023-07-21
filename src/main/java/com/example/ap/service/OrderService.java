package com.example.ap.service;

 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ap.data.Order;
import com.example.ap.data.OrderRepository;
import com.example.ap.utility.State;

@Service
public class OrderService {
	

	
	@Autowired
  private final OrderRepository orderRepository;

	@Autowired
  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public List<Order> getOrders() {
    return orderRepository.findAll();
  }
  public Optional<Order> getOrders(Long orderId) {
	  return orderRepository.findById(orderId);
//	  .orElseThrow(()-> new InvalidConfigurationPropertyValueException());
  }

  public void addNewOrder(Order order) {
	  order.setState(State.C.getStato());
    orderRepository.save(order);
  }
  public void updateOrder(Order order) {
	  orderRepository.save(order);
  }

  public void deleteOrder(Long orderId) {
    orderRepository.deleteById(orderId);
  }
}

