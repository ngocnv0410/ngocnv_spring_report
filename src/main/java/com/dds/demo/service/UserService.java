package com.dds.demo.service;

import com.dds.demo.dto.UserDTO;
import com.dds.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDTO> getAll(Pageable pageable);
    User update(UserDTO userDTO);
    User insert(UserDTO userDTO);
    void delete(Long id);
}
