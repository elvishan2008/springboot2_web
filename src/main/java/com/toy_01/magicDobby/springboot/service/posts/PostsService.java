package com.toy_01.magicDobby.springboot.service.posts;

import com.toy_01.magicDobby.springboot.domain.posts.Posts;
import com.toy_01.magicDobby.springboot.domain.posts.PostsRepository;
import com.toy_01.magicDobby.springboot.web.dto.PostsListsResponseDto;
import com.toy_01.magicDobby.springboot.web.dto.PostsResponseDto;
import com.toy_01.magicDobby.springboot.web.dto.PostsSaveRequestDto;
import com.toy_01.magicDobby.springboot.web.dto.PostsUpdateRequestsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestsDto requestsDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestsDto.getTitle(), requestsDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts post = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(post);
    }

    @Transactional
    public List<PostsListsResponseDto> findAllDesc(){
        return postsRepository.findallDesc().stream()
                .map(PostsListsResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
}
