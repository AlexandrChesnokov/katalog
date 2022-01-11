package com.controller.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.User;
import com.model.websocket.Message;
import com.service.CartService;
import com.service.UserService;
import com.util.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final CartService cartService;

    private final UserService userService;

    public MessageController(CartService basketService, SimpMessagingTemplate simpMessagingTemplate, UserService userService) {
        this.cartService = basketService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.userService = userService;
    }

    @MessageMapping("/hello")
    public void send(SimpMessageHeaderAccessor sha,@Payload Message message) {

        User currentUser = userService.findByEmail(sha.getUser().getName());

        if (message.getAction() == 1) {
            log.info("WS: Received a message from {} to add a product with ID {} to the cart", currentUser.getEmail(), message.getProductId());
            cartService.addProductToCart(currentUser.getId(), Integer.parseInt(message.getProductId()));
            log.info("WS: Product with ID {} has been successfully added to the {} cart", message.getProductId(), currentUser.getEmail());
        }
        else if (message.getAction() == 2) {
            log.info("WS: Received a message from {} to delete a product with ID {} from the cart", currentUser.getEmail(), message.getProductId());
            cartService.deleteProductFromCart(currentUser.getId(), Integer.parseInt(message.getProductId()));
            log.info("WS: Product with id {} has been successfully removed from the {} basket", message.getProductId(), currentUser.getEmail());
        }

        String jsonResponse = null;
        try {
            jsonResponse = Converter.convertObjectToJsonString(cartService.showCartByUser(currentUser.getId()));
        } catch (JsonProcessingException e) {
            log.error("WS: " + e);
        }

        simpMessagingTemplate.convertAndSendToUser(currentUser.getEmail(), "/queue/messages", jsonResponse);
    }
}
