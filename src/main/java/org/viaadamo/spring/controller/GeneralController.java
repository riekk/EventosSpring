package org.viaadamo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.viaadamo.spring.entity.Host;
import org.viaadamo.spring.service.HostService;

@ControllerAdvice
public class GeneralController {

    private HostService hostService;

    public GeneralController(HostService hostService) {
        this.hostService = hostService;
    }

    @ModelAttribute
    private void populateModel(Model model) {
        model.addAttribute("hostList", hostService.findAll());
    }

}
