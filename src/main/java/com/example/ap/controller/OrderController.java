package com.example.ap.controller;

 
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ap.data.Order;
import com.example.ap.service.OrderService;

@RestController
@RequestMapping(path = "pizzeria")
public class OrderController {

	Stack<Order> stk = new Stack<>(); 
  private final OrderService orderService;
 

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public List<Order> getOrders() {
    return orderService.getOrders();
  }
  @GetMapping(path = "{orderId}")
  public Optional<Order> getOrders(@PathVariable Long orderId) {
	  return orderService.getOrders(orderId);
  }

  @GetMapping(value = "check")
  public Order checkOrder() {
	  Order o = new Order(); 
	  o = stk.lastElement();
	  return o;
	  
  }
  @GetMapping(value = "checkState/{orderId}")
  public String checkOrders(@PathVariable Long orderId) {
	  return checkOrdersService(orderId);
	  
  }

private String checkOrdersService(Long orderId) {
	Order o = new Order(); 
	  String s = "";
	  boolean flag = false;
	  for(int i = 0 ; i<stk.size()&& !flag;i++) {
		  o = stk.get(i);
		  if(o.getId()==orderId) {
			s= o.getState();
			flag=true;
		  }
	  }
	  return s;
}
  @PostMapping
  public void addNewOrder(@RequestBody Order order) {
    orderService.addNewOrder(order);
    stk.push(order);
  }
  
  @PutMapping(value = "changeState/{orderId}")
  public void changeState(@PathVariable Long orderId,@RequestBody Order order)
  {
	  Order updatedOrder = orderService.getOrders(orderId).get();
	  updatedOrder.setState(order.getState());
	  orderService.updateOrder(updatedOrder);
	  updateOrdersService(updatedOrder);
	  
  }

  @DeleteMapping(path = "{orderId}")
  public void deleteOrder(@PathVariable Long orderId) {
    orderService.deleteOrder(orderId);
  }
  
  private void updateOrdersService(Order orderUpdated) {
		Order o = new Order(); 
		
		  boolean flag = false;
		  for(int i = 0 ; i<stk.size()&& !flag;i++) {
			  o = stk.get(i);
			  if(o.getId()==orderUpdated.getId()) {
				 o.setState(orderUpdated.getState());
				flag=true;
			  }
		  }
		 
	}

}
