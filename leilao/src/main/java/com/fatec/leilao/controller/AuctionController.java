package com.fatec.leilao.controller;

import com.fatec.leilao.entities.AuctionItem;
import com.fatec.leilao.entities.vehicles.Car;
import com.fatec.leilao.services.AuctionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/auction")
public class AuctionController {

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping
    public String viewAuctionItems(Model model) {
        List<AuctionItem> items = auctionService.getAllItems();
        model.addAttribute("items", items);
        return "auction";  // Thymeleaf template
    }

    @PostMapping("/addCar")
    public String addCar(@RequestBody Car car) {
        auctionService.addItem(car);
        return "redirect:/auction";
    }

//    @PostMapping("/addDevices")
//    public String addSmartphone(@RequestBody UtilityDto utility) {
//        auctionService.addItem(utility);
//        return "redirect:/auction";
//    }

    @GetMapping("/item/{id}")
    @ResponseBody
    public AuctionItem viewAuctionItem(@PathVariable Long id) {
        return auctionService.getItemById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAuctionItem(@PathVariable Long id) {
        auctionService.deleteItem(id);
        return "redirect:/auction";
    }
}
