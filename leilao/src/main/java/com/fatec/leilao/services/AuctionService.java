package com.fatec.leilao.services;

import com.fatec.leilao.entities.AuctionItem;
import com.fatec.leilao.repository.AuctionItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionService {

    private final AuctionItemRepository auctionItemRepository;

    public AuctionService(AuctionItemRepository auctionItemRepository) {
        this.auctionItemRepository = auctionItemRepository;
    }

    public List<AuctionItem> getAllItems() {
        return auctionItemRepository.findAll();
    }

    public AuctionItem addItem(AuctionItem item) {
        return auctionItemRepository.save(item);
    }

    public AuctionItem getItemById(Long id) {
        return auctionItemRepository.findById(id).orElse(null);
    }

    public void deleteItem(Long id) {
        auctionItemRepository.deleteById(id);
    }
}
