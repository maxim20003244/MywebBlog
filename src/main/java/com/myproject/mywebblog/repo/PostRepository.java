package com.myproject.mywebblog.repo;

import com.myproject.mywebblog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post,Long> {

}
