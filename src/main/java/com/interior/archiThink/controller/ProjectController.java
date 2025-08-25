package com.interior.archiThink.controller;

import com.interior.archiThink.dto.ProjectDto;
import com.interior.archiThink.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/project")
public class ProjectController {

        @Autowired
        private ProjectServiceImpl projectTypeImpl;

        @GetMapping
        public ResponseEntity<?> getAllProject() {
            List<ProjectDto> projectList = projectTypeImpl.getProjectList();
            if (!projectList.isEmpty()) {
                return ResponseEntity.ok(projectList);
            }
            return ResponseEntity.notFound().build();
        }

        @PostMapping
        public ResponseEntity<?> createProject(@RequestBody ProjectDto projectDTO,
            UriComponentsBuilder uriBuilder) {
            ProjectDto created = projectTypeImpl.saveProject(projectDTO);
            if(created==null)
                return ResponseEntity.badRequest().build();
            var uri = uriBuilder.path("project/{id}").buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uri).body(created); // 201 for creation
        }

        @GetMapping("{id}")
        public ResponseEntity<?> getProject(@PathVariable Long id) {
            ProjectDto project = projectTypeImpl.getProject(id);
            if(Objects.nonNull(project)) {
                return ResponseEntity.ok(project);
            }
            return ResponseEntity.notFound().build();
        }

        @PutMapping("{id}")
        public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDTO) {
            ProjectDto updated = projectTypeImpl.updateProject(projectDTO, id);
            if(updated == null)
                return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(updated);
        }

        @DeleteMapping("{id}")
        public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
            if (projectTypeImpl.deleteProjectById(id))
                return ResponseEntity.noContent().build();
            return ResponseEntity.notFound().build();
        }
    }
