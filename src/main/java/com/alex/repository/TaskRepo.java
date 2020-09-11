package com.alex.repository;

import com.alex.domain.TaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<TaskEntity, Long> {
}
