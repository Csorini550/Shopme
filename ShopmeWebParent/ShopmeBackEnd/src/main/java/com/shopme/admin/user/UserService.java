package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;
    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }

    public List<Role> listAllRoles() {
        return (List<Role>) roleRepo.findAll();
    }

    public void save(User user){
        encodePassword(user);
        repo.save(user);
    }

    public boolean isEmailUnique(String email){
        User userByEmail = repo.getUserByEmail(email);
        return userByEmail == null;

    }

    public void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
}
