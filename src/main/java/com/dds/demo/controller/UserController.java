package com.dds.demo.controller;

import com.dds.demo.dto.UserDTO;
import com.dds.demo.service.UserService;
import com.dds.demo.utils.PaginationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll(Pageable pageable) {
        Page<UserDTO> page = userService.getAll(pageable);
        return ResponseEntity.ok()
                .headers(PaginationUtils.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequestUri(), page))
                .body(page.getContent());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO) {
        userService.insert(userDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO) {
        userService.update(userDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
