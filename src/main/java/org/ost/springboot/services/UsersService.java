package org.ost.springboot.services;

import org.ost.springboot.models.User;
import org.ost.springboot.repositories.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) //все методы внутри класса будут Transactional только чтение
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public User findById(int id) {
        Optional<User> foundUser = usersRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Transactional
    public void save(User user) {
        usersRepository.save(user);
    }

    @Transactional
    public void update(int id, User updateUser) {
        Optional<User> foundUser = usersRepository.findById(id);
        foundUser.ifPresent(user -> updateUser.setCreateAt(user.getCreateAt()));
        updateUser.setId(id);
        usersRepository.save(updateUser);
    }

    @Transactional
    public void delete(int id) {
        usersRepository.deleteById(id);
    }

}
