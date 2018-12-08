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
    public boolean findUser() {
        Scanner input = new Scanner(System.in);
        Console console = System.console();
        System.out.println("Your login: ");
        String flogin = input.nextLine();
        System.out.println("Your password: "); // user_1
        String fpassword = (console != null) ? new String(console.readPassword()): input.nextLine();

        String hashPassword = BCrypt.hashpw(fpassword, saltItem.getSalt()); /* www.browserling.com/tools/bcrypt-check */

        User fuser = iuserRepository.findByLogin(flogin);

        boolean status;
        if (fuser == null) {
            System.out.println("user not findByLogin");
            status = false;
        } else {
            if (fuser.getPassword().equals(hashPassword)) {
                userLoginHolder.login(fuser);
                System.out.println(fuser);
                status = true;
            } else {
                System.out.println("Error password");
                status = false;
            }
        }
        return status;
    }
}

