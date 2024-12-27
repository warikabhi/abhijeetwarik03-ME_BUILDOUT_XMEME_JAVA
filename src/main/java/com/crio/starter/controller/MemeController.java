package com.crio.starter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import com.crio.starter.data.Meme;
import com.crio.starter.exchange.MemeDTO;
import com.crio.starter.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memes")
public class MemeController {

    @Autowired
    private MemeService memeService;

    @PostMapping("/")
public ResponseEntity<?> createMeme(@Valid @RequestBody MemeDTO memeDTO) {
    if (memeDTO.getName() == null || memeDTO.getName().isEmpty() ||
        memeDTO.getUrl() == null || memeDTO.getUrl().isEmpty() ||
        memeDTO.getCaption() == null || memeDTO.getCaption().isEmpty()) {
        
        // If any of the required fields are missing, return a 400 Bad Request
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Name, URL, and Caption are required fields.");
    }

    // Meme meme = new Meme();
    // meme.setName(memeDTO.getName());
    // meme.setUrl(memeDTO.getUrl());
    // meme.setCaption(memeDTO.getCaption());
    Meme savedMeme = memeService.saveMeme(memeDTO);
    
    // Return the ID of the saved meme
    Map<String, String> response = new HashMap<>();
    response.put("id", savedMeme.getId());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}


@GetMapping("/")
public ResponseEntity<List<Meme>> getLatestMemes() {
    List<Meme> memes = memeService.getLatestMemes();
    return ResponseEntity.ok(memes);  // Directly return the memes
}

    @GetMapping("/{id}")
public ResponseEntity<Meme> getMemeById(@PathVariable String id) {
    Optional<Meme> meme = memeService.getMemeById(id);

    if (meme.isPresent()) {
        return ResponseEntity.ok(meme.get()); // Return the meme if found
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 if not found
    }
}
    
}
