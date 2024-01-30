package org.viaadamo.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.viaadamo.spring.entity.Host;
import org.viaadamo.spring.service.HostService;

import java.util.List;

@Controller
@RequestMapping("/view/hosts")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping("/all")
    public String getHosts(Model model) {
        model.addAttribute("hosts", hostService.findAll());
        return "host/hosts";
    }
    @ModelAttribute
    public void getHostList (Model model) {
        model.addAttribute("hosts", hostService.findAll());
    }

}
