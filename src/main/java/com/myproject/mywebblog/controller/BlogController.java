package com.myproject.mywebblog.controller;

import com.myproject.mywebblog.models.Post;
import com.myproject.mywebblog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;


@Controller
public class BlogController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam(required = false) String title,@RequestParam String anons,@RequestParam String full_text, Model model) {
        Post post = new Post(title,anons,full_text);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }

        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-details";
    }

        @GetMapping("/blog/{id}/edit")
        public String blogEdit(@PathVariable(value = "id") long id, Model model){
            if (!postRepository.existsById(id)) {
                return "redirect:/blog";
            }

            Optional<Post> post = postRepository.findById(id);
            ArrayList<Post> res = new ArrayList<>();
            post.ifPresent(res::add);
            model.addAttribute("post", res);
            return "blog-edit";
        }
    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate( @PathVariable(value = "id") long id ,String title,@RequestParam String anons,@RequestParam String full_text, Model model) {
        Post post  = null;
        try {
            post = postRepository.findById(id).orElseThrow(Exception::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
        post.setTitle(title);
       post.setAnonos(anons);
       post.setFull_text(full_text);
       postRepository.save(post);
        return "redirect:/blog/{id}";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete( @PathVariable(value = "id") long id , Model model) throws Exception {
        Post post  = null;
        try {
            post = postRepository.findById(id).orElseThrow(Exception::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
        postRepository.delete(post);
        return "redirect:/blog";
    }

}