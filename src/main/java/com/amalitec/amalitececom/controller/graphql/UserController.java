package com.amalitec.amalitececom.controller.graphql;


import com.amalitec.amalitececom.request_response.graphql.input.UserInput;
import com.amalitec.amalitececom.request_response.graphql.output.AllUserOutput;
import com.amalitec.amalitececom.request_response.graphql.output.DeletionStatus;
import com.amalitec.amalitececom.request_response.graphql.output.UserOutput;
import com.amalitec.amalitececom.mapper.UserMapper;
import com.amalitec.amalitececom.model.User;
import com.amalitec.amalitececom.service.UserService;
import com.amalitec.amalitececom.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    final private UserService userService;
    final private UserMapper userMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Transactional(readOnly = true)
    @QueryMapping
    public AllUserOutput getAllUsers() {
        List<User> users = userService.getAllUsers();
        log.info("count of users: {} ",users.size());
        AllUserOutput userOutput = new AllUserOutput();
        userOutput.setUserList(users);
        userOutput.setMessage(Constants.SUCCESS);
        userOutput.setStatus(Constants.SUCCESS_STATUS);

        return userOutput;
    }

    @Transactional(readOnly = true)
    @QueryMapping
    public UserOutput getUserById(@Argument UUID id) {
        UserOutput userOutput = new UserOutput();
        Optional<User> optionalUser = userService.getUserById(id);

        if(!optionalUser.isPresent()){
            userOutput.setMessage(Constants.NOTFOUND);
            userOutput.setStatus(Constants.FAILURE_STATUS_02);
            return userOutput;
        }else{
            User user = optionalUser.get();
            userOutput.setUser(user);
            userOutput.setMessage(Constants.SUCCESS);
            userOutput.setStatus(Constants.SUCCESS_STATUS);
        }

        return userOutput;

    }

    @Transactional
    @MutationMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public UserOutput createUser(@Argument UserInput input) {
        UserOutput userOutput = new UserOutput();

        try {
            User user = userMapper.toEntity(input);
            logger.info("user: {}", user);
            userOutput.setMessage(Constants.SUCCESS);
            userOutput.setStatus(Constants.SUCCESS_STATUS);
            userOutput.setUser(user);

            User createdUser = userService.createUser(user);

        }catch (Exception e){
            userOutput.setMessage(Constants.FAILURE_MESSAGE);
            userOutput.setStatus(Constants.FAILURE_STATUS_01);
        }

        return userOutput;

    }

    @Transactional
    @MutationMapping
    public UserOutput updateUser( @Argument UserInput input) {
        UUID id = input.getId();

        User user=userMapper.toEntity(input);
        UserOutput userOutput = new UserOutput();

        if (!userService.existsById(id)) {
            userOutput.setMessage(Constants.NOTFOUND);
            userOutput.setStatus(Constants.FAILURE_STATUS_02);
        }else {
            User updatedUser = userService.updateUser(id, user);
            userOutput.setMessage(Constants.SUCCESS);
            userOutput.setStatus(Constants.SUCCESS_STATUS);
            userOutput.setUser(updatedUser);
        }

        return userOutput;
    }

    @Transactional
    @MutationMapping
    public DeletionStatus deleteUser(@Argument UUID id) {
        logger.info("user with id: {} being deleted",id);
        if (!userService.existsById(id)) {
            logger.info("user with id: {} not found",id);
            return new DeletionStatus(false, "User not found");
        }
        userService.deleteUser(id);
        logger.info("user with id: {} deleted successfully",id);
        return new DeletionStatus(true, "User deleted successfully");
    }

}
