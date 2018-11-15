package com.tablet.repository.domain;

import com.modelsale.model.User;
import com.tablet.repository.IListRepository;

public interface IUserRepository extends IListRepository<User> {
    User findByLogin(String login);
}
