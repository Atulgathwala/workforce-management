package com.example.workforce.repository.impl;

import com.example.workforce.common.model.TaskManagement;
import com.example.workforce.common.model.enums.Priority;
import com.example.workforce.common.model.enums.ReferenceType;
import com.example.workforce.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class InMemoryTaskRepository implements TaskRepository {

    private final List<TaskManagement> tasks = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public TaskManagement save(TaskManagement task) {
        if (task.getId() == null) {
            task.setId(idGenerator.getAndIncrement());
        } else {
            tasks.removeIf(t -> Objects.equals(t.getId(), task.getId()));
        }
        tasks.add(task);
        return task;
    }

    @Override
    public Optional<TaskManagement> findById(Long id) {
        return tasks.stream()
                .filter(t -> Objects.equals(t.getId(), id))
                .findFirst();
    }

    @Override
    public List<TaskManagement> findAll() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<TaskManagement> findByReferenceIdAndReferenceType(Long referenceId, ReferenceType referenceType) {
        return tasks.stream()
                .filter(t -> Objects.equals(t.getReferenceId(), referenceId)
                        && t.getReferenceType() == referenceType)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskManagement> findByAssigneeIdIn(List<Long> assigneeIds) {
        return tasks.stream()
                .filter(t -> assigneeIds.contains(t.getAssigneeId()))
                .collect(Collectors.toList());
    }
    @Override
    public List<TaskManagement> findByPriority(Priority priority) {
        return tasks.stream()
                .filter(t -> priority.equals(t.getPriority()))
                .collect(Collectors.toList());
    }


}
