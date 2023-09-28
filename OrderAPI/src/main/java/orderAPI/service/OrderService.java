package orderAPI.service;

import orderAPI.dto.ItemDto;
import orderAPI.dto.mapper.ItemMapper;
import orderAPI.models.Cart;
import orderAPI.repository.OrderRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepositry orderRepositry;
    @Autowired
    private RestTemplate restTemplate;

    public Cart createOrder(Cart cart)
    {
        Boolean ch = orderValidation( cart ); // orderValidation methos valided productQuantity, Couponcode, BankBalance
        if(ch)
        {
            Cart cart1 = orderRepositry.save(cart);
            return cart1;
        }

        orderExecution(cart);

        return cart;
    }

    private void orderExecution(Cart cart) {

        //  productQuantity,


        // Couponcode,


        // BankBalance
    }

    private Boolean orderValidation(Cart cart) {

        // validate productQuantity

       Boolean ProductValidation;
       ItemDto itemDto = ItemMapper.INSTANCE.itemToItemDTO(cart.getItems().get(0));

        ProductValidation = restTemplate.getForObject("http://localhost:8081/stockItems/products/quantity/"
                + itemDto.getProductId()+"/" + itemDto.getQuantity() ,Boolean.class);

        // validate couponCode


        // validate BankBalance


        return ProductValidation;
    }

    public List<Cart> getOrdersByEmail(String email) {
        return null;
    }

    public List<Cart> getOrdersWithinPeriod(Date start, Date end) {
        return null;
    }
}
