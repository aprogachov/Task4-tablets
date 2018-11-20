package com.tablet.authorization;

import com.modelsale.model.User;
import com.tablet.repository.domain.IUserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Console;
import java.util.Scanner;

@Component
public class UserAuthorization implements IUserAuthorization {

    @Autowired
    private IUserRepository iuserRepository;
    @Autowired
    private SaltItem saltItem;
    @Autowired
    private UserLoginHolder userLoginHolder;

    @Override
    public void findUser() {
        Scanner input = new Scanner(System.in);
        Console console = System.console();
        System.out.println("Your login: ");
        String flogin = input.nextLine();
        System.out.println("Your password: "); // user_1
        String fpassword = (console != null) ? new String(console.readPassword()): input.nextLine();
        /* www.browserling.com/tools/bcrypt-check */
        String hashPassword = BCrypt.hashpw(fpassword, saltItem.getSalt());

        User fuser = iuserRepository.findByLogin(flogin);

        if (fuser == null) {
            System.out.println("user not found");
        } else {
            if (fuser.getPassword().equals(hashPassword)) {
                userLoginHolder.login(fuser);
                System.out.println(fuser);
            } else {
                System.out.println("user not found");
            }
        }
    }
}
