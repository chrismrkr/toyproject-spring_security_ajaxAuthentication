package security.corespringsecurity.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import security.corespringsecurity.domain.Account;
import security.corespringsecurity.repository.UserRepository;
import security.corespringsecurity.service.UserService;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void createUser(Account account) {
        userRepository.save(account);
    }

}
