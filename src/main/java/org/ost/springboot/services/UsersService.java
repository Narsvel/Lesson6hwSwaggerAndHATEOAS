package org.ost.springboot.services;

import org.ost.springboot.models.User;
import org.ost.springboot.repositories.UsersRepository;
import org.ost.springboot.utils.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) //все методы внутри класса будут по default Transactional только чтение
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
        return foundUser.orElseThrow(UserNotFoundException::new);
    }

    @Transactional //т.к. метод не только читает данные, указываем аннотация Transactional, кторая заменит аннотицию класса
    public User save(User user) {
        return usersRepository.save(user);
    }

    @Transactional
    public User update(int id, User updateUser) {
        Optional<User> foundUser = usersRepository.findById(id);
        foundUser.ifPresent(user -> updateUser.setCreateAt(user.getCreateAt()));
        updateUser.setId(id);
        return usersRepository.save(updateUser);
    }

    @Transactional
    public void delete(int id) {
        if (usersRepository.findById(id).isEmpty())
            throw new UserNotFoundException();
        usersRepository.deleteById(id);
    }

}
