package com.crio.starter.service;

import java.util.List;
import java.util.Optional;
import com.crio.starter.data.Meme;
import com.crio.starter.exchange.MemeDTO;
import com.crio.starter.repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MemeService {

    @Autowired
    private MemeRepository memeRepository;

    // Save a new meme
    public Meme saveMeme(MemeDTO memeDTO) {
        if (memeRepository.existsByNameAndUrl(memeDTO.getName(), memeDTO.getUrl())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Meme already exists");
        }
        Meme meme = new Meme();
        meme.setName(memeDTO.getName());
        meme.setUrl(memeDTO.getUrl());
        meme.setCaption(memeDTO.getCaption());   
        return memeRepository.save(meme);
    }

    // Get the latest 100 memes
    public List<Meme> getLatestMemes() {
        return memeRepository.findTop100ByOrderByIdDesc();
    }

    // Get a specific meme by id
    public Optional<Meme> getMemeById(String id) {
        return memeRepository.findById(id);
    }
    
    public boolean existsByNameAndUrl(String name, String url){
        return memeRepository.existsByNameAndUrl(name, url);
    }
}