package com.interior.archiThink.controller;

import com.interior.archiThink.dto.ProjectDto;
import com.interior.archiThink.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        public ResponseEntity<?> createProject(@RequestBody ProjectDto projectDTO) {
            try {
                ProjectDto created = projectTypeImpl.saveProject(projectDTO);
                return new ResponseEntity<>(created, HttpStatus.CREATED); // 201 for creation
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>("An error occurred while creating the service type", HttpStatus.INTERNAL_SERVER_ERROR);
            }
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
            if(Objects.nonNull(updated)) {
                return ResponseEntity.ok(updated);
            }
            return ResponseEntity.notFound().build();
        }

        @DeleteMapping("{id}")
        public ResponseEntity<String> deleteProject(@PathVariable Long id) {
            if (projectTypeImpl.deleteProjectById(id)) {
                return ResponseEntity.ok("Project deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }
