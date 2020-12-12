package com.dds.demo.service.impl;

import com.dds.demo.dto.UserDTO;
import com.dds.demo.exception.BadRequestAlertException;
import com.dds.demo.model.User;
import com.dds.demo.repository.UserRepository;
import com.dds.demo.service.UserService;
import com.dds.demo.service.mapper.UserMapper;
import com.dds.demo.utils.TranslatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private static final String USER = "user";

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Page<UserDTO> getAll(Pageable pageable) {
        logger.debug(">>>>>>>>>>> find all with pageable");
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    public User update(UserDTO userDTO) {
        logger.debug(">>>>>>>>> update user");
        if (userDTO.getId() == null) {
            throw new BadRequestAlertException(TranslatorUtils.toLocale("error.user.missing_id"), UserServiceImpl.USER, "user.missing_id");
        }
        return userRepository.save(userMapper.toEntity(userDTO));
    }

    @Override
    public User insert(UserDTO userDTO) {
        logger.debug(">>>>>>>>>> Insert user");
        return userRepository.save(userMapper.toEntity(userDTO));
    }

    @Override
    public void delete(Long id) {
        logger.debug(">>>>>>>>> Delete User");
        userRepository.deleteById(id);
    }
}
