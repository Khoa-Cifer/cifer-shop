package com.cifer.ecommerce.service;

import com.cifer.ecommerce.exception.RoleAlreadyExistException;
import com.cifer.ecommerce.exception.UserNotFoundException;
import com.cifer.ecommerce.model.Role;
import com.cifer.ecommerce.model.User;

import java.util.List;

public interface IRoleService {
    List<Role> getRoles();

    Role createRole(Role theRole) throws RoleAlreadyExistException;

    void deleteRole(Long id);

    Role findByName(String name);

    User removeUserFromRole(Long userId, Long roleId) throws UserNotFoundException;

    User assignRoleToUser(Long userId, Long roleId) throws RoleAlreadyExistException;

    Role removeAllUsersFromRole(Long roleId);
}
