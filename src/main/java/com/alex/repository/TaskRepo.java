package com.alex.repository;

import com.alex.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<Task, Long> {
}
