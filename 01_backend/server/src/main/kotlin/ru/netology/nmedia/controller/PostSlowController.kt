package ru.netology.nmedia.controller

import org.springframework.web.bind.annotation.*
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.service.PostService

const val delay = 5000L

@RestController
@RequestMapping("/api/slow/posts")
class PostSlowController(private val service: PostService) {
    @GetMapping
    fun getAll(): List<Post> {
        Thread.sleep(delay)
        return service.getAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Post {
        Thread.sleep(delay)
        return service.getById(id)
    }

    @PostMapping
    fun save(@RequestBody dto: Post): Post {
        Thread.sleep(delay)
        return service.save(dto)
    }

    @DeleteMapping("/{id}")
    fun removeById(@PathVariable id: Long) {
        Thread.sleep(delay)
        service.removeById(id)
    }
}