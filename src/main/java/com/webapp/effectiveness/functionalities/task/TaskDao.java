package com.webapp.effectiveness.functionalities.task;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao extends CrudRepository<Task, Long> {
}
