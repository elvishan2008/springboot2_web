package com.toy_01.magicDobby.springboot.web;

import com.toy_01.magicDobby.springboot.service.posts.PostsService;
import com.toy_01.magicDobby.springboot.web.dto.PostsSaveRequestDto;
import com.toy_01.magicDobby.springboot.web.dto.PostsUpdateRequestsDto;
import com.toy_01.magicDobby.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestsDto requestsDto){
        return postsService.update(id, requestsDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }
}
