package com.udacity.jwdnd.c1.review;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String getChatPage(@ModelAttribute("newChatForm") ChatForm newChatForm, Model model) {
        model.addAttribute("messageList",messageService.getMessageList());
        return "chat";
    }

    @PostMapping
    public String addChat(@ModelAttribute("newChatForm") ChatForm newChatForm, Model model) {
        messageService.addMessage(newChatForm.getUsername(), newChatForm.getMessageText(), newChatForm.getMessageType());
        model.addAttribute("messageList",messageService.getMessageList());
        return "chat";
    }
}
